package com.gmail.bukinmg.di;

import com.gmail.bukinmg.ui.activity.EventsActivity;
import com.gmail.bukinmg.ui.activity.LoginActivity;
import com.gmail.bukinmg.ui.activity.MainMenuActivity;
import com.gmail.bukinmg.ui.activity.RegisterActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract LoginActivity contributeLoginActivity();

    @ContributesAndroidInjector
    abstract RegisterActivity contributeRegisterActivity();

    @ContributesAndroidInjector
    abstract MainMenuActivity contributeMainMenuActivity();

    @ContributesAndroidInjector
    abstract EventsActivity contributeEventsActivity();
}
