package com.gmail.bukinmg.model.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.DateFormatSymbols;

@Entity(tableName = "event_table")
public class Event {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int distance;

    private int year;

    private int month;

    private int day;

    private String userEmail;

    public Event(int distance, int year, int month, int day, String userEmail) {
        this.distance = distance;
        this.year = year;
        this.month = month;
        this.day = day;
        this.userEmail = userEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getStringMonth() {
        return new DateFormatSymbols().getMonths()[month].toLowerCase();
    }
}
