package com.dakota.gallimore.homeawaysearch;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import net.openid.appauth.AuthorizationRequest;
import net.openid.appauth.AuthorizationServiceConfiguration;
import net.openid.appauth.ResponseTypeValues;

import static com.dakota.gallimore.homeawaysearch.Utils.AuthUtils.doAuthorization;
import static com.dakota.gallimore.homeawaysearch.Utils.AuthUtils.readAuthState;


/**
 * Created by galli_000 on 10/30/2017.
 * Activity responsible for handling initial API authorization check using 3-legged OAuth2.
 */
public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (readAuthState(this).isAuthorized()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        AuthorizationServiceConfiguration serviceConfig =
                new AuthorizationServiceConfiguration(
                        Uri.parse("https://ws.homeaway.com/oauth/authorize"), // authorization endpoint
                        Uri.parse("https://ws.homeaway.com/oauth/token")); // token endpoint

        AuthorizationRequest.Builder authRequestBuilder =
                new AuthorizationRequest.Builder(
                        serviceConfig, // the authorization service configuration
                        "b7159595-c72d-4c9b-8c53-37d649a7d2b8", // the client ID, typically pre-registered and static
                        ResponseTypeValues.CODE, // the response_type value: we want an auth code
                        Uri.parse("https://ws.homeaway.com/auth-code")); // the redirect URI to which the auth response is sent

        doAuthorization(this, authRequestBuilder.build());
    }
}
