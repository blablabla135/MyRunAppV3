package com.gmail.bukinmg.viewmodel;

import androidx.lifecycle.ViewModel;

import com.gmail.bukinmg.model.Entity.Day;
import com.gmail.bukinmg.model.Repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class MainMenuViewModel extends ViewModel {

    public List<Day> dates;
    private Repository repository;
    private Calendar mainCalendar;

    @Inject
    public MainMenuViewModel(Repository repository) {
        this.repository = repository;
        this.mainCalendar = Calendar.getInstance(Locale.ENGLISH);
        mainCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        dates = new ArrayList<>();
        initializeDates();
    }


    public void initializeDates() {
        dates.clear();
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
    }

}
