package com.gmail.bukinmg.di;

import com.gmail.bukinmg.ui.LoginActivity;
import com.gmail.bukinmg.ui.MainMenuActivity;
import com.gmail.bukinmg.ui.RegisterActivity;

import dagger.Component;

@Component
public interface AppComponent {

    void inject(LoginActivity loginActivity);

    void inject(RegisterActivity registerActivity);

    void inject(MainMenuActivity mainMenuActivity);
}
