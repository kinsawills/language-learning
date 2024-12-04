package com.model;

/**
 * @author kinsawills
 */
import java.util.ArrayList;
import java.util.UUID;

/**
 * The LanguageList class contains a list of languages
 */
public class LanguageList {
    private static LanguageList languageList;
    private ArrayList<Language> languages;

    /**
     * Creates a default language list
     */
    private LanguageList() {
        languages = DataLoader.getLanguages();
    }

    public ArrayList<Language> getLanguages() {
        return languages;
    }

    /**
     * Gets the instance of the language list
     * 
     * @return returns the instance of the language list
     */
    public static LanguageList getInstance() {
        if (languageList == null)
            languageList = new LanguageList();
        return languageList;
    }

    /**
     * Gets the array list of languages
     * 
     * @return returns the array list of languages
     */
    public Language getLanguageByUUID(UUID id) {
        for (Language language : languages) {
            if (language.getUUID().equals(id))
                return language;
        }
        return null;
    }
}
