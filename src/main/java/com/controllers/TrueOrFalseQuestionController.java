package com.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import java.io.IOException;
import com.language.App;

public class TrueOrFalseQuestionController {

    @FXML
    private VBox Questions;

    @FXML
    private TextField txt_question;

    @FXML
    void returnHome(ActionEvent event) throws IOException {
        App.setRoot("welcome");
    }

    @FXML
    void fillInTheBlankQuestion(ActionEvent event) throws IOException {
        App.setRoot("fillInTheBlankQuestion");
    }

}