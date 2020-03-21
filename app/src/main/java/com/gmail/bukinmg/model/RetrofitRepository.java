package com.gmail.bukinmg.model;

import com.gmail.bukinmg.model.entity.MainEvent;
import com.gmail.bukinmg.model.entity.Post;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitRepository {

    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Inject
    public RetrofitRepository(Retrofit retrofit) {
        this.jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
    }

    public List<MainEvent> getAllMainEvents() {
        List<MainEvent> mainEvents = new ArrayList<>();
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                List<Post> posts = response.body();

                for (Post post : posts) {
                    mainEvents.add(new MainEvent(post.getName(), post.getDate()));
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                List<MainEvent> mainEvents = new ArrayList<>();
                mainEvents.add(new MainEvent("Unknown event", "Unknown date"));

            }
        });
        return mainEvents;
    }
}
