package com.model;

/**
 * DataConstants class contains constants that are used in the DataLoader and
 * DataWriter classes to read and write data
 */

public abstract class DataConstants {
    protected static final String USER_FILE_NAME = "speaking/src/main/java/com/data/user.json";
    protected static final String USER_FILE_NAME_JSON = "speaking/src/test/resources/data/user.json";
    protected static final String USER_ID = "uuid";
    protected static final String USER_FIRST_NAME = "firstName";
    protected static final String USER_LAST_NAME = "lastName";
    protected static final String USER_USERNAME = "username";
    protected static final String USER_EMAIL = "emailAddress";
    protected static final String USER_BIRTHDAY = "birthday";
    protected static final String USER_AVATAR = "avatar";
    protected static final String CHARACTER = "character";
    protected static final String HAT = "hat";
    protected static final String LANGUAGES = "languages";
    protected static final String FRIENDS = "friends";
    protected static final String USER_POINTS = "points";
    protected static final String CURRENT_LESSON = "currentLesson";
    protected static final String CURRENT_EXERCISE = "currentExercise";
    protected static final String USER_PASSWORD = "password";

    protected static final String LANGUAGE_FILE_NAME = "speaking/src/main/java/com/data/language.json";
    protected static final String LANGUAGE_FILE_NAME_JSON = "speaking/src/test/resources/data/language.json";
    protected static final String LANGUAGE_ID = "uuid";
    protected static final String LANGUAGE_NAME = "nameOfLanguage";
    protected static final String LANGUAGE_WORDS = "words";
    protected static final String LANGUAGE_PHRASES = "phrases";
    protected static final String LANGUAGE_STORIES = "stories";

    // Stories
    protected static final String STORY_TITLE = "title";
    protected static final String STORY_ENGLISHSTORY = "englishStory";
    protected static final String STORY_SPANISHSTORY = "spanishStory";

    protected static final String WORD_ID = "uuid";
    protected static final String WORD_INENGLISH = "englishWord";
    protected static final String WORD_INTARGETLANGUAGE = "translatedWord";
    protected static final String WORD_PRONUNCIATION = "pronunciation";
    protected static final String WORD_GENDER = "gender";
    protected static final String WORD_PARTOFSPEECH = "partOfSpeech";
    protected static final String WORD_DIFFICULTY = "difficulty";

    // Phrase
    protected static final String PHRASE_ID = "uuid";
    protected static final String PHRASE_IN_ENGLISH = "englishPhrase";
    protected static final String PHRASE_IN_TARGET_LANGUAGE = "translatedPhrase";
    protected static final String PHRASE_DIFFICULTY = "difficulty";

    // User progress
    protected static final String USERPROGRESS = "userProgress";
    protected static final String USERPROGRESS_LANGUAGE = "language";
    protected static final String USERPROGRESS_DIFFICULTY = "difficulty";
    protected static final String USERPROGRESS_CURRENTSTORY = "currentStory";
    protected static final String USERPROGRESS_PHRASEPROGRESS = "phraseProgress";
    protected static final String USERPROGRESS_PHRASEPROGRESS_PHRASE = "phrase";
    protected static final String USERPROGRESS_PHRASEPROGRESS_INTEGER = "integer";
    protected static final String USERPROGRESS_WORDPROGRESS = "wordProgress";
    protected static final String USERPROGRESS_WORDPROGRESS_WORD = "word";
    protected static final String USERPROGRESS_WORDPROGRESS_INTEGER = "integer";

    protected static final String EXERCISE_INDEX = "index";

    public static boolean isJUnitTest() {  
		for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
		  if (element.getClassName().startsWith("org.junit.")) {
			return true;
		  }           
		}
		return false;
	  }
}
