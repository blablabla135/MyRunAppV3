package com.gmail.bukinmg.model.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String eMail;

    private String password;

    private String mainEventName;

    private String mainEventDate;

    public User(String eMail, String password, String mainEventName, String mainEventDate) {
        this.eMail = eMail;
        this.password = password;
        this.mainEventName = mainEventName;
        this.mainEventDate = mainEventDate;
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

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getMainEventName() {
        return mainEventName;
    }

    public void setMainEventName(String mainEventName) {
        this.mainEventName = mainEventName;
    }

    public String getMainEventDate() {
        return mainEventDate;
    }

    public void setMainEventDate(String mainEventDate) {
        this.mainEventDate = mainEventDate;
    }
}