package com.gmail.bukinmg.utility;

import com.gmail.bukinmg.model.entity.Event;

import java.util.Comparator;

public class EventDayComparator implements Comparator<Event> {
    @Override
    public int compare(Event o1, Event o2) {
        return Integer.compare(o2.getDay(), o1.getDay());
    }
}