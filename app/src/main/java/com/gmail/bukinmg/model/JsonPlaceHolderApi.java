package com.gmail.bukinmg.model;

import com.gmail.bukinmg.model.entity.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<Post>> getPosts();


}
