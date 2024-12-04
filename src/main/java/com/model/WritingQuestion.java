package com.model;

import java.util.Random;

/**
 * @author Kevin Buie
 *         Creates a writing question for the user
 */
public class WritingQuestion implements Question {
    private Phrase phrase;
    private String questionString;
    private String answer;

    /**
     * Creates a default Writing Question with the parameters
     * 
     * @param phrase needs a phrase for the question
     * @param id     needs the id to pull the stored question
     */
    public WritingQuestion(Phrase phrase) {
        this.phrase = phrase;
        this.generateQuestion();
    }

    /**
     * Generates the question
     */
    public void generateQuestion() {
        Random r = new Random();

        // english phrase
        int phraseLength = phrase.getEnglishPhrase().size();
        StringBuilder englishStr = new StringBuilder();

        for (int i = 0; i < phraseLength; i++) {
            if (englishStr.length() > 0) {
                englishStr.append(" ");
            }
            englishStr.append(this.phrase.getEnglishPhrase().get(i).getEnglishWord());
        }

        // translated phrase
        phraseLength = phrase.getTranslatedPhrase().size();
        int answerIndex = r.nextInt(phraseLength);

        StringBuilder translatedStr = new StringBuilder();
        for (int i = 0; i < phraseLength; i++) {
            if (translatedStr.length() > 0) {
                translatedStr.append(" ");
            }
            if (i == answerIndex) {
                translatedStr.append("__________");
            } else {
                translatedStr.append(this.phrase.getTranslatedPhrase().get(i).getTranslatedWord());
            }
        }

        this.questionString = englishStr.toString() + " = " + translatedStr.toString();
        this.answer = this.phrase.getTranslatedPhrase().get(answerIndex).getTranslatedWord();
    }

    /**
     * joins the english phrases together and separates them by spaces
     * 
     * @return returns the new question to the user to be answered
     */
    public String getQuestion() {
        return this.questionString;
    }

    /**
     * joins the answer phrase together and separates the words with spaces
     *
     * @return returns the answer
     */
    public String getAnswer() {
        return this.answer;
    }

    /**
     * Checks to see if the user answer is correct
     * 
     * @param answer requires the user answer in order to compare it to the actual
     *               answer
     * @return returns a boolean if the answer is correct or not
     */
    public boolean isCorrect(String input) {
        return getAnswer().equals(input);
    }

    /**
     * The question as a string
     * @return the question
     */
    public String toString() {
        return "Question: " + this.questionString + " | Answer: " + this.answer;
    }

    /**
     * Gets the phrase that the question is based on
     * @return Returns the phrase
     */
    public Phrase getPhrase() {
        return this.phrase;
    }
}
