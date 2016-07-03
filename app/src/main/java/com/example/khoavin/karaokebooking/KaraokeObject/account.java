package com.example.khoavin.karaokebooking.KaraokeObject;

import com.google.gson.Gson;

public class account implements IObject {
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    int userid;
    String username;
    String password;

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    int userRole;
    public account() {
    }

    public account(String u, String p)
    {
        setUsername(u);
        setPassword(p);
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public account convertFromJson() {
        return null;
    }
}
