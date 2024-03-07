package com.example.chatappjavafx.Controllers;

import com.example.chatappjavafx.BaseClasses.User;
import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ControllerDatabase {
    public static MongoCollection getColletion(String collectionName) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/");
        MongoDatabase mongoDatabase = mongoClient.getDatabase("chat-project");
        return mongoDatabase.getCollection(collectionName);
    }

    public static List<User> fetchUsersOnline() {
        MongoCollection collection = getColletion("online-users");
        FindIterable<Document> usersIterable = collection.find();
        Iterator it = usersIterable.iterator();
        List<User> usersList = new ArrayList<>();
        while (it.hasNext()) {
            Document user = (Document) it.next();
            if (user.isEmpty()) {
                return null;
            } else {
                User user1 = new User(user.getString("username"), user.getString("onlineAt"));
                usersList.add(user1);
            }
        }
        return usersList;
    }

}
