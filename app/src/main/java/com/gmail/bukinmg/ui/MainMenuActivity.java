package com.gmail.bukinmg.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.gmail.bukinmg.R;
import com.gmail.bukinmg.databinding.ActivityMainMenuBinding;
import com.gmail.bukinmg.di.DaggerAppComponent;
import com.gmail.bukinmg.viewmodel.MainMenuViewModel;

import javax.inject.Inject;

public class MainMenuActivity extends AppCompatActivity {

    @Inject
    MainMenuViewModel mainMenuViewModel;
    ActivityMainMenuBinding activityMainMenuBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerAppComponent.builder().build().inject(this);

        activityMainMenuBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_menu);
        activityMainMenuBinding.setMainMenuViewModel(mainMenuViewModel);
        activityMainMenuBinding.setLifecycleOwner(this);
    }
}
