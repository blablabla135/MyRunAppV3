package com.gmail.bukinmg.model.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "event_table")
public class Event {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int distance;

    private int year;

    private int month;

    private int day;

    private int userId;

    public Event(int distance, int year, int month, int day, int userId) {
        this.distance = distance;
        this.year = year;
        this.month = month;
        this.day = day;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public int getDistance() {
        return distance;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}