package com.example.chatappjavafx.BaseClasses;

public class AdminUser extends User {
    private int adminLevel;

    public AdminUser(String username, String email, String password, String timestamp, int adminLevel) {
        super(username, email, password, timestamp);
        this.adminLevel = adminLevel;
    }
}
