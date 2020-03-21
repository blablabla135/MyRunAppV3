package com.gmail.bukinmg.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.gmail.bukinmg.R;
import com.gmail.bukinmg.databinding.ActivityMainMenuBinding;
import com.gmail.bukinmg.di.ViewModelFactory;
import com.gmail.bukinmg.ui.fragment.MenuDialogFragment;
import com.gmail.bukinmg.ui.adapter.DatesAdapter;
import com.gmail.bukinmg.viewmodel.MainMenuViewModel;


import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainMenuActivity extends AppCompatActivity {

    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    DatesAdapter datesAdapter;
    private MainMenuViewModel mainMenuViewModel;
    private ActivityMainMenuBinding activityMainMenuBinding;
    private MenuDialogFragment dialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidInjection.inject(this);

        mainMenuViewModel = new ViewModelProvider(this, viewModelFactory).get(MainMenuViewModel.class);

        activityMainMenuBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_menu);
        activityMainMenuBinding.setMainMenuViewModel(mainMenuViewModel);
        activityMainMenuBinding.setLifecycleOwner(this);

        Intent intentLogin = getIntent();

        String eMail = intentLogin.getStringExtra("email");
        String mainEventDate = intentLogin.getStringExtra("date");

        mainMenuViewModel.setUserEmail(eMail);
        mainMenuViewModel.setMainEventDate(mainEventDate);

        mainMenuViewModel.initializeDates();

        mainMenuViewModel.getDatesList().observe(this, days -> {
            datesAdapter.setDays(mainMenuViewModel.getDatesList().getValue());
            activityMainMenuBinding.setDatesAdapter(datesAdapter);
        });

        datesAdapter.setOnItemClickListener(day -> {
            mainMenuViewModel.setDay(day);
            dialogFragment = new MenuDialogFragment();
            dialogFragment.show(getSupportFragmentManager(), "MenuDialogFragment");
        });

        mainMenuViewModel.addTrigger.observe(this, booleanEventWrapper -> {
            if (mainMenuViewModel.addTrigger != null) {
                dialogFragment.dismiss();
                mainMenuViewModel.initializeDates();
            }
        });

        mainMenuViewModel.cancelTrigger.observe(this, booleanEventWrapper -> {
            if (mainMenuViewModel.cancelTrigger != null) {
                dialogFragment.dismiss();
            }
        });

        mainMenuViewModel.listTrigger.observe(this, booleanEventWrapper -> {
            if (mainMenuViewModel.listTrigger != null) {
                Intent intent = new Intent(MainMenuActivity.this, EventsActivity.class);
                intent.putExtra("email", eMail);
                intent.putExtra("date", mainEventDate);
                startActivity(intent);
            }
        });


    }
}
