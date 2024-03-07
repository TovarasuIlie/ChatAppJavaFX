package com.example.chatappjavafx.Controllers;

import com.example.chatappjavafx.BaseClasses.OnlineUsers;
import com.example.chatappjavafx.BaseClasses.User;
import com.example.chatappjavafx.ChatWindow;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoginController implements EventHandler<ActionEvent> {
    private TextField usernameTextField;
    private TextField passwordTextField;

    public LoginController(TextField usernameTextField, TextField passwordTextField) {
        this.usernameTextField = usernameTextField;
        this.passwordTextField = passwordTextField;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        if(!username.isEmpty() && !password.isEmpty()) {
            MongoCollection mongoCollection = ControllerDatabase.getColletion("users");
            Document userData = (Document) mongoCollection.find(Filters.eq("username", username)).first();
            if(!userData.isEmpty()) {
                if(PasswordController.verifyPassword(password, userData.getString("password"))) {
                    LocalDateTime localDateTime = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedDateTime = localDateTime.format(formatter);

                    MongoCollection onlineUsersCollection = ControllerDatabase.getColletion("online-users");
                    Document onlineUser = new Document();
                    onlineUser.append("username", username);
                    onlineUser.append("onlineAt", formattedDateTime);
                    onlineUsersCollection.insertOne(onlineUser);

                    Node node = (Node) actionEvent.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    try {
                        String email = "adasda";
                        ChatWindow chatWindow = new ChatWindow(new User(username, email, password, formattedDateTime), new OnlineUsers(ControllerDatabase.fetchUsersOnline()));
                        chatWindow.start(stage);
                        stage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Eroare!");
                    alert.setHeaderText("Datele introduse nu corespund!");
                    alert.setContentText("Numele de utilizator sau parola nu se potrivesc!");
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Eroare!");
                alert.setHeaderText("Contul nu exista!");
                alert.setContentText("Contul cu numele de utilizator '" + username + "' nu exista!");
                alert.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Eroare!");
            alert.setHeaderText("Eroare Campuri Goale!");
            alert.setContentText("Toate campurile trebuie completate!");
            alert.show();
        }
    }
}
