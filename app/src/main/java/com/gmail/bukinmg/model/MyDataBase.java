package com.gmail.bukinmg.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.gmail.bukinmg.model.Dao.EventDao;
import com.gmail.bukinmg.model.Dao.MainEventDao;
import com.gmail.bukinmg.model.Dao.UserDao;
import com.gmail.bukinmg.model.Entity.Event;
import com.gmail.bukinmg.model.Entity.MainEvent;
import com.gmail.bukinmg.model.Entity.User;

@Database(entities = {User.class, Event.class, MainEvent.class}, version = 1, exportSchema = false)
public abstract class MyDataBase extends RoomDatabase {


    public abstract UserDao userDao();

    public abstract EventDao eventDao();

    public abstract MainEventDao mainEventDao();

}
