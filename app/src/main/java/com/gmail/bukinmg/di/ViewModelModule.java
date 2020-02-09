package com.gmail.bukinmg.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.gmail.bukinmg.viewmodel.LoginViewModel;
import com.gmail.bukinmg.viewmodel.MainMenuViewModel;
import com.gmail.bukinmg.viewmodel.RegisterViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    protected abstract ViewModel loginViewModel(LoginViewModel loginViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel.class)
    protected abstract ViewModel registerViewModel(RegisterViewModel registerViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MainMenuViewModel.class)
    protected abstract ViewModel mainMenuViewModel(MainMenuViewModel mainMenuViewModel);

}
