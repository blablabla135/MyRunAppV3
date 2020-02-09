package com.gmail.bukinmg.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.gmail.bukinmg.R;
import com.gmail.bukinmg.databinding.ActivityLoginBinding;
import com.gmail.bukinmg.di.DaggerAppComponent;
import com.gmail.bukinmg.viewmodel.LoginViewModel;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity {

    @Inject
    LoginViewModel loginViewModel;
    ActivityLoginBinding activityLoginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerAppComponent.builder().build().inject(this);

        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        activityLoginBinding.setLoginViewModel(loginViewModel);
        activityLoginBinding.setLifecycleOwner(this);

        loginViewModel.mainTrigger.observe(this, aBoolean -> {
            if (aBoolean) {
                Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
                startActivity(intent);
            }
        });

        loginViewModel.registerTrigger.observe(this, aBoolean -> {
            if (aBoolean) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}
