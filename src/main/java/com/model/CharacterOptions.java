package com.model;

/**
 * Enumeration for an avatar's character options
 * 
 * @author Christian Ruff
 */
public enum CharacterOptions {
    LLAMA,
    GIRAFFE,
    LIZARD;

    public static CharacterOptions DEFAULT_CHARACTER = CharacterOptions.LLAMA;

    /**
     * Accepts a string and returns the CharacterOption enum that matches
     * 
     * @param str Exact name of the corresponding enum
     * @return Returns the corresponding enum if a match is found, ELSE returns
     *         default
     */
    public static CharacterOptions getValue(String str) {
        for (int i = 0; i < CharacterOptions.values().length; i++) {
            if (CharacterOptions.values()[i].toString().equals(str))
                return CharacterOptions.values()[i];
        }
        return DEFAULT_CHARACTER;
    }
}
