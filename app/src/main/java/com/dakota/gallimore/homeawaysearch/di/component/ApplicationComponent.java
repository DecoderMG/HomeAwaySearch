package com.dakota.gallimore.homeawaysearch.di.component;

import com.dakota.gallimore.homeawaysearch.ListingActivity;
import com.dakota.gallimore.homeawaysearch.LoginActivity;
import com.dakota.gallimore.homeawaysearch.MainActivity;
import com.dakota.gallimore.homeawaysearch.Utils.OkHttpLibraryGlideModule;
import com.dakota.gallimore.homeawaysearch.Views.SearchFragment;
import com.dakota.gallimore.homeawaysearch.di.module.ApplicationModule;
import com.dakota.gallimore.homeawaysearch.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by galli_000 on 12/15/2017.
 */

@Singleton
@Component(modules = {
        ApplicationModule.class,
        NetworkModule.class})
public interface ApplicationComponent {
    void inject(LoginActivity loginActivity);
    void inject(MainActivity mainActivity);
    void inject(ListingActivity listingActivity);
    void inject(SearchFragment searchFragment);
    void inject(OkHttpLibraryGlideModule okHttpLibraryGlideModule);
}
