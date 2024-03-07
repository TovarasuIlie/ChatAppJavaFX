package com.example.chatappjavafx;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

;

public class ConnectionMongoDB {
    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017/");
        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
        //mongoClient.listDatabaseNames().forEach(System.out::println);
        MongoCollection test =  mongoDatabase.getCollection("JavaTest");
        Document doc = new Document("name", "georgel");
        doc.append("_id", 1);
        doc.append("varsta", 69);
        test.insertOne(doc);

    }
}
