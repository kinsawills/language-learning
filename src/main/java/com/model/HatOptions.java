package com.model;

/**
 * Enumeration for an avatar's hat options
 * 
 * @author Christian Ruff
 */
public enum HatOptions {
    SOMBRERO,
    CORDOBES,
    TXAPELA,
    NONE;

    public static HatOptions DEFAULT_HAT = HatOptions.NONE;

    /**
     * Accepts a string and returns the HatOptions enum that matches
     * 
     * @param str Exact name of the corresponding enum
     * @return Returns the corresponding enum if a match is found, ELSE returns
     *         default
     */
    public static HatOptions getValue(String str) {
        for (int i = 0; i < HatOptions.values().length; i++) {
            if (HatOptions.values()[i].toString().equals(str))
                return HatOptions.values()[i];
        }
        return DEFAULT_HAT;
    }
}
