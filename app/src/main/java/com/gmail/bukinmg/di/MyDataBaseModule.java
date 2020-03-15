package com.gmail.bukinmg.di;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.gmail.bukinmg.model.dao.EventDao;
import com.gmail.bukinmg.model.dao.UserDao;
import com.gmail.bukinmg.model.MyDataBase;
import com.gmail.bukinmg.model.DBRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MyDataBaseModule {

    @Singleton
    @Provides
    public MyDataBase provideMyDataBase(@NonNull Application application) {
        return Room.databaseBuilder(application,
                MyDataBase.class, "my_database").fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    public UserDao provideUserDao(MyDataBase myDataBase) {
        return myDataBase.userDao();
    }

    @Singleton
    @Provides
    public EventDao provideEventDao(MyDataBase myDataBase) {
        return myDataBase.eventDao();
    }


    @Singleton
    @Provides
    public DBRepository repository(UserDao userDao, EventDao eventDao) {
        return new DBRepository(userDao, eventDao);
    }
}