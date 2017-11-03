package com.dakota.gallimore.homeawaysearch.Utils;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by galli_000 on 11/1/2017.
 * Utility class aiding in Network operations.
 */

public class NetworkUtils {

    private static JSONObject jsonObject;

    /**
     * Handles grabbing data from a specified URL using provided accessToken. Utilizes OkHttp asynchronous requests and will fire NetworkCallback when data is retrieved.
     *
     * @param url             - URL endpoint for Json API (HomeAway)
     * @param accessToken     - accessToken required to access data
     * @param networkCallback - Callback to monitor when asynchronous data is returned
     */
    public static void getHomeAwayJsonData(String url, String accessToken, final NetworkCallback networkCallback) {

        // use the access token to do something ...
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + accessToken)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                final String exception = e.getMessage();
                Log.d("Networking Utils: ", exception);
                try {
                    networkCallback.onJsonObjectReturn(new JSONObject(""));
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    try {
                        jsonObject = new JSONObject(response.body().string());
                        networkCallback.onJsonObjectReturn(jsonObject);
                        Log.d("Networking Utils: ", response.body().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (NullPointerException npe) {
                        npe.printStackTrace();
                    }
                }
            }
        });
    }
}
