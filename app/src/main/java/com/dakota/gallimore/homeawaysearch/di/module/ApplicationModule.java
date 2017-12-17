package com.dakota.gallimore.homeawaysearch.di.module;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by galli_000 on 12/15/2017.
 */

@Module
public class ApplicationModule {

    @NonNull
    private final Application application;

    public ApplicationModule(@NonNull Application app) {
        this.application = app;
    }

    @Provides @NonNull @Singleton
    public Application provideApplication() {
        return application;
    }

    @Provides @NonNull @Singleton
    public Context provideContext(@NonNull Application app) {
        return app;
    }

    @Provides @NonNull @Singleton
    public Gson provideGson() {
        return new Gson();
    }
}
