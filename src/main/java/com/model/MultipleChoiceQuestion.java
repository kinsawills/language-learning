package com.model;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * @author Kevin Buie
 *         Creates a Multiple Choice Question for the user
 */

public class MultipleChoiceQuestion implements Question {

    private final int NUMBER_OF_CHOICES = 3;
    private Language language; // reference for pulling similar questions, pass "current language"
    private Phrase phrase;
    private List<String> choices;
    private int correctAnswer;

    /**
     * Creates a multiple choice question
     * 
     * @param words         gives an array list of word options for the multiple
     *                      choice answers
     * @param correctAnswer assigns the correct answer for the problem
     * @param id            assigns the specific question an id so it can be reused
     */
    public MultipleChoiceQuestion(Phrase phrase, Language language) {
        this.phrase = phrase;
        this.language = language;
        this.choices = new ArrayList<>();
        generateQuestion();
    }

    /**
     * takes what the correct answer is and returns the translated word for the
     * question
     * 
     * @return returns the question
     */
    public String getQuestion() {
        StringBuilder question = new StringBuilder("Select the correct translation by typing the matching number for : \"");
        question.append(convertPhraseToString(phrase, false)).append("\"\n");
        for (int i = 0; i < choices.size(); i++) {
            question.append(i + 1).append(". ").append(choices.get(i)).append("\n");
        }

        return question.toString();
    }

    /**
     * Generates the multiple choice question
     */
    public void generateQuestion() {
        Random r = new Random();
        ArrayList<Phrase> allPhrases = language.getPhrases();

        String correctAnswerStr = convertPhraseToString(phrase, true);
        choices.add(correctAnswerStr);

        while (choices.size() < NUMBER_OF_CHOICES) {
            Phrase randomPhrase = allPhrases.get(r.nextInt(allPhrases.size()));
            String translatedStr = convertPhraseToString(randomPhrase, true);
            if (!choices.contains(translatedStr) && !randomPhrase.equals(phrase)) {
                choices.add(translatedStr);
            }
        }
        Collections.shuffle(choices);

        for (int i = 0; i < choices.size(); i++) {
            if (choices.get(i).equals(correctAnswerStr))
                this.correctAnswer = i + 1;
        }
    }

    /**
     * Converts the phrase to a string
     * @param phrase Takes the phrase in order to convert
     * @param isEnglish Tells weather the phrase is in english or not
     * @return the phrase as a string
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
     * Returns the correct answer
     * 
     * @return returns the correct answer
     */
    public String getAnswer() {
        return Integer.toString(correctAnswer);
    }

    /**
     * Checks to see if the answer is correct
     * 
     * @param answer requires the user answer in order to compare it to the actual
     *               answer
     * @return returns a boolean if the answer is correct or not
     */
    public boolean isCorrect(String answer) {
        return getAnswer().equals(answer);
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