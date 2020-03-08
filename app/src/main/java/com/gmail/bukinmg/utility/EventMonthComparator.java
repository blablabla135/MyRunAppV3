package com.gmail.bukinmg.utility;

import com.gmail.bukinmg.model.entity.Event;

import java.util.Comparator;

public class EventMonthComparator implements Comparator<Event> {
    @Override
    public int compare(Event o1, Event o2) {
        return Integer.compare(o2.getMonth(), o1.getMonth());
    }
}
