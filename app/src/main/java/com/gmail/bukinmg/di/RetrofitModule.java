package com.gmail.bukinmg.di;

import com.gmail.bukinmg.model.RetrofitRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {

    @Singleton
    @Provides
    public Retrofit provideRetrofit(GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/blablabla135/JSON")
                .addConverterFactory(gsonConverterFactory)
                .build();
    }


    @Singleton
    @Provides
    public RetrofitRepository retrofitRepository(Retrofit retrofit) {
        return new RetrofitRepository(retrofit);
    }

    @Provides
    public Gson gson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    public GsonConverterFactory gsonConverterFactory(Gson gson){
        return GsonConverterFactory.create(gson);
    }

}
