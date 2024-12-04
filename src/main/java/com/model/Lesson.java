package com.model;

import java.util.ArrayList;
import java.util.UUID;

import software.amazon.awssdk.services.polly.endpoints.internal.Value.Array;

/**
 * The Lesson class represents a lesson containing exercises and stories.
 * 
 * @author Risha Patel
 */
public class Lesson {
    private static int NUMBER_OF_TIMES_PHRASE_SEEN = 1;
    public static final int NUMBER_OF_QUESTIONS = 5;
    private UserProgress userProgress;
    private ArrayList<Question> questions;
    private int score;
    private Language language;

    /**
     * Creates a lesson based on the user progress
     * 
     * @param userProgress requires the progress in order for difficulty and
     *                     completed tasks
     */
    public Lesson(UserProgress userProgress, Language language) {
        this.userProgress = userProgress;
        this.language = language;
        this.questions = this.generateQuestions(NUMBER_OF_QUESTIONS, NUMBER_OF_TIMES_PHRASE_SEEN);
        this.score = 0;
    }

    public Lesson(UserProgress userProgress, Language language, int thresholdForTimesPhrasesSeen) {
        this.userProgress = userProgress;
        this.language = language;
        this.questions = this.generateQuestions(NUMBER_OF_QUESTIONS, thresholdForTimesPhrasesSeen);
        this.score = 0;
    }

    /**
     * Generates questions based on the user progress
     * 
     * @param numOfQuestions determiens how many questions to generate
     * @return returns the questions in a list
     */
    public ArrayList<Question> generateQuestions(int numOfQuestions, int threshold) {
        ArrayList<Phrase> allPhrases = language.getPhrases();
        ArrayList<Phrase> selectedPhrases = new ArrayList<Phrase>();
        int userDifficulty = userProgress.getDifficulty();

        // 1. pull a list of (NUMBER_OF_QUESTIONS) phrases that are within the user's
        // difficulty
        for (int i = 0; i < allPhrases.size() && selectedPhrases.size() < NUMBER_OF_QUESTIONS; i++) {
            Phrase phrase = allPhrases.get(i);
            if (phrase.getDifficulty() == userDifficulty
                    && userProgress.getPhraseProgress(phrase) < threshold)
                selectedPhrases.add(phrase);
        }

        // 2. generate all four question types
        ArrayList<Question> selectedQuestions = new ArrayList<Question>();
        for (int j = 0; j < selectedPhrases.size(); j++) {
            switch (j % 4) {
                case 0:
                    selectedQuestions.add(new MultipleChoiceQuestion(selectedPhrases.get(j), this.language));
                    break;
                case 1:
                    selectedQuestions.add(new ListeningQuestion(selectedPhrases.get(j)));
                    break;
                case 2:
                    selectedQuestions.add(new TrueFalseQuestion(selectedPhrases.get(j), this.language));
                    break;
                case 3:
                    selectedQuestions.add(new WritingQuestion(selectedPhrases.get(j)));
                    break;
            }
        }

        return selectedQuestions;
    }

    /**
     * gets the questions for the lesson
     * 
     * @return returns those questions
     */
    public ArrayList<Question> getQuestions() {
        return this.questions;
    }

    public void increaseScore() {
        this.score++;
    }

    public void decreaseScore() {
        this.score--;
    }

    /**
     * Gets the score to keep track of the user's progress
     * 
     * @return returns the users score based on number of questions answered
     *         correctly
     */
    public String getScore() {
        return score + " / " + questions.size();
    }
}
