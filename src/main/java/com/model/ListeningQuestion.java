package com.model;

import java.util.ArrayList;
import com.narration.Narriator;

/**
 * @author Kevin Buie
 *         Creates a Listening Question for the user
 */
public class ListeningQuestion implements Question {
    private Phrase phrase;
    private String question;

    /**
     * Creates a public listening question for the user
     * 
     * @param phrase needs a phrase in order to set up the question
     * @param id     needs the stored id in order to pull the specific stored
     *               variables
     */
    public ListeningQuestion(Phrase phrase) {
        this.phrase = phrase;
        this.generateQuestion();
    }

    /**
     * Generates the listening question
     */
    public void generateQuestion() {
        question = this.convertPhraseToString(this.phrase, false);
    }

    /**
     * Converts the phrase to a string
     * 
     * @param phrase    Takes the phrase in order to convert
     * @param isEnglish Takes a boolean to determine if the phrase is in English or
     *                  Spanish
     * @return Returns the phrase as a string
     */
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

    /**
     * Gets the question and joins the words together separated by spaces to make a
     * sentence
     * 
     * @return returns the new question to the user to be answered
     */
    public String getQuestion() {
        this.playAudio();
        return "Type the phrase in Spanish: ";
    }

    /**
     * Joins the answer phrase together and separates the words with spaces
     * 
     * @return returns the answer
     */
    public String getAnswer() {
        return this.question;
    }

    /**
     * Checks to see if the user answer is correct
     * 
     * @param answer requires the user answer in order to compare it to the actual
     *               answer
     * @return returns a boolean if the answer is correct or not
     */
    public boolean isCorrect(String answer) {
        return getAnswer().equals(answer);
    }

    /**
     * Plays the audio for the listening question
     */
    public void playAudio() {
        System.out.println("Listen to the following question");
        Narriator.playSound(question);
    }

    /**
     * Gets the phrase that the question is based on
     * 
     * @return Returns the phrase
     */
    public Phrase getPhrase() {
        return this.phrase;
    }
}
