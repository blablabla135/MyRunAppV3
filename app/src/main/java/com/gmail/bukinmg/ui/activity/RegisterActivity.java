package com.gmail.bukinmg.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.gmail.bukinmg.R;
import com.gmail.bukinmg.databinding.ActivityRegisterBinding;
import com.gmail.bukinmg.di.ViewModelFactory;
import com.gmail.bukinmg.ui.fragment.MainEventDialogFragment;
import com.gmail.bukinmg.utility.EventWrapper;
import com.gmail.bukinmg.viewmodel.RegisterViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class RegisterActivity extends AppCompatActivity {

    @Inject
    ViewModelFactory viewModelFactory;
    private RegisterViewModel registerViewModel;
    private ActivityRegisterBinding activityRegisterBinding;
    private MainEventDialogFragment dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        AndroidInjection.inject(this);

        registerViewModel = new ViewModelProvider(this, viewModelFactory).get(RegisterViewModel.class);


        activityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        activityRegisterBinding.setRegisterViewModel(registerViewModel);
        activityRegisterBinding.setLifecycleOwner(this);


        registerViewModel.loginTrigger.observe(this, aBoolean -> {
            if (registerViewModel.loginTrigger != null) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        registerViewModel.dialogTrigger.observe(this, booleanEventWrapper -> {
            dialogFragment = new MainEventDialogFragment();
            dialogFragment.show(getSupportFragmentManager(), "MainEventDialogFragment");
        });
    }


}
