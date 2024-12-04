package com.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

import java.io.IOException;

import com.language.App;

public class fillInTheBlankQuestionController {

    @FXML
    void progressScreen(ActionEvent event) throws IOException{
        App.setRoot("progress");
    }

    @FXML
    void returnHome(ActionEvent event) throws IOException{
        App.setRoot("welcome");
    }

}