package com.dakota.gallimore.homeawaysearch.Utils;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.dakota.gallimore.homeawaysearch.di.HomeAwaySearchApplication;

import java.io.InputStream;

import javax.inject.Inject;

import okhttp3.OkHttpClient;

/**
 * Created by galli_000 on 11/10/2017.
 */
@GlideModule
public final class OkHttpLibraryGlideModule extends AppGlideModule {

    @Inject
    OkHttpClient client;

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        HomeAwaySearchApplication.get(context).getAppComponent().inject(this);
        OkHttpUrlLoader.Factory factory = new OkHttpUrlLoader.Factory(client);
        glide.getRegistry().replace(GlideUrl.class, InputStream.class, factory);
    }
}
