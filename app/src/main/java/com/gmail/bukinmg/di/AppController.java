package com.gmail.bukinmg.di;

import android.app.Activity;
import android.app.Application;


import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasAndroidInjector;

public class AppController extends Application implements HasAndroidInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }

}
