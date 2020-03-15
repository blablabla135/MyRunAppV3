package com.gmail.bukinmg.viewmodel;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.bukinmg.model.DBRepository;
import com.gmail.bukinmg.model.entity.Event;
import com.gmail.bukinmg.utility.EventWrapper;

import java.util.List;

import javax.inject.Inject;

public class EventsViewModel extends ViewModel {


    private LiveData<List<Event>> eventsList;
    public MutableLiveData<EventWrapper<Boolean>> backTrigger = new MutableLiveData<>();
    private DBRepository dBRepository;

    @Inject
    public EventsViewModel(DBRepository dBRepository) {
        this.dBRepository = dBRepository;
        this.eventsList = dBRepository.getAllEventsLiveData();
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
