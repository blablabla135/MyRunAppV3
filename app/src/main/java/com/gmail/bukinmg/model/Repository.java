package com.gmail.bukinmg.model;

import androidx.lifecycle.LiveData;

import com.gmail.bukinmg.model.Dao.EventDao;
import com.gmail.bukinmg.model.Dao.MainEventDao;
import com.gmail.bukinmg.model.Dao.UserDao;
import com.gmail.bukinmg.model.Entity.Event;
import com.gmail.bukinmg.model.Entity.MainEvent;
import com.gmail.bukinmg.model.Entity.User;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Repository {

    private UserDao userDao;
    private EventDao eventDao;
    private MainEventDao mainEventDao;

    private LiveData<List<User>> usersList;
    private LiveData<List<Event>> eventsList;
    private LiveData<List<MainEvent>> mainEventsList;


    @Inject
        public Repository(UserDao userDao, EventDao eventDao, MainEventDao mainEventDao) {
            this.userDao = userDao;
            this.eventDao = eventDao;
            this.mainEventDao = mainEventDao;
            usersList = userDao.getAllUsers();
            eventsList = eventDao.getAllEvents();
            mainEventsList = mainEventDao.getAllMainEvents();
        }

    public LiveData<List<User>> getUsersList() {
        return usersList;
    }

    public LiveData<List<Event>> getEventsList() {
        return eventsList;
    }

    public LiveData<List<MainEvent>> getMainEventsList() {
        return mainEventsList;
    }
}




