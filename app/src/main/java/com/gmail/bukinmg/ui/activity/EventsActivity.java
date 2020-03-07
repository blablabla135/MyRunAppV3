package com.gmail.bukinmg.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.gmail.bukinmg.R;
import com.gmail.bukinmg.databinding.ActivityEventsBinding;
import com.gmail.bukinmg.di.ViewModelFactory;
import com.gmail.bukinmg.ui.adapter.EventsAdapter;
import com.gmail.bukinmg.viewmodel.EventsViewModel;
import com.gmail.bukinmg.viewmodel.LoginViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class EventsActivity extends AppCompatActivity {

    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    EventsAdapter eventsAdapter;
    private EventsViewModel eventsViewModel;
    private ActivityEventsBinding activityEventsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidInjection.inject(this);

        eventsViewModel = new ViewModelProvider(this, viewModelFactory).get(EventsViewModel.class);

        activityEventsBinding = DataBindingUtil.setContentView(this, R.layout.activity_events);
        activityEventsBinding.setEventsViewModel(eventsViewModel);
        activityEventsBinding.setLifecycleOwner(this);

        eventsViewModel.getEventsList().observe(this, events -> {
            eventsAdapter.setEvents(eventsViewModel.getEventsList().getValue());
            activityEventsBinding.setEventsAdapter(eventsAdapter);
        });

        eventsViewModel.backTrigger.observe(this, aBoolean -> {
            if (eventsViewModel.backTrigger != null) {
                Intent intent = new Intent(EventsActivity.this, MainMenuActivity.class);
                startActivity(intent);
            }
        });
    }


}
