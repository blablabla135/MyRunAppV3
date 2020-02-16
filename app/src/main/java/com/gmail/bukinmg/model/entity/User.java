package com.gmail.bukinmg.model.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String eMail;

    private String password;

    private int mainEventId;

    public User(String eMail, String password, int mainEventId) {
        this.eMail = eMail;
        this.password = password;
        this.mainEventId = mainEventId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEMail() {
        return eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMainEventId() {
        return mainEventId;
    }

    public void setMainEventId(int mainEventId) {
        this.mainEventId = mainEventId;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
}