package com.gmail.bukinmg.utill;

public class User {

    private String eMail;
    private String password;

    public User(String eMail, String password) {
        this.eMail = eMail;
        this.password = password;
    }

    public String getEMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
