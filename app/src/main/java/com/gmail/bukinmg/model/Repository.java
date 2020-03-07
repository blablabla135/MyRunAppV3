package com.gmail.bukinmg.model;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gmail.bukinmg.model.dao.EventDao;
import com.gmail.bukinmg.model.dao.MainEventDao;
import com.gmail.bukinmg.model.dao.UserDao;
import com.gmail.bukinmg.model.entity.Event;
import com.gmail.bukinmg.model.entity.MainEvent;
import com.gmail.bukinmg.model.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Repository {

    private UserDao userDao;
    private EventDao eventDao;
    private MainEventDao mainEventDao;


    @Inject
    public Repository(UserDao userDao, EventDao eventDao, MainEventDao mainEventDao) {
        this.userDao = userDao;
        this.eventDao = eventDao;
        this.mainEventDao = mainEventDao;
    }

    public void insert(User user) {
        new InsertUserAsyncTask(userDao).execute(user);
    }

    public void insert(Event event) {
        new InsertEventAsyncTask(eventDao).execute(event);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            users = new GetAllUsersAsyncTask(userDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return users;
    }

    public LiveData<List<Event>> getAllEvents() {
        LiveData<List<Event>> events = new MutableLiveData<>();
        try {
            events = new GetAllEventsAsyncTask(eventDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return events;
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }

    private static class InsertEventAsyncTask extends AsyncTask<Event, Void, Void> {
        private EventDao eventDao;

        private InsertEventAsyncTask(EventDao eventDao) {
            this.eventDao = eventDao;
        }

        @Override
        protected Void doInBackground(Event... events) {
            eventDao.insert(events[0]);
            return null;
        }
    }

    private static class GetAllUsersAsyncTask extends AsyncTask<Void, Void, List<User>> {
        private UserDao userDao;

        private GetAllUsersAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected List<User> doInBackground(Void... voids) {
            return userDao.getAllUsers();
        }
    }

    private static class GetAllEventsAsyncTask extends AsyncTask<Void, Void, LiveData<List<Event>>> {
        private EventDao eventDao;

        private GetAllEventsAsyncTask(EventDao eventDao) {
            this.eventDao = eventDao;
        }

        @Override
        protected LiveData<List<Event>> doInBackground(Void... voids) {
            return eventDao.getAllEvents();
        }
    }
}




