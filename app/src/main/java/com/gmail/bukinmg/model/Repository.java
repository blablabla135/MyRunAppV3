package com.gmail.bukinmg.model;

import android.os.AsyncTask;
import android.util.Log;

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

    public void insert(User user) {
        new InsertUserAsyncTask(userDao).execute(user);
    }

    public void insert(Event event) {
        new InsertEventAsyncTask(eventDao).execute(event);
    }

    public void insert(MainEvent mainEvent) {
        new InsertMainEventAsyncTask(mainEventDao).execute(mainEvent);
    }

    public void update(User user) {
        new UpdateUserAsyncTask(userDao).execute(user);
    }

    public void update(Event event) {
        new UpdateEventAsyncTask(eventDao).execute(event);
    }

    public void update(MainEvent mainEvent) {
        new UpdateMainEventAsyncTask(mainEventDao).execute(mainEvent);
    }

    public void delete(User user) {
        new DeleteUserAsyncTask(userDao).execute(user);
    }

    public void delete(Event event) {
        new DeleteEventAsyncTask(eventDao).execute(event);
    }

    public void delete(MainEvent mainEvent) {
        new DeleteMainEventAsyncTask(mainEventDao).execute(mainEvent);
    }

    public LiveData<List<User>> getAllUsers() {
        return usersList;
    }

    public LiveData<List<Event>> getAllEvents() {
        return eventsList;
    }

    public LiveData<List<MainEvent>> getAllMainEvents() {
        return mainEventsList;
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

    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private UpdateUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.update(users[0]);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private DeleteUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.delete(users[0]);
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

    private static class UpdateEventAsyncTask extends AsyncTask<Event, Void, Void> {
        private EventDao eventDao;

        private UpdateEventAsyncTask(EventDao eventDao) {
            this.eventDao = eventDao;
        }

        @Override
        protected Void doInBackground(Event... events) {
            eventDao.update(events[0]);
            return null;
        }
    }

    private static class DeleteEventAsyncTask extends AsyncTask<Event, Void, Void> {
        private EventDao eventDao;

        private DeleteEventAsyncTask(EventDao eventDao) {
            this.eventDao = eventDao;
        }

        @Override
        protected Void doInBackground(Event... events) {
            eventDao.delete(events[0]);
            return null;
        }
    }

    private static class InsertMainEventAsyncTask extends AsyncTask<MainEvent, Void, Void> {
        private MainEventDao mainEventDao;

        private InsertMainEventAsyncTask(MainEventDao mainEventDao) {
            this.mainEventDao = mainEventDao;
        }

        @Override
        protected Void doInBackground(MainEvent... mainEvents) {
            mainEventDao.insert(mainEvents[0]);
            return null;
        }
    }

    private static class UpdateMainEventAsyncTask extends AsyncTask<MainEvent, Void, Void> {
        private MainEventDao mainEventDao;

        private UpdateMainEventAsyncTask(MainEventDao mainEventDao) {
            this.mainEventDao = mainEventDao;
        }

        @Override
        protected Void doInBackground(MainEvent... mainEvents) {
            mainEventDao.update(mainEvents[0]);
            return null;
        }
    }

    private static class DeleteMainEventAsyncTask extends AsyncTask<MainEvent, Void, Void> {
        private MainEventDao mainEventDao;

        private DeleteMainEventAsyncTask(MainEventDao mainEventDao) {
            this.mainEventDao = mainEventDao;
        }

        @Override
        protected Void doInBackground(MainEvent... mainEvents) {
            mainEventDao.delete(mainEvents[0]);
            return null;
        }
    }





}




