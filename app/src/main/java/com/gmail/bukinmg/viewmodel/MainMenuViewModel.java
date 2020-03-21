package com.gmail.bukinmg.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gmail.bukinmg.model.DBRepository;
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
    private DBRepository dBRepository;
    private Calendar mainCalendar;
    private Day day;
    private String userEmail;
    private String mainEventDate;
    public MutableLiveData<EventWrapper<Boolean>> addTrigger = new MutableLiveData<>();
    public MutableLiveData<EventWrapper<Boolean>> cancelTrigger = new MutableLiveData<>();
    public MutableLiveData<EventWrapper<Boolean>> listTrigger = new MutableLiveData<>();
    public MutableLiveData<EventWrapper<Boolean>> menuTrigger = new MutableLiveData<>();

    @Inject
    public MainMenuViewModel(DBRepository dBRepository) {
        this.dBRepository = dBRepository;
        this.mainCalendar = Calendar.getInstance(Locale.ENGLISH);
        mainCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        distance.setValue("");
    }

    public void initializeDates() {
        month.setValue(mainCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH));

        List<Day> dates = new ArrayList<>();
        List<Event> events = dBRepository.getAllEvents();
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

        for (Day day : dates) {
            for (Event event : events) {
                if (day.getDay() == event.getDay() &&
                        day.getMonth() == event.getMonth() &&
                        day.getYear() == event.getYear() && userEmail.equals(event.getUserEmail())) {
                    day.setColor(3);
                }
            }

            Calendar date = Calendar.getInstance(Locale.ENGLISH);

            String [] arrayDate = mainEventDate.split("-");

            if (day.getDay() == Integer.parseInt(arrayDate[0]) &&
                    day.getMonth() == Integer.parseInt(arrayDate[1]) &&
                    day.getYear() == Integer.parseInt(arrayDate[2])) {
                day.setColor(2);
            }

            if (day.getDay() == date.get(Calendar.DAY_OF_MONTH) &&
            day.getMonth() == date.get(Calendar.MONTH) &&
            day.getYear() == date.get(Calendar.YEAR)) {
                day.setColor(1);
            }
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
        List<Event> events = dBRepository.getAllEvents();
        if (userDistance.equals("")) {
            distanceError.setValue("Enter distance");
        } else {
            for (Event event : events) {
                if (event.getDay() == day.getDay() && event.getMonth() == day.getMonth() && event.getYear() == day.getYear() &&event.getUserEmail().equals(userEmail)) {
                    dBRepository.delete(event);
                }
            }
            dBRepository.insert(new Event(Integer.parseInt(userDistance), day.getYear(), day.getMonth(), day.getDay(), userEmail));
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
        listTrigger.setValue(new EventWrapper<>(true));
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

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setMainEventDate(String mainEventDate) {
        this.mainEventDate = mainEventDate;
    }
}
