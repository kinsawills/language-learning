package com.model;

import com.narration.Narriator;

/**
 * Story class will have list of stories
 * 
 * @author Risha Patel
 */

public class Story {
    private String title;
    private String englishStory;
    private String spanishStory;
    private int currentStoryIndex;

    /**
     * Constructor for story class
     * 
     * @param title        The title of the story
     * @param englishStory The english translation of the story
     * @param spanishStory The spanish translation of the story
     */
    public Story(String title, String englishStory, String spanishStory) {
        this.title = title;
        this.englishStory = englishStory;
        this.spanishStory = spanishStory;
    }

    /**
     * Gets the title of the story
     * @return the title of the story
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the english story
     * @return the english story
     */
    public String getEnglishStory() {
        return englishStory;
    }

    /**
     * Gets the spanish story
     * @return the spanish story
     */
    public String getSpanishStory() {
        return spanishStory;
    }

    /**
     * Gets the current story's index
     * @return the index of the current story
     */
    public int getCurrentStoryIndex() {
        return this.currentStoryIndex;
    }

    /**
     * To string method in order to test the story
     */
    public String toString() {
        return "Title: " + this.title + "\n English Story: " + this.englishStory + "\n Spanish Story: "
                + this.spanishStory + "\n\n";
    }

    /**
     * Displays the story
     * @return Returns the story as a string
     */
    public String displayStory() {
        return spanishStory;
    }

    /**
     * Speaks the story
     */
    public void speakStory() {
        Narriator.playSound(spanishStory);
    }
}
