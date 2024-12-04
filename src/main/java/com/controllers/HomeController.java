package com.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.language.App;
import com.model.Facade;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HomeController {
    @FXML
    private TextField txt_username;

    @FXML
    private TextField txt_password;

    @FXML
    private Label error_message;
    
    @FXML
    private void login(ActionEvent event) throws IOException {
        String username = txt_username.getText().toString();
        String password = txt_password.getText().toString();
        System.out.println(username + " " + password);

        Facade facade = Facade.getInstance();

        if (facade.login(username, password)) {
            App.setRoot("Welcome");
        } else {
            error_message.setText("Invalid credentials. Please try again.");
        }
    }
}
