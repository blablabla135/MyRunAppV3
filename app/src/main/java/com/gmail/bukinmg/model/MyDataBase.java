package com.gmail.bukinmg.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.gmail.bukinmg.model.dao.EventDao;
import com.gmail.bukinmg.model.dao.UserDao;
import com.gmail.bukinmg.model.entity.Event;
import com.gmail.bukinmg.model.entity.User;

@Database(entities = {User.class, Event.class}, version = 1, exportSchema = false)
public abstract class MyDataBase extends RoomDatabase {


    public abstract UserDao userDao();

    public abstract EventDao eventDao();
}
