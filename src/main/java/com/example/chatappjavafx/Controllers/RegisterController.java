package com.example.chatappjavafx.Controllers;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.bson.Document;

import java.util.regex.Pattern;

public class RegisterController implements EventHandler<ActionEvent> {

    private TextField emailTextField;
    private TextField usernameTextField;
    private TextField passwordTextField;
    private TextField confirmPasswordTextField;

    public RegisterController(TextField emailTextField, TextField usernameTextField, TextField passwordTextField, TextField confirmPasswordTextField) {
        this.emailTextField = emailTextField;
        this.usernameTextField = usernameTextField;
        this.passwordTextField = passwordTextField;
        this.confirmPasswordTextField = confirmPasswordTextField;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        String email = emailTextField.getText();
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String confrimPassword = confirmPasswordTextField.getText();

        if(!email.isEmpty() && !username.isEmpty() && !password.isEmpty() && !confrimPassword.isEmpty()) {
            if(password.equals(confrimPassword)) {
                Pattern p = Pattern.compile("[~!@#$%^&*()_+{}\\[\\]:;,.<>/?-]");
                if(!p.matcher(username).find()) {
                    p = Pattern.compile("^(.+)@(.+)$");
                    if(p.matcher(email).find()) {
                        MongoCollection usersCollection = ControllerDatabase.getColletion("users");
                        Document newUser = new Document();
                        newUser.append("username", username);
                        newUser.append("email", email);
                        newUser.append("password", PasswordController.hashPassword(password));
                        try {
                            usersCollection.insertOne(newUser);
                        } catch (MongoException mongoException) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Eroare");
                            alert.setHeaderText("");
                            alert.setContentText(mongoException.getMessage());
                            alert.show();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Eroare");
                        alert.setHeaderText("Eroare la Adresa de Email!");
                        alert.setContentText("Adresa de Email nu este valida. Te rugam sa mai verifici inca odata!");
                        alert.show();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Atentionare!");
                    alert.setHeaderText("Atentie la Nume Utilizator!");
                    alert.setContentText("Numele de utilizator contine caractere speciale. Te rugam sa mai verifici inca odata!");
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Atentionare!");
                alert.setHeaderText("Atentie la Parola!");
                alert.setContentText("Cele doua parole nu se potrivesc. Te rugam sa mai verifici inca odata!");
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
