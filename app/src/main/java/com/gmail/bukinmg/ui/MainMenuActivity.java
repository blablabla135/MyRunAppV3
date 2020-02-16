package com.gmail.bukinmg.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.bukinmg.R;
import com.gmail.bukinmg.databinding.ActivityMainMenuBinding;
import com.gmail.bukinmg.di.ViewModelFactory;
import com.gmail.bukinmg.ui.adapter.DatesAdapter;
import com.gmail.bukinmg.viewmodel.MainMenuViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainMenuActivity extends AppCompatActivity {

    @Inject
    ViewModelFactory viewModelFactory;
    private MainMenuViewModel mainMenuViewModel;
    private ActivityMainMenuBinding activityMainMenuBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidInjection.inject(this);

        mainMenuViewModel = new ViewModelProvider(this, viewModelFactory).get(MainMenuViewModel.class);

        activityMainMenuBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_menu);
        activityMainMenuBinding.setMainMenuViewModel(mainMenuViewModel);
        activityMainMenuBinding.setLifecycleOwner(this);

        DatesAdapter datesAdapter = new DatesAdapter(mainMenuViewModel.dates);
        activityMainMenuBinding.setDatesAdapter(datesAdapter);


    }
}
