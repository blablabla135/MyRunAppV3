package com.gmail.bukinmg.model.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class MainEvent {

    private String name;

    private String date;

    public MainEvent(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
