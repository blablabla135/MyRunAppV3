package com.gmail.bukinmg.di;

import com.gmail.bukinmg.ui.LoginActivity;
import com.gmail.bukinmg.ui.MainMenuActivity;
import com.gmail.bukinmg.ui.RegisterActivity;

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
}
