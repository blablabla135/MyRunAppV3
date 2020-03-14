package com.gmail.bukinmg.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
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
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidInjection.inject(this);

        eventsViewModel = new ViewModelProvider(this, viewModelFactory).get(EventsViewModel.class);

        activityEventsBinding = DataBindingUtil.setContentView(this, R.layout.activity_events);
        activityEventsBinding.setEventsViewModel(eventsViewModel);
        activityEventsBinding.setLifecycleOwner(this);

        recyclerView = findViewById(R.id.events_recycler_view);

        linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(eventsAdapter);

        eventsViewModel.getEventsList().observe(this, events -> {
            eventsAdapter.setEvents(eventsViewModel.getEventsList().getValue());
            //activityEventsBinding.setEventsAdapter(eventsAdapter);
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                eventsViewModel.deleteEvent(eventsAdapter.getEventAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);

        eventsViewModel.backTrigger.observe(this, aBoolean -> {
            if (eventsViewModel.backTrigger != null) {
                Intent intent = new Intent(EventsActivity.this, MainMenuActivity.class);
                startActivity(intent);
            }
        });
    }


}
