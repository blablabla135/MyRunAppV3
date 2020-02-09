package com.gmail.bukinmg.model;

import com.gmail.bukinmg.utill.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class Repository {

    private List<User> users = new ArrayList<>();
    private List<String> dates = new ArrayList<>();

    @Inject
    public Repository() {
        users.add(new User("1", "1"));
        users.add(new User("2", "2"));

        for (int i = 0; i < 45; i++) {
            dates.add(String.valueOf(i));
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public List<String> getDates() {
        return dates;
    }
}
