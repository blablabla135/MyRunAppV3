package com.gmail.bukinmg.model;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gmail.bukinmg.model.dao.EventDao;
import com.gmail.bukinmg.model.dao.UserDao;
import com.gmail.bukinmg.model.entity.Event;
import com.gmail.bukinmg.model.entity.MainEvent;
import com.gmail.bukinmg.model.entity.Post;
import com.gmail.bukinmg.model.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class Repository {

    private UserDao userDao;
    private EventDao eventDao;
    private JsonPlaceHolderApi jsonPlaceHolderApi;


    @Inject
    public Repository(UserDao userDao, EventDao eventDao, RetrofitClient retrofitClient) {
        this.userDao = userDao;
        this.eventDao = eventDao;
        this.jsonPlaceHolderApi = retrofitClient.getJsonPlaceHolderApi();
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

    public LiveData<List<Event>> getAllEventsLiveData() {
        LiveData<List<Event>> events = new MutableLiveData<>();
        try {
            events = new GetAllEventsAsyncTaskLiveData(eventDao).execute().get();
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

    private static class GetAllEventsAsyncTaskLiveData extends AsyncTask<Void, Void, LiveData<List<Event>>> {
        private EventDao eventDao;

        private GetAllEventsAsyncTaskLiveData(EventDao eventDao) {
            this.eventDao = eventDao;
        }

        @Override
        protected LiveData<List<Event>> doInBackground(Void... voids) {
            return eventDao.getAllEventsLiveData();
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

    public MutableLiveData<List<MainEvent>> getAllMainEvents() {
        MutableLiveData<List<MainEvent>> mainEventsListLiveData = new MutableLiveData<>();
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                List<MainEvent> mainEvents = new ArrayList<>();
                List<Post> posts = response.body();

                for (Post post : posts) {
                    String date = post.getDate();
                    String [] subStr;
                    subStr = date.split("-");
                    mainEvents.add(new MainEvent(post.getName(), Integer.parseInt(subStr[2]), Integer.parseInt(subStr[1]), Integer.parseInt(subStr[0])));
                }
                mainEventsListLiveData.setValue(mainEvents);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                List<MainEvent> mainEvents = new ArrayList<>();
                mainEvents.add(new MainEvent("0000", 0, 0, 0));
                mainEventsListLiveData.setValue(mainEvents);
            }
        });
        return mainEventsListLiveData;
    }
}




