package com.gmail.bukinmg.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.gmail.bukinmg.R;
import com.gmail.bukinmg.databinding.ActivityLoginBinding;
import com.gmail.bukinmg.di.ViewModelFactory;
import com.gmail.bukinmg.viewmodel.LoginViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class LoginActivity extends AppCompatActivity {

    @Inject
    ViewModelFactory viewModelFactory;
    private LoginViewModel loginViewModel;
    private ActivityLoginBinding activityLoginBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidInjection.inject(this);

        loginViewModel = new ViewModelProvider(this, viewModelFactory).get(LoginViewModel.class);

        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        activityLoginBinding.setLoginViewModel(loginViewModel);
        activityLoginBinding.setLifecycleOwner(this);

        loginViewModel.mainTrigger.observe(this, aBoolean -> {
            if (loginViewModel.mainTrigger != null) {
                Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
                intent.putExtra("email", loginViewModel.eMail.getValue());
                intent.putExtra("date", loginViewModel.getMainEventDate());
                startActivity(intent);
            }
        });

        loginViewModel.registerTrigger.observe(this, aBoolean -> {
            if (loginViewModel.registerTrigger != null) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
