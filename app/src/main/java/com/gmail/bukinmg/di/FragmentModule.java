package com.gmail.bukinmg.di;

import com.gmail.bukinmg.ui.MenuDialogFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract MenuDialogFragment contributeMenuDialogFragment();
}
