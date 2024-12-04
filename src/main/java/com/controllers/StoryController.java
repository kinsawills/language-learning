package com.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;

import com.language.App;
import com.model.Facade;
import com.model.Story;

public class StoryController {

    @FXML
    private Text txt_storyMode;

    @FXML
    private Text txt_storyTitle;

    @FXML
    private TextArea txt_englishStory;

    @FXML
    private Button revealButton;

    @FXML
    private Button playStoryButton;

    @FXML
    private Button smallCloseButton;

    @FXML
    private Button largeCloseButton;

    private Facade facade;
    private Story currentStory;

    @FXML
    public void initialize() {
        facade = Facade.getInstance();
        displayCurrentStory();
    }

    @FXML
    void RevealButton(ActionEvent event) {
        if (currentStory != null) {
            boolean isVisible = txt_englishStory.isVisible();
            txt_englishStory.setVisible(!isVisible);

            if (isVisible) {
                revealButton.setText("Reveal in English");
            } else {
                revealButton.setText("");
            }
        } else {
            txt_storyTitle.setText("");
        }
    }

    @FXML
    void PlayStory(ActionEvent event) throws IOException {
        System.out.println("Button clicked");
        if (currentStory != null) {
            currentStory.speakStory();
        }
    }

    private void displayCurrentStory() {
        if (facade.getCurrentUser() != null) {
            currentStory = facade.getCurrentUser()
                    .getUserProgress(facade.getCurrentLanguage())
                    .getCurrentStory();

            if (currentStory != null) {
                txt_storyTitle.setText(currentStory.getSpanishStory());
                txt_englishStory.setText(currentStory.getEnglishStory());
                txt_englishStory.setVisible(false);
                revealButton.setText("Reveal in English");
            } else {
                txt_storyTitle.setText("");
                txt_englishStory.setText("");
            }
        } else {
            txt_storyTitle.setText("");
            txt_englishStory.setText("");
        }
    }

    @FXML
    void SmallCloseButton(ActionEvent event) throws IOException {
        App.setRoot("Welcome");
    }

    @FXML
    void LargeCloseButton(ActionEvent event) throws IOException {
        facade.logout();
        Platform.exit();
    }
}
