module com.example.chatappjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires tyrus.client;
    requires tyrus.server;
    requires tyrus.websocket.core;
    requires javax.websocket.api;

    opens com.example.chatappjavafx to javafx.fxml;
    exports com.example.chatappjavafx;
}