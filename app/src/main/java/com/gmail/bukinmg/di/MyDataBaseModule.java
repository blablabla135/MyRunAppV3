package com.gmail.bukinmg.di;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.gmail.bukinmg.model.Dao.EventDao;
import com.gmail.bukinmg.model.Dao.MainEventDao;
import com.gmail.bukinmg.model.Dao.UserDao;
import com.gmail.bukinmg.model.MyDataBase;
import com.gmail.bukinmg.model.Repository;

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
    public MainEventDao provideMainEventDao(MyDataBase myDataBase) {
        return myDataBase.mainEventDao();
    }

    @Singleton
    @Provides
    public Repository repository(UserDao userDao, EventDao eventDao, MainEventDao mainEventDao) {
        return new Repository(userDao, eventDao, mainEventDao);
    }
}