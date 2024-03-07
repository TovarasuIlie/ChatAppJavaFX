package com.example.chatappjavafx.BaseClasses;

import java.util.List;

public class OnlineUsers {
    private List<User> usersList;

    public OnlineUsers(List<User> usersList) {
        this.usersList = usersList;
    }

    public List<User> getUsersList() {
        return usersList;
    }

    public int getNumberOfUsers() {
        return usersList.size();
    }

    public String getUsernameOf(int id) {
        return usersList.get(id).getUsername();
    }

    public String getTimestampOf(int id) {
        return usersList.get(id).getTimestamp();
    }

    @Override
    public String toString() {
        return "OnlineUsers{" +
                "usersList=" + usersList.toString() +
                '}';
    }
}
