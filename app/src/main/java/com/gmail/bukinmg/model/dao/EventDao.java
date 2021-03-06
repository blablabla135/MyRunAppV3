package com.gmail.bukinmg.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.gmail.bukinmg.model.entity.Event;

import java.util.List;

@Dao
public interface EventDao {

    @Insert
    void insert(Event event);

    @Update
    void update(Event event);

    @Delete
    void delete(Event event);

    @Query("SELECT * FROM event_table")
    LiveData<List<Event>> getAllEventsLiveData();

    @Query("SELECT * FROM event_table")
    List<Event> getAllEvents();


}