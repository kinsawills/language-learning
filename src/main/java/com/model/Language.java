package com.model;

/**
 * @author kinsawills
 */
import java.util.ArrayList;
import java.util.UUID;

/**
 * The language class contains lists of words, phrases, and stories in order to
 * create a language
 */
public class Language {
    private UUID id;
    private String nameOfLanguage;
    private ArrayList<Word> words;
    private ArrayList<Phrase> phrases;
    private ArrayList<Story> stories;

    /**
     * Constructor for language class
     * 
     * @param id             requires the id in order to create the language
     * @param nameOfLanguage requires the name of the language
     * @param words          requires the array list of words in order to create the
     *                       language
     * @param phrases        requires the array list of phrases in order to create
     *                       the language
     */
    public Language(UUID id, String nameOfLanguage, ArrayList<Word> words,
            ArrayList<Phrase> phrases, ArrayList<Story> stories) {
        this.id = id;
        this.nameOfLanguage = nameOfLanguage;
        this.words = words;
        this.phrases = phrases;
        this.stories = stories;
    }

    /**
     * Gets the array list of phrases
     * 
     * @return returns the array list of phrases
     */
    public ArrayList<Phrase> getPhrases() {
        return phrases;
    }

    /**
     * Gets the array list of words for the language
     * 
     * @return the array list of words
     */
    public ArrayList<Word> getWords() {
        return words;
    }

    /**
     * Gets the name of the language
     * 
     * @return the name of the language
     */
    public String getLanguage() {
        return nameOfLanguage;
    }

    /**
     * Gets the UUID of the language
     * 
     * @return returns the UUID of the language
     */
    public UUID getUUID() {
        return id;
    }

    /**
     * Gets the array list of stories
     * 
     * @return returns the array list of stories
     */
    public ArrayList<Story> getStories() {
        return stories;
    }

    /**
     * Override toString to return the language as a string
     */
    public String toString() {
        return "ID: " + this.id + "\n  Language: " + this.nameOfLanguage + "\n  Words: " + this.words + "\n  Phrases: "
                + this.phrases + "\n  Stories: " + this.stories;
    }
}
