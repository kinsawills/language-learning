package com.model;

public class EnumUtilities {

    /**
     * Gets the enumeration from a string
     * @param <T> requires the type of the enumeration
     * @param enumClass requires the class of the enumeration
     * @param str requires the string in order to get the enumeration
     * @return returns the enumeration
     */
    public static <T extends Enum<T>> T getEnumFromString(Class<T> enumClass, String str) {
        for (T enumConstant : enumClass.getEnumConstants()) {
            if (enumConstant.toString().equalsIgnoreCase(str)) {
                return enumConstant;
            }
        }
        return null;
    }

}
