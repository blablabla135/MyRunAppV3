package com.gmail.bukinmg.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gmail.bukinmg.model.DBRepository;
import com.gmail.bukinmg.model.entity.Event;
import com.gmail.bukinmg.utility.EventWrapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class EventsViewModel extends ViewModel {


    private LiveData<List<Event>> eventsList;
    public MutableLiveData<EventWrapper<Boolean>> backTrigger = new MutableLiveData<>();
    private DBRepository dBRepository;

    @Inject
    public EventsViewModel(DBRepository dBRepository) {
        this.dBRepository = dBRepository;
        eventsList = dBRepository.getAllLiveEvents();
    }

    public void onBackClick() {
        backTrigger.setValue(new EventWrapper<>(true));
    }

    public LiveData<List<Event>> getEventsList() {
        return eventsList;
    }

    public void deleteEvent(Event event) {
        dBRepository.delete(event);
    }


}
