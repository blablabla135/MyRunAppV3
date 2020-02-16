package com.gmail.bukinmg.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.gmail.bukinmg.model.entity.MainEvent;

import java.util.List;

@Dao
public interface MainEventDao {

    @Insert
    void insert(MainEvent mainEvent);

    @Update
    void update(MainEvent mainEvent);

    @Delete
    void delete(MainEvent mainEvent);

    @Query("SELECT * FROM main_event_table")
    LiveData<List<MainEvent>> getAllMainEvents();
}