package com.gmail.bukinmg.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gmail.bukinmg.model.Repository;
import com.gmail.bukinmg.model.entity.Day;
import com.gmail.bukinmg.model.entity.Event;
import com.gmail.bukinmg.utility.EventWrapper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class MainMenuViewModel extends ViewModel {

    private MutableLiveData<List<Day>> datesList = new MutableLiveData<>();
    public MutableLiveData<String> month = new MutableLiveData<>();
    public MutableLiveData<String> distance = new MutableLiveData<>();
    public MutableLiveData<String> distanceError = new MutableLiveData<>();
    private Repository repository;
    private Calendar mainCalendar;
    private Day day;
    public MutableLiveData<EventWrapper<Boolean>> addTrigger = new MutableLiveData<>();
    public MutableLiveData<EventWrapper<Boolean>> cancelTrigger = new MutableLiveData<>();
    public MutableLiveData<EventWrapper<Boolean>> listTrigger = new MutableLiveData<>();
    public MutableLiveData<EventWrapper<Boolean>> menuTrigger = new MutableLiveData<>();

    @Inject
    public MainMenuViewModel(Repository repository) {
        this.repository = repository;
        this.mainCalendar = Calendar.getInstance(Locale.ENGLISH);
        mainCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        distance.setValue("");
        initializeDates();
    }

    public void initializeDates() {
        month.setValue(mainCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH));

        List<Day> dates = new ArrayList<>();
        Calendar datesCalendar = (Calendar) mainCalendar.clone();

        datesCalendar.set(Calendar.DAY_OF_MONTH, 1);
        int firstDayOfMonth = datesCalendar.get(Calendar.DAY_OF_WEEK) - 1;
        datesCalendar.add(Calendar.DAY_OF_MONTH, -(firstDayOfMonth - 1));

        for (int i = 0; i < 42; i++) {
            dates.add(new Day(datesCalendar.get(Calendar.DAY_OF_MONTH)
                    , datesCalendar.get(Calendar.MONTH)
                    , datesCalendar.get(Calendar.YEAR)));
            datesCalendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        datesList.setValue(dates);
    }

    public void nextMonth() {
        mainCalendar.add(Calendar.MONTH, 1);
        initializeDates();
    }

    public void previousMonth() {
        mainCalendar.add(Calendar.MONTH, -1);
        initializeDates();
    }

    public void onAddClick() {
        String userDistance = distance.getValue();
        if (userDistance.equals("")) {
            distanceError.setValue("Enter distance");
        } else {
            repository.insert(new Event(Integer.parseInt(userDistance), day.getYear(), day.getMonth(), day.getDay(), 1));
            distance.setValue("");
            distanceError.setValue(null);
            addTrigger.setValue(new EventWrapper<>(true));
        }

    }

    public void onCancelClick() {
        cancelTrigger.setValue(new EventWrapper<>(true));
        distanceError.setValue(null);
    }

    public void onListClick() {

    }

    public void onMenuClick() {

    }

    public MutableLiveData<List<Day>> getDatesList() {
        return datesList;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Day getDay() {
        return day;
    }
}
