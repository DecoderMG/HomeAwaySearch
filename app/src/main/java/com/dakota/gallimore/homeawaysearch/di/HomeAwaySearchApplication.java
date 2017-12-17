package com.dakota.gallimore.homeawaysearch.di;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.dakota.gallimore.homeawaysearch.di.component.ApplicationComponent;
import com.dakota.gallimore.homeawaysearch.di.component.DaggerApplicationComponent;
import com.dakota.gallimore.homeawaysearch.di.module.ApplicationModule;
import com.dakota.gallimore.homeawaysearch.di.module.NetworkModule;

/**
 * Created by galli_000 on 12/15/2017.
 */

public class HomeAwaySearchApplication extends Application {

    private ApplicationComponent appComponent;

    public static HomeAwaySearchApplication get(@NonNull Context ctx) {
        return (HomeAwaySearchApplication) ctx.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = buildComponent();
    }

    public ApplicationComponent getAppComponent() { return appComponent; }

    protected ApplicationComponent buildComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule())
                .build();
    }
}
