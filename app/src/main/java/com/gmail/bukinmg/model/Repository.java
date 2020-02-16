package com.gmail.bukinmg.model;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

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

    private LiveData<List<Event>> eventsList;
    private LiveData<List<MainEvent>> mainEventsList;


    @Inject
    public Repository(UserDao userDao, EventDao eventDao, MainEventDao mainEventDao) {
        this.userDao = userDao;
        this.eventDao = eventDao;
        this.mainEventDao = mainEventDao;
        eventsList = eventDao.getAllEvents();
        mainEventsList = mainEventDao.getAllMainEvents();
    }

    public void insert(User user) {
        new InsertUserAsyncTask(userDao).execute(user);
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
}




