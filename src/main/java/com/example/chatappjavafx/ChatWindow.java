package com.example.chatappjavafx;

import com.example.chatappjavafx.BaseClasses.OnlineUsers;
import com.example.chatappjavafx.BaseClasses.User;
import com.example.chatappjavafx.Controllers.ControllerDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

public class ChatWindow extends Application {
    private User currentUser;
    private OnlineUsers onlineUsers;

    public ChatWindow(User currentUser, OnlineUsers onlineUsers) {
        this.currentUser = currentUser;
        this.onlineUsers = onlineUsers;
    }

    public ChatWindow() {
        this.currentUser = null;
        this.onlineUsers = null;
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane mainPane = new BorderPane();
        mainPane.setStyle("-fx-background-color: rgb(15, 87, 21)");

        VBox leftPanel = new VBox();
        leftPanel.setMinWidth(900);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(780, 400);
        scrollPane.setStyle("-fx-padding: 0 10 10 20; -fx-background-color: #FFF");

        VBox chatContentPanel = new VBox();
        chatContentPanel.setStyle("-fx-background-color: #FFF");
        VBox.setMargin(scrollPane, new Insets(20, 0, 0, 20));
        for(int i = 0; i < 10; i++) {
            VBox chatDisplay = new VBox();
            chatDisplay.setStyle("-fx-background-color: #FFF");
            VBox.setMargin(chatDisplay, new Insets(20, 0, 0, 0));
            chatDisplay.setMinWidth(800);
            Label username = new Label("Gica");
            username.setStyle("-fx-font-weight: bolder; -fx-font-size: 15");
            Label messageText = new Label("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse egestas, turpis at fermentum ultrices, odio ex lacinia erat, at iaculis nunc magna eu tortor. Vivamus interdum, velit id facilisis mattis, metus lectus gravida odio, in pharetra arcu lorem sed nisl. Phasellus dui tortor, imperdiet id lorem in, volutpat consectetur diam. Mauris egestas pharetra orci, at sagittis nibh feugiat in. Mauris non massa pretium, rutrum ipsum sit amet, porttitor nibh. Aenean dolor augue, lobortis eu sagittis sed, consequat sit amet augue. Fusce scelerisque laoreet risus sed ultrices. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.");
            messageText.setStyle("-fx-font-size: 14");
            messageText.setWrapText(true);
            messageText.setMaxWidth(837);
            chatDisplay.getChildren().addAll(username, messageText);
            chatContentPanel.getChildren().add(chatDisplay);
        }
        scrollPane.setContent(chatContentPanel);

        leftPanel.getChildren().add(scrollPane);

        HBox chatPane = new HBox();
        TextArea chatTextArea = new TextArea();
        chatTextArea.setPromptText("Scrie aici...");
        chatTextArea.setWrapText(true);
        chatTextArea.setFocusTraversable(false);
        chatTextArea.setMaxSize(800,150);
        HBox.setMargin(chatTextArea, new Insets(10, 0, 0 ,20));
        HBox.setHgrow(chatTextArea, Priority.ALWAYS);
        chatPane.getChildren().add(chatTextArea);

        Button sendButton = new Button("Trimite!");
        sendButton.getStyleClass().addAll("btn", "btn-lg", "btn-danger");
        HBox.setMargin(sendButton, new Insets(60, 20, 0 ,20));
        chatPane.getChildren().add(sendButton);

        leftPanel.getChildren().add(chatPane);

        mainPane.setLeft(leftPanel);

        VBox rightPanel = new VBox();
        rightPanel.setMinWidth(300);

        Label onlineUsersLabel = new Label("Utilizatori Online");
        onlineUsersLabel.setStyle("-fx-font-weight: bolder; -fx-font-size: 30");
        onlineUsersLabel.setTextFill(Paint.valueOf("FFF"));
        onlineUsersLabel.setTranslateX(30);
        VBox.setMargin(onlineUsersLabel, new Insets(10, 0, 0, 0));
        rightPanel.getChildren().add(onlineUsersLabel);

        ScrollPane usersScrollPane = new ScrollPane();
        usersScrollPane.setMaxHeight(510);
        usersScrollPane.setStyle("-fx-background-color: rgb(15, 87, 21)");
        VBox.setMargin(usersScrollPane, new Insets(0, 20, 0, 20));
        VBox usersContent = new VBox();
        usersContent.setBackground(new Background(new BackgroundFill(Color.rgb(15, 87, 21), CornerRadii.EMPTY, Insets.EMPTY)));


        //TO DO: sa se execute la fiecare actualizare a bazei de date

        for(int  i = 0; i < onlineUsers.getNumberOfUsers(); i++) {
            VBox userDisplay = new VBox();
            userDisplay.setBackground(new Background(new BackgroundFill(Color.rgb(174, 242, 180), CornerRadii.EMPTY, Insets.EMPTY)));
            userDisplay.setMinWidth(245);
            userDisplay.setStyle("-fx-backgroud-color: #F4F");
            Label username = new Label(onlineUsers.getUsernameOf(i));
            username.setStyle("-fx-font-weight: bolder; -fx-font-size: 15");
            Label onlineTime = new Label(onlineUsers.getTimestampOf(i));
            onlineTime.setStyle("-fx-font-weight: bolder");
            VBox.setMargin(userDisplay, new Insets(10, 0, 0, 0));
            userDisplay.setAlignment(Pos.CENTER);
            userDisplay.getChildren().addAll(username, onlineTime);
            usersContent.getChildren().add(userDisplay);
        }

        usersScrollPane.setContent(usersContent);
        rightPanel.getChildren().add(usersScrollPane);

        Label numberOfUsersLabel = new Label("Numar utilizatori online: 5");
        numberOfUsersLabel.setStyle("-fx-font-weight: bolder; -fx-font-size: 15");
        numberOfUsersLabel.setTextFill(Paint.valueOf("FFF"));
        numberOfUsersLabel.setTranslateX(30);
        VBox.setMargin(numberOfUsersLabel, new Insets(10, 0, 0, 0));
        rightPanel.getChildren().add(numberOfUsersLabel);

        mainPane.setRight(rightPanel);

        Scene scene = new Scene(mainPane, 1200, 600);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Chat Window");
        stage.setOnHidden(windowEvent -> {
            MongoCollection collection = ControllerDatabase.getColletion("online-users");
            collection.deleteMany(Filters.eq("username", currentUser.getUsername()));
        });
        stage.show();
    }
}
