package com.dakota.gallimore.homeawaysearch.di.module;

import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;

import com.dakota.gallimore.homeawaysearch.Utils.Tls12SocketFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;
import javax.net.ssl.SSLContext;

import dagger.Module;
import dagger.Provides;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;

/**
 * Created by galli_000 on 12/15/2017.
 */

@Module
public class NetworkModule {

    public NetworkModule() { }

    @Provides @NonNull @Singleton
    public OkHttpClient.Builder provideHttpClientBuilder() {
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .followRedirects(true)
                .followSslRedirects(true)
                .retryOnConnectionFailure(true)
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS);
        return client;
    }

    @Provides @NonNull @Singleton
    public OkHttpClient provideHttpClientTLS2(OkHttpClient.Builder builder) {
        if (Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT < 22) {
            try {
                SSLContext sc = SSLContext.getInstance("TLSv1.2");
                sc.init(null, null, null);
                builder.sslSocketFactory(new Tls12SocketFactory(sc.getSocketFactory()));
                ConnectionSpec cs = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                        .tlsVersions(TlsVersion.TLS_1_2)
                        .build();
                List specs = new ArrayList<>();
                specs.add(cs);
                specs.add(ConnectionSpec.COMPATIBLE_TLS);
                specs.add(ConnectionSpec.CLEARTEXT);
                builder.connectionSpecs(specs);
            } catch (Exception exc) {
                Log.e("OkHttpTLSCompat", "Error while setting TLS 1.2", exc);
            }
        }
        return builder.build();
    }
}
