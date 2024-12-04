package com.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

import com.language.App;
import com.model.Facade;

public class ListeningQuestionController {

    @FXML
    private Button smallCloseButton;

    @FXML
    private Button largeCloseButton;

    private Facade facade;

    @FXML
    public void initialize() {
        facade = Facade.getInstance();
    }

    @FXML
    void ToWelcomeScreen(ActionEvent event) throws IOException {
        App.setRoot("Welcome");
    }

    @FXML
    void Logout(ActionEvent event) throws IOException {
        facade.logout();
        Platform.exit();
    }

    @FXML
    void writingQuestion(ActionEvent event) throws IOException {
        App.setRoot("WritingQuestion");
    }

    @FXML
    void returnHome(ActionEvent event) throws IOException {
        App.setRoot("welcome");
    }

}
