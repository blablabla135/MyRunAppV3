package com.gmail.bukinmg.model.Entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String eMail;

    private String password;

    private long firstRunDate;

    private int mainEventId;

    public User(String eMail, String password, long firstRunDate, int mainEventId) {
        this.eMail = eMail;
        this.password = password;
        this.firstRunDate = firstRunDate;
        this.mainEventId = mainEventId;
    }

    public int getId() {
        return id;
    }

    public String getEMail() {
        return eMail;
    }

    public String getPassword() {
        return password;
    }

    public long getFirstRunDate() {
        return firstRunDate;
    }

    public int getMainEventId() {
        return mainEventId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstRunDate(long firstRunDate) {
        this.firstRunDate = firstRunDate;
    }

    public void setMainEventId(int mainEventId) {
        this.mainEventId = mainEventId;
    }
}