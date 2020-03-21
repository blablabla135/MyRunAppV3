package com.gmail.bukinmg.model;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gmail.bukinmg.model.dao.EventDao;
import com.gmail.bukinmg.model.dao.UserDao;
import com.gmail.bukinmg.model.entity.Event;
import com.gmail.bukinmg.model.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DBRepository {

    private UserDao userDao;
    private EventDao eventDao;
    private LiveData<List<Event>> eventsLiveData;
    private List<Event> events;



    @Inject
    public DBRepository(UserDao userDao, EventDao eventDao) {
        this.userDao = userDao;
        this.eventDao = eventDao;
    }

    public void insert(User user) {
        new InsertUserAsyncTask(userDao).execute(user);
    }

    public void insert(Event event) {
        new InsertEventAsyncTask(eventDao).execute(event);
    }

    public void delete(Event event) {
        new DeleteEventAsyncTask(eventDao).execute(event);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            users = new GetAllUsersAsyncTask(userDao).execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        try {
            events = new GetAllEventsAsyncTask(eventDao).execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return events;
    }

    public LiveData<List<Event>> getAllLiveEvents() {
        LiveData<List<Event>> events = new LiveData<List<Event>>() {
        };
        try {
            events = new GetAllLiveEventsAsyncTask(eventDao).execute().get();
        } catch (ExecutionException | InterruptedException e) {
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

    private static class GetAllEventsAsyncTask extends AsyncTask<Void, Void, List<Event>> {
        private EventDao eventDao;

        private GetAllEventsAsyncTask(EventDao eventDao) {
            this.eventDao = eventDao;
        }

        @Override
        protected List<Event> doInBackground(Void... voids) {
            return eventDao.getAllEvents();
        }
    }

    private static class GetAllLiveEventsAsyncTask extends AsyncTask<Void, Void, LiveData<List<Event>>> {
        private EventDao eventDao;

        private GetAllLiveEventsAsyncTask(EventDao eventDao) {
            this.eventDao = eventDao;
        }

        @Override
        protected LiveData<List<Event>> doInBackground(Void... voids) {
            return eventDao.getAllEventsLiveData();
        }
    }

}




