package com.dakota.gallimore.homeawaysearch.Utils;

import android.os.Build;
import android.util.Log;

import com.dakota.gallimore.homeawaysearch.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.TlsVersion;

/**
 * Created by galli_000 on 11/1/2017.
 * Utility class aiding in Network operations.
 */

public class NetworkUtils {

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
                Log.d(Constants.NETWORK_LOG_TAG, exception);
                networkCallback.onJsonObjectReturn("");
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    try {
                        String data = response.body().string();
                        networkCallback.onJsonObjectReturn(data);
                        Log.d(Constants.NETWORK_LOG_TAG, "OkHttp Response received ok");
                    } catch (NullPointerException npe) {
                        npe.printStackTrace();
                    }
                }
            }
        });
    }

    public static OkHttpClient.Builder enableTls12OnPreLollipop(OkHttpClient.Builder client) {
        if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 22) {
            try {
                SSLContext sc = SSLContext.getInstance("TLSv1.2");
                sc.init(null, null, null);
                client.sslSocketFactory(new Tls12SocketFactory(sc.getSocketFactory()));

                ConnectionSpec cs = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                        .tlsVersions(TlsVersion.TLS_1_2)
                        .build();

                List specs = new ArrayList<>();
                specs.add(cs);
                specs.add(ConnectionSpec.COMPATIBLE_TLS);
                specs.add(ConnectionSpec.CLEARTEXT);

                client.connectionSpecs(specs);
            } catch (Exception exc) {
                Log.e("OkHttpTLSCompat", "Error while setting TLS 1.2", exc);
            }
        }

        return client;
    }

    public static OkHttpClient getNewHttpClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .followRedirects(true)
                .followSslRedirects(true)
                .retryOnConnectionFailure(true)
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS);
        return enableTls12OnPreLollipop(client).build();
    }
}
