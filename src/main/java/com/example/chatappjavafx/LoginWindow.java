package com.example.chatappjavafx;

import com.example.chatappjavafx.Controllers.LoginController;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.FileInputStream;

public class LoginWindow extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane mainContianer = new BorderPane();

        Pane leftContainer = new Pane();
        leftContainer.setMinWidth(400);
        leftContainer.setStyle("-fx-background-color: #FFF");

        Label titleLeftLabel = new Label("Autentificare in Cont");
        titleLeftLabel.setStyle("-fx-font-weight: bolder");
        titleLeftLabel.setFont(new Font(30));
        titleLeftLabel.setLayoutX(55);
        titleLeftLabel.setLayoutY(45);
        leftContainer.getChildren().add(titleLeftLabel);


        Label usernameLabel = new Label("Utilizatorul sau Email-ul");
        usernameLabel.setStyle("-fx-font-weight: bolder");
        usernameLabel.setLayoutX(110);
        usernameLabel.setLayoutY(180);
        leftContainer.getChildren().add(usernameLabel);

        TextField usernameTextField = new TextField();
        usernameTextField.setPromptText("Utilizatorul sau Email-ul");
        usernameTextField.setMinWidth(200);
        usernameTextField.setFocusTraversable(false);
        usernameTextField.setLayoutX(100);
        usernameTextField.setLayoutY(200);
        leftContainer.getChildren().add(usernameTextField);

        Label passwordLabel = new Label("Parola");
        passwordLabel.setStyle("-fx-font-weight: bolder");
        passwordLabel.setLayoutX(110);
        passwordLabel.setLayoutY(235);
        leftContainer.getChildren().add(passwordLabel);

        TextField passwordTextField = new TextField();
        passwordTextField.setPromptText("Parola");
        passwordTextField.setMinWidth(200);
        passwordTextField.setFocusTraversable(false);
        passwordTextField.setLayoutX(100);
        passwordTextField.setLayoutY(255);
        leftContainer.getChildren().add(passwordTextField);

        Button logInButton = new Button("Autentifica-te!");
        logInButton.setLayoutX(100);
        logInButton.setLayoutY(295);
        logInButton.getStyleClass().addAll("btn", "btn-success");
        logInButton.setFocusTraversable(false);
        logInButton.setOnAction(new LoginController(usernameTextField, passwordTextField));
        leftContainer.getChildren().add(logInButton);

        ButtonBar buttonBar = new ButtonBar();
        Button resetButton = new Button("Ti-ai uitat parola?");
        resetButton.getStyleClass().setAll("btn");
        resetButton.setFocusTraversable(false);
        Button registerButton = new Button("Creaza-ti Cont!");
        registerButton.setOnAction(actionEvent -> {
            Node node = (Node) actionEvent.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            try {
                RegisterWindow registerWindow = new RegisterWindow();
                registerWindow.start(stage);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        registerButton.getStyleClass().setAll("btn", "btn-info");
        registerButton.setFocusTraversable(false);
        ButtonBar.setButtonData(registerButton, ButtonBar.ButtonData.APPLY);
        ButtonBar.setButtonData(resetButton, ButtonBar.ButtonData.APPLY);
        buttonBar.getButtons().addAll(registerButton, resetButton);
        buttonBar.setLayoutY(400);
        leftContainer.getChildren().add(buttonBar);

        mainContianer.setRight(leftContainer);

        Pane rightContainer = new Pane();
        rightContainer.setMinWidth(400);
        rightContainer.setStyle("-fx-background-color: rgb(15, 87, 21)");

        Image logoImage = new Image(new FileInputStream("src/main/java/com/example/chatappjavafx/Logos/login-logo.png"));
        ImageView imageView = new ImageView(logoImage);
        imageView.setX(50);
        imageView.setY(120);
        imageView.setFitHeight(160);
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);
        rightContainer.getChildren().add(imageView);

        Label copyRightsLabel = new Label("© Copyright. All Rights Reseved | by Niculai Ilie-Traian ©");
        //copyRightsLabel.setStyle("-fx-text-fill: #FFF");
        copyRightsLabel.setTextFill(Paint.valueOf("FFF"));
        copyRightsLabel.setStyle("-fx-font-weight: bolder");
        copyRightsLabel.setLayoutX(40);
        copyRightsLabel.setLayoutY(410);
        rightContainer.getChildren().add(copyRightsLabel);

        mainContianer.setLeft(rightContainer);

        Scene scene = new Scene(mainContianer, 800, 450);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        primaryStage.setTitle("Demo Stage");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}