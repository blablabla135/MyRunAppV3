package com.gmail.bukinmg.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.gmail.bukinmg.R;
import com.gmail.bukinmg.databinding.ActivityRegisterBinding;
import com.gmail.bukinmg.di.DaggerAppComponent;
import com.gmail.bukinmg.viewmodel.LoginViewModel;
import com.gmail.bukinmg.viewmodel.RegisterViewModel;

import javax.inject.Inject;

public class RegisterActivity extends AppCompatActivity {

    @Inject
    RegisterViewModel registerViewModel;
    ActivityRegisterBinding activityRegisterBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        DaggerAppComponent.builder().build().inject(this);

        activityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        activityRegisterBinding.setRegisterViewModel(registerViewModel);
        activityRegisterBinding.setLifecycleOwner(this);


        registerViewModel.loginTrigger.observe(this, aBoolean -> {
            if (aBoolean) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }


}
