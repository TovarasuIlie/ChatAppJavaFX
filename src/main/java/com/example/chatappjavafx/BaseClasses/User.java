package com.example.chatappjavafx.BaseClasses;

public class User {
    private String username;
    private String email;
    private String password;
    private String timestamp;

    public User(String username, String email, String password, String timestamp) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.timestamp = timestamp;
    }

    public User(String username, String timestamp) {
        this.username = username;
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
