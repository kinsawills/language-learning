package com.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.model.Facade;
import com.model.Phrase;
import com.model.Word;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import com.language.App;
import java.io.IOException;

public class ProgressController implements Initializable {
    private boolean loggedIn = Facade.getInstance().login("c", "r");
    private int cardIndex = 0;
    private ArrayList<Phrase> problemPhrases = Facade.getInstance().getProblemProgress();

    @FXML
    private Button leftButton;

    @FXML
    private Text txt_englishPhrase;

    @FXML
    private Text txt_translatedPhrase;

    @FXML
    private VBox vbox_flashcard;

    @FXML
    void ReturnHome(ActionEvent event) throws IOException {
        App.setRoot("Welcome");
    }

    @FXML
    void nextCard(ActionEvent event) {
        displayNextCard();
    }

    @FXML
    void revealAnswer(ActionEvent event) {
        txt_translatedPhrase.setText(convertPhraseToString(problemPhrases.get(cardIndex), true));
    }

    public void initialize(URL url, ResourceBundle rb) {
        displayNextCard();
    }

    private void displayNextCard() {
        if (problemPhrases.isEmpty()) {
            txt_englishPhrase.setText("No problem phrases :)");
            txt_translatedPhrase.setText("Keep up the good work!");
        } else {
            cardIndex = (cardIndex + 1) % problemPhrases.size();
            Phrase firstCard = problemPhrases.get(cardIndex);
            txt_englishPhrase.setText(convertPhraseToString(firstCard, false));
            txt_translatedPhrase.setText("");
        }
    }

    private String convertPhraseToString(Phrase phrase, boolean isEnglish) {
        ArrayList<Word> phraseArr;
        StringBuilder str = new StringBuilder();
        if (isEnglish) {
            phraseArr = phrase.getEnglishPhrase();
            int len = phraseArr.size();

            for (int i = 0; i < len; i++) {
                if (str.length() > 0)
                    str.append(" ");
                str.append(phraseArr.get(i).getEnglishWord());
            }
        } else {
            phraseArr = phrase.getTranslatedPhrase();
            int len = phraseArr.size();

            for (int i = 0; i < len; i++) {
                if (str.length() > 0)
                    str.append(" ");
                str.append(phraseArr.get(i).getTranslatedWord());
            }
        }

        return str.toString();
    }

}
