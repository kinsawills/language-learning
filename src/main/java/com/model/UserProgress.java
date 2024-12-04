package com.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Defines a class for user progress
 * @author christianruff
 */
public class UserProgress {
    private Language language;
    private int difficulty;
    private int currentStory;
    private HashMap<Phrase, Integer> phraseProgress;
    private HashMap<Word, Integer> wordProgress;

    /**
     * Creates a default user progress
     */
    public UserProgress(Language language) {
        this.language = language;
        this.difficulty = 1;
        this.currentStory = 0;
        this.phraseProgress = new HashMap<Phrase, Integer>();
        this.wordProgress = new HashMap<Word, Integer>();
    }

    /**
     * Creates a user progress with the parameters
     * @param difficulty     takes in the difficulty of the user
     * @param currentStory   takes in the current story of the user
     * @param phraseProgress takes in the phrase progress of the user
     * @param wordProgress   takes in the word progress of the user
     */
    public UserProgress(Language language, int difficulty, int currentStory, HashMap<Phrase, Integer> phraseProgress,
            HashMap<Word, Integer> wordProgress) {
        this.language = language;
        this.difficulty = difficulty;
        this.currentStory = currentStory;
        this.phraseProgress = phraseProgress;
        this.wordProgress = wordProgress;
    }

    /**
     * Gets the language
     * @return the language
     */
    public Language getLanguage() {
        return this.language;
    }

    /**
     * Gets the difficulty of the user
     * @return returns the difficulty
     */
    public int getDifficulty() {
        return this.difficulty;
    }

    /**
     * Gets the word progress of the user
     * @param word takes in the word to get the progress for
     * @return returns the progress
     */
    public HashMap<Word, Integer> getWordProgress() {
        return wordProgress;
    }

    /**
     * Gets the word progress
     * @param word the word to get the progress for
     * @return the progress
     */
    public int getWordProgress(Word word) {
        return this.wordProgress.getOrDefault(word, 0);
    }

    /**
     * Updates the word progress of the user
     * @param word takes in the word to update the progress for
     */
    public void updateWordProgress(Word word) {

    }

    /**
     * Gets the phrase progress of the user
     * @return returns the progress
     */
    public HashMap<Phrase, Integer> getPhraseProgress() {
        return phraseProgress;
    }

    /**
     * Gets the progress of a phrase
     * @param phrase The phrase to get the progress for
     * @return The progress of the phrase
     */
    public int getPhraseProgress(Phrase phrase) {
        return this.phraseProgress.getOrDefault(phrase, 0);
    }

    /**
     * Counts the correct phrase of the user
     * @param phrase takes in the phrase to count the progress for
     */
    public void countCorrectPhrase(Phrase phrase) {
        int currentCount = this.phraseProgress.getOrDefault(phrase, 0);
        phraseProgress.put(phrase, currentCount + 1);
    }

    /**
     * Counts the incorrect phrase of the user
     * @param phrase takes in the phrase to count the progress for
     */
    public void countIncorrectPhrase(Phrase phrase) {
        int currentCount = this.phraseProgress.getOrDefault(phrase, 0);
        phraseProgress.put(phrase, currentCount - 1);
    }

    /**
     * Gets the current story of the user
     * @return returns the current story
     */
    public Story getCurrentStory() {
        ArrayList<Story> stories = language.getStories();
        return stories.get(currentStory);
    }

    public int getCurrentStoryIndex() {
        return this.currentStory;
    }

    /**
     * Gets a string representation of the user progress
     * @return returns the string
     */
    public String toString() {
        return "Language: " + this.language + " | Difficulty: " + this.difficulty + " | Current Story: "
                + this.currentStory
                + " | Phrase Progress (Language:Score)" + this.phraseProgress
                + " | " + this.wordProgress;
    }

    /**
     * Gets the phrases that the user is struggling with
     * @return A string of phrases that the user is struggling with
     */
    public String displayHardPhrases() {
        StringBuilder hardPhrases = new StringBuilder();
        ArrayList<Phrase> phrases = this.getPhraseList();
        for (Phrase phrase : phrases) {
            if (this.phraseProgress.get(phrase) < 1) {
                hardPhrases.append(phrase.toString());
                hardPhrases.append(this.phraseProgressToString(phrase));
            }
        }
        return hardPhrases.toString();
    }

    /**
     * Gets the list of phrases that the user has seen
     * @return returns the list of phrases the user has seen
     */
    public ArrayList<Phrase> getPhraseList() {
        ArrayList<Phrase> phraseList = new ArrayList<>();
        for (Phrase phrase : this.phraseProgress.keySet()) {
            phraseList.add(phrase);
        }
        return phraseList;
    }

    /**
     * Gets the phrase progress as a string
     * @param phrase The phrase to get the progress for
     * @return The phrase progress as a string
     */
    public String phraseProgressToString(Phrase phrase) {
        return "| Current Score: " + this.getPhraseProgress(phrase) + "\n";
    }

}
