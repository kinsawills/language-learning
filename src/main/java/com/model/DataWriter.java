package com.model;

import java.text.SimpleDateFormat;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * DataWriter class saves list of registered users and their progress
 * 
 * @author Risha Patel
 */

public class DataWriter extends DataConstants {

    /**
     * Saves the users to the json file
     * 
     * @param users - ArrayList of users
     * @return - boolean representing the success of the operation
     */
    @SuppressWarnings("unchecked")
    public static boolean saveUsers(ArrayList<User> users) {
        UserList user = UserList.getInstance();
        ArrayList<User> userList = user.getUsers();
        JSONArray usersJSON = new JSONArray();
        for (User u : userList) {
            usersJSON.add(createUserJSON(u));
        }

        try (FileWriter file = new FileWriter(
                "C:\\Users\\risha\\CSCE247-LanguageLearningApp-Final\\speaking\\src\\main\\java\\com\\data\\user.json")) {
            file.write(usersJSON.toJSONString());
            file.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Creates a JSON object from a user
     * 
     * @param user - User object
     * @return - JSON object representing the user
     */
    @SuppressWarnings("unchecked")
    private static JSONObject createUserJSON(User user) {
        JSONObject userJSON = new JSONObject();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        userJSON.put(USER_ID, user.getUUID().toString());
        userJSON.put(USER_FIRST_NAME, user.getFirstName());
        userJSON.put(USER_LAST_NAME, user.getLastName());
        userJSON.put(USER_USERNAME, user.getUsername());
        userJSON.put(USER_PASSWORD, user.getPassword());
        userJSON.put(USER_EMAIL, user.getEmailAddress());
        userJSON.put(USER_BIRTHDAY, simpleDate.format(user.getBirthday()));
        userJSON.put(FRIENDS, user.getFriends());
        userJSON.put(USERPROGRESS, user.getUserProgress());
        userJSON.put(USER_POINTS, user.getPoints());

        // Avatar JSON
        JSONObject avatarJSON = new JSONObject();
        avatarJSON.put(CHARACTER, user.getAvatar().getCharacter().toString());
        avatarJSON.put(HAT, user.getAvatar().getHat().toString());
        userJSON.put(USER_AVATAR, avatarJSON);

        // Friends JSON
        JSONArray friendsArray = new JSONArray();
        for (User friend : user.getFriends()) {
            friendsArray.add(friend.getUUID().toString());
        }
        userJSON.put(FRIENDS, friendsArray);

        // User Progress JSON
        JSONArray userProgressArray = new JSONArray();
        for (UserProgress progress : user.getUserProgress()) {
            userProgressArray.add(createProgressJSON(progress));
        }
        userJSON.put(USERPROGRESS, userProgressArray);
        userJSON.put(USER_POINTS, user.getPoints());

        return userJSON;
    }

    /**
     * Saves the user progress to the json file
     * 
     * @param userProgress - ArrayList of user progress objects
     * @return - boolean representing that the user progress was saved successfully
     */
    @SuppressWarnings("unchecked")
    public static boolean saveUserProgress(ArrayList<UserProgress> userProgress) {
        JSONArray userProgressJSON = new JSONArray();
        for (UserProgress progress : userProgress) {
            userProgressJSON.add(createProgressJSON(progress));
        }
        // return userProgressJSON;
        String path = getFileWritingPath(USER_FILE_NAME, USER_FILE_NAME_JSON);
        try (FileWriter file = new FileWriter(path)) {
            file.write(userProgressJSON.toJSONString());
            file.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Converts UserProgress object to JSON format
     * 
     * @param progress - UserProgress object
     * @return - JSON object representing the user progress
     */
    @SuppressWarnings("unchecked")
    private static JSONObject createProgressJSON(UserProgress progress) {
        JSONObject progressJSON = new JSONObject();
        progressJSON.put(USERPROGRESS_LANGUAGE, progress.getLanguage().getUUID().toString());
        progressJSON.put(USERPROGRESS_DIFFICULTY, progress.getDifficulty());
        progressJSON.put(USERPROGRESS_CURRENTSTORY, progress.getCurrentStoryIndex());

        // Phrase Progress to JSON
        JSONArray phraseProgressArray = new JSONArray();
        for (HashMap.Entry<Phrase, Integer> entry : progress.getPhraseProgress().entrySet()) {
            JSONObject phraseEntry = new JSONObject();
            phraseEntry.put(USERPROGRESS_PHRASEPROGRESS_PHRASE, entry.getKey().getUUID().toString());
            phraseEntry.put(USERPROGRESS_PHRASEPROGRESS_INTEGER, entry.getValue());
            phraseProgressArray.add(phraseEntry);
        }
        progressJSON.put(USERPROGRESS_PHRASEPROGRESS, phraseProgressArray);

        // Word Progress JSON
        JSONArray wordProgressArray = new JSONArray();
        for (HashMap.Entry<Word, Integer> entry : progress.getWordProgress().entrySet()) {
            JSONObject wordEntry = new JSONObject();
            wordEntry.put(USERPROGRESS_WORDPROGRESS_WORD, entry.getKey().getUUID().toString());
            wordEntry.put(USERPROGRESS_WORDPROGRESS_INTEGER, entry.getValue());
            wordProgressArray.add(wordEntry);
        }
        progressJSON.put(USERPROGRESS_WORDPROGRESS, wordProgressArray);

        return progressJSON;
    }

    /**
     * Saves the languages to the json file
     * 
     * @param languages - ArrayList of languages
     * @return - boolean representing that the languages were saved successfully
     * 
     */

    // LANGUAGES
    @SuppressWarnings("unchecked")
    public static boolean saveLanguages(ArrayList<Language> languages) {
        JSONArray languagesJSON = new JSONArray();
        for (Language language : languages) {
            languagesJSON.add(createLanguageJSON(language));
        }

        try (FileWriter file = new FileWriter(
                "C:\\Users\\risha\\CSCE247-LanguageLearningApp-Final\\speaking\\src\\main\\java\\com\\data\\language.json")) {
            file.write(languagesJSON.toJSONString());
            file.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Converts Language object to JSON format
     * 
     * @param language - Language object
     * @return - JSON object representing the language
     */
    @SuppressWarnings("unchecked")
    private static JSONObject createLanguageJSON(Language language) {
        JSONObject languageJSON = new JSONObject();

        languageJSON.put(LANGUAGE_ID, language.getUUID().toString());
        languageJSON.put(LANGUAGE_NAME, language.getLanguage().toString());

        // Add stories
        JSONArray storiesJSON = new JSONArray();
        for (Story story : language.getStories()) {
            storiesJSON.add(createStoryJSON(story));
        }
        languageJSON.put(LANGUAGE_STORIES, storiesJSON);

        // Add Words
        JSONArray wordsJSON = new JSONArray();
        for (Word word : language.getWords()) {
            wordsJSON.add(createWordJSON(word));
        }
        languageJSON.put(LANGUAGE_WORDS, wordsJSON);

        // Add Phrases
        JSONArray phrasesJSON = new JSONArray();
        for (Phrase phrase : language.getPhrases()) {
            phrasesJSON.add(createPhraseJSON(phrase));
        }
        languageJSON.put(LANGUAGE_PHRASES, phrasesJSON);

        return languageJSON;
    }

    /**
     * Converts Story object to JSON format
     *
     * @param story - Story object to be converted
     * @return - JSON object representing the story
     */
    @SuppressWarnings("unchecked")
    private static JSONObject createStoryJSON(Story story) {
        JSONObject storyJSON = new JSONObject();
        storyJSON.put(STORY_TITLE, story.getTitle().toString());
        storyJSON.put(STORY_ENGLISHSTORY, story.getEnglishStory().toString());
        storyJSON.put(STORY_SPANISHSTORY, story.getSpanishStory().toString());
        return storyJSON;
    }

    /**
     * Converts Word object to JSON format
     * 
     * @param word - Word object to be converted
     * @return - JSON object representing the word
     */
    @SuppressWarnings("unchecked")
    private static JSONObject createWordJSON(Word word) {
        JSONObject wordJSON = new JSONObject();
        wordJSON.put(WORD_ID, word.getUUID().toString());
        wordJSON.put(WORD_INENGLISH, word.getEnglishWord());
        wordJSON.put(WORD_INTARGETLANGUAGE, word.getTranslatedWord());
        wordJSON.put(WORD_PRONUNCIATION, word.getPronunciation());
        wordJSON.put(WORD_GENDER, word.getGender().toString());
        wordJSON.put(WORD_PARTOFSPEECH, word.getPartOfSpeech().toString());
        wordJSON.put(WORD_DIFFICULTY, word.getDifficulty());
        return wordJSON;
    }

    /**
     * Converts Phrase object to JSON format
     * 
     * @param phrase - Phrase object to be converted
     * @return - JSON object representing the phrase
     */
    @SuppressWarnings("unchecked")
    private static JSONObject createPhraseJSON(Phrase phrase) {
        JSONObject phraseJSON = new JSONObject();
        phraseJSON.put(PHRASE_ID, phrase.getUUID().toString());
        phraseJSON.put(PHRASE_DIFFICULTY, phrase.getDifficulty());

        // English Phrase
        JSONArray englishPhraseArray = new JSONArray();
        for (Word word : phrase.getEnglishPhrase()) {
            englishPhraseArray.add(word.getUUID().toString());
        }
        phraseJSON.put(PHRASE_IN_ENGLISH, englishPhraseArray);

        // Translated Phrase
        JSONArray translatedPhraseArray = new JSONArray();
        for (Word word : phrase.getTranslatedPhrase()) {
            translatedPhraseArray.add(word.getUUID().toString());
        }
        phraseJSON.put(PHRASE_IN_TARGET_LANGUAGE, translatedPhraseArray);

        return phraseJSON;
    }

    private static String getFileWritingPath(String PATH_NAME, String JUNIT_PATH_NAME) {
        try {
            if (isJUnitTest()) {
                URI url = DataWriter.class.getResource(JUNIT_PATH_NAME).toURI();
                return url.getPath();
            } else {
                return PATH_NAME;
            }
        } catch (Exception e) {
            System.out.println("Difficulty getting resource path");
            return "";
        }
    }

}
