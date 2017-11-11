package com.dakota.gallimore.homeawaysearch.Utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.dakota.gallimore.homeawaysearch.LoginActivity;
import com.dakota.gallimore.homeawaysearch.MainActivity;

import net.openid.appauth.AuthState;
import net.openid.appauth.AuthorizationRequest;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.AuthorizationServiceConfiguration;

import org.json.JSONException;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by galli_000 on 10/31/2017.
 * Utility class for OAuth2 authentication-based methods.
 */

public class AuthUtils {


    private static final String EXTRA_FAILED = "failed";
    private static final int RC_AUTH = 100;

    /**
     * Reads current authentication status of our app from shared preferences.
     *
     * @param context - Current activity requesting authentication status
     * @return - AuthState - contains authentication status data
     */
    @NonNull
    public static AuthState readAuthState(Context context) {
        SharedPreferences authPrefs = context.getSharedPreferences("auth", MODE_PRIVATE);
        String stateJson = authPrefs.getString("stateJson", null);

        if (stateJson != null) {
            try {
                return AuthState.jsonDeserialize(stateJson);
            } catch (JSONException e) {
                e.printStackTrace();
                return new AuthState();
            }
        } else {
            return new AuthState();
        }
    }

    /**
     * Writes current Authentication status to persistent shared preference.
     *
     * @param state   - Current authentication state to save. (i.e. authentication code, access token, etc.)
     * @param context - Current activity attempting to save authentication state
     */
    public static void writeAuthState(@NonNull AuthState state, Context context) {
        SharedPreferences authPrefs = context.getSharedPreferences("auth", MODE_PRIVATE);
        authPrefs.edit()
                .putString("stateJson", state.jsonSerializeString())
                .apply();
    }

    /**
     * Handles getting authorization code for 3-legged OAuth from OAuth server. Creates Pending Intents based on result.
     *
     * @param context     - Activity requesting authorization
     * @param authRequest - Authorization Request information
     */
    public static void doAuthorization(Context context, AuthorizationRequest authRequest) {

        Intent completionIntent = new Intent(context, MainActivity.class);
        Intent cancelIntent = new Intent(context, LoginActivity.class);
        cancelIntent.putExtra(EXTRA_FAILED, true);
        cancelIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        AuthorizationService authService = new AuthorizationService(context);
        authService.performAuthorizationRequest(
                authRequest,
                PendingIntent.getActivity(context, 0, completionIntent, 0),
                PendingIntent.getActivity(context, 0, cancelIntent, 0));
    }

    public static AuthorizationServiceConfiguration getHomeAwayAuthorizationServiceConfiguration() {
        return new AuthorizationServiceConfiguration(
                Uri.parse("https://ws.homeaway.com/oauth/authorize"), // authorization endpoint
                Uri.parse("https://ws.homeaway.com/oauth/token")); // token endpoint
    }
}
