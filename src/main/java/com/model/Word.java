package com.model;

import java.util.UUID;

/**
 * @author Kevin Buie
 *         Creates a public word and stores things like the translation in order
 *         to create questions
 */
public class Word {
    private UUID id;
    private String englishWord;
    private String translatedWord;
    private String pronunciation;
    private PartOfSpeech partOfSpeech;
    private Gender gender;
    private int difficulty;

    /**
     * Takes all the information and parts of a word and stores them for later use
     * 
     * @param englishWord    Stores what the word is in english
     * @param translatedWord Stores what the word is in spanish
     * @param pronunciation  Stores the pronunciation of the word
     * @param partOfSpeech   Stores the part of speech of the word
     * @param gender         Stores the gender of the word
     */
    public Word(String englishWord, String translatedWord, String pronunciation, PartOfSpeech partOfSpeech,
            Gender gender, int difficulty) {
        this.id = UUID.randomUUID();
        this.englishWord = englishWord;
        this.translatedWord = translatedWord;
        this.pronunciation = " ";
        this.partOfSpeech = partOfSpeech.ADJECTIVE;
        this.gender = gender.NEITHER;
        this.difficulty = difficulty;
    }

    /**
     * Constructor for data loader
     * 
     * @author Christian Ruff
     * @param englishWord    takes in the english word
     * @param translatedWord takes in the translated word
     * @param pronunciation  takes in the pronunciation of the word
     * @param partOfSpeech   takes in the part of speech of the word
     * @param gender         takes in the gender of the word
     * @param difficulty     takes in the difficulty of the word
     */
    public Word(UUID id, String englishWord, String translatedWord, String pronunciation, PartOfSpeech partOfSpeech,
            Gender gender, int difficulty) {
        this.id = id;
        this.englishWord = englishWord;
        this.translatedWord = translatedWord;
        this.pronunciation = pronunciation;
        this.partOfSpeech = partOfSpeech;
        this.gender = gender;
        this.difficulty = difficulty;
    }

    /**
     * Gets the UUID for the word
     * 
     * @return ID in data type, UUID
     */
    public UUID getUUID() {
        return this.id;
    }

    /**
     * Gets what the word is in english
     * 
     * @return returns the english word
     */
    public String getEnglishWord() {
        return englishWord;
    }

    /**
     * Gets what the word is in spanish
     * 
     * @return returns the spanish word
     */
    public String getTranslatedWord() {
        return translatedWord;
    }

    /**
     * Gets the pronunciation of whatever word
     * 
     * @return returns how to say it
     */
    public String getPronunciation() {
        return pronunciation;
    }

    /**
     * Gets the part of speech that the word is (noun, pronoun, etc)
     * 
     * @return returns that part of speech
     */
    public PartOfSpeech getPartOfSpeech() {
        return partOfSpeech;
    }

    /**
     * Returns a formatted string for the word
     * @return String formatted (EN word) (translated word)
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nSpanish: ");
        sb.append(translatedWord != null ? translatedWord : "null");
        sb.append("\n");
        sb.append("English: ");
        sb.append(englishWord != null ? englishWord : "null");
        sb.append("\n\n");
        return sb.toString();
    }

    /**
     * Gets the difficulty of the word
     * 
     * @return returns the difficulty
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * Gets the gender of the word
     * @return returns the gender
     */
    public Gender getGender() {
        return this.gender;
    }
}
