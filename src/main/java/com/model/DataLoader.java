package com.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * The FileReader class reads users and languages
 * 
 * @author Christian Ruff
 */

public class DataLoader extends DataConstants {

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    private static ArrayList<Language> languagesArr = new ArrayList<>();

    // LANGUAGES
    public static ArrayList<Language> getLanguages() {
        BufferedReader reader = getReaderFromFile(LANGUAGE_FILE_NAME, LANGUAGE_FILE_NAME_JSON);
        try {
            JSONArray languageJSON = (JSONArray) new JSONParser().parse(reader);
            for (int i = 0; i < languageJSON.size(); i++) {
                JSONObject currentLang = (JSONObject) languageJSON.get(i);

                // 0. store all variables
                UUID lessonID = UUID.fromString((String) currentLang.get(LANGUAGE_ID));
                String nameOfLanguage = (String) currentLang.get(LANGUAGE_NAME);
                ArrayList<Story> stories = parseStoriesFromLanguageObject((JSONObject) currentLang);
                ArrayList<Word> words = parseWordsFromLanguageObject((JSONObject) currentLang);
                ArrayList<Phrase> phrases = parsePhrasesFromLanguageObject((JSONObject) currentLang, words);

                languagesArr.add(new Language(lessonID, nameOfLanguage, words, phrases, stories));
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return languagesArr;
    }

    private static ArrayList<Story> parseStoriesFromLanguageObject(JSONObject json) {
        ArrayList<Story> stories = new ArrayList<Story>();
        JSONArray jsonStories = (JSONArray) json.get(LANGUAGE_STORIES);

        for (int i = 0; i < jsonStories.size(); i++) {
            JSONObject currentStory = (JSONObject) jsonStories.get(i);
            String title = (String) currentStory.get(STORY_TITLE);
            String englishStory = (String) currentStory.get(STORY_ENGLISHSTORY);
            String spanishStory = (String) currentStory.get(STORY_SPANISHSTORY);
            stories.add(new Story(title, englishStory, spanishStory));
        }
        return stories;
    }

    /**
     * Parses the words from a JSON language object
     * 
     * @param json Takes the language JSON object
     * @return Returns an ArrayList of words from the language
     */
    private static ArrayList<Word> parseWordsFromLanguageObject(JSONObject json) {
        ArrayList<Word> words = new ArrayList<Word>();
        JSONArray jsonWords = (JSONArray) json.get(LANGUAGE_WORDS);

        for (int j = 0; j < jsonWords.size(); j++) {
            JSONObject currentWord = (JSONObject) jsonWords.get(j);
            UUID wordID = UUID.fromString((String) currentWord.get(WORD_ID));
            String englishWord = (String) currentWord.get(WORD_INENGLISH);
            String translatedWord = (String) currentWord.get(WORD_INTARGETLANGUAGE);
            String pronunciation = (String) currentWord.get(WORD_PRONUNCIATION);
            Gender gender = EnumUtilities.getEnumFromString(Gender.class,
                    (String) currentWord.get(WORD_GENDER));
            PartOfSpeech partOfSpeech = EnumUtilities.getEnumFromString(PartOfSpeech.class,
                    (String) currentWord.get(WORD_PARTOFSPEECH));
            int difficulty = Math.toIntExact((long) currentWord.get(WORD_DIFFICULTY));
            words.add(new Word(wordID, englishWord, translatedWord, pronunciation, partOfSpeech, gender,
                    difficulty));
        }
        return words;
    }

    /**
     * Parses the phrases from a JSON language object
     * 
     * @param json Takes the language JSON object
     * @return Returns an ArrayList of phrases from the language
     */
    private static ArrayList<Phrase> parsePhrasesFromLanguageObject(JSONObject json, ArrayList<Word> words) {
        ArrayList<Phrase> phrases = new ArrayList<Phrase>();
        JSONArray jsonPhrases = (JSONArray) json.get(LANGUAGE_PHRASES);

        for (int j = 0; j < jsonPhrases.size(); j++) {
            JSONObject currentPhrase = (JSONObject) jsonPhrases.get(j);

            UUID phraseID = UUID.fromString((String) currentPhrase.get(PHRASE_ID));
            ArrayList<Word> englishPhrase = new ArrayList<Word>();
            ArrayList<Word> translatedPhrase = new ArrayList<Word>();
            int difficulty = Math.toIntExact((long) currentPhrase.get(PHRASE_DIFFICULTY));

            // parse English phrase
            JSONArray jsonEnglishPhrase = (JSONArray) currentPhrase.get(PHRASE_IN_ENGLISH);
            for (int k = 0; k < jsonEnglishPhrase.size(); k++) {
                Word englishWord = findWordByUUID(words, UUID.fromString((String) jsonEnglishPhrase.get(k)));
                if (englishWord != null)
                    englishPhrase.add(englishWord);
            }

            // parse translated phrase
            JSONArray jsonTranslatedPhrase = (JSONArray) currentPhrase.get(PHRASE_IN_TARGET_LANGUAGE);
            for (int k = 0; k < jsonTranslatedPhrase.size(); k++) {
                Word translatedWord = findWordByUUID(words,
                        UUID.fromString((String) jsonTranslatedPhrase.get(k)));
                if (translatedWord != null)
                    translatedPhrase.add(translatedWord);
            }
            phrases.add(new Phrase(phraseID, englishPhrase, translatedPhrase, difficulty));
        }

        return phrases;
    }

    /**
     * Finds a word by its UUID
     * 
     * @param words Takes an ArrayList of words
     * @param id    Returns a word by its UUID
     * @return Returns the word if found, else NULL
     */
    private static Word findWordByUUID(ArrayList<Word> words, UUID id) {
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).getUUID().equals(id))
                return words.get(i);
        }
        return null;
    }

    // USERS
    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        BufferedReader reader = getReaderFromFile(USER_FILE_NAME, USER_FILE_NAME_JSON);
        try {
            JSONArray peopleJSON = (JSONArray) new JSONParser().parse(reader);
            HashMap<User, ArrayList<UUID>> friendsHash = new HashMap<User, ArrayList<UUID>>();

            // 1. construct user objects without friends
            for (int i = 0; i < peopleJSON.size(); i++) {
                JSONObject personJSON = (JSONObject) peopleJSON.get(i);
                UUID id = UUID.fromString((String) personJSON.get(USER_ID));
                String firstName = (String) personJSON.get(USER_FIRST_NAME);
                String lastName = (String) personJSON.get(USER_LAST_NAME);
                String username = (String) personJSON.get(USER_USERNAME);
                String password = (String) personJSON.get(USER_PASSWORD);
                String emailAddress = (String) personJSON.get(USER_EMAIL);
                int points = Math.toIntExact((long) personJSON.get(USER_POINTS));

                // convert special data types
                Date birthday = convertStringToDate((String) personJSON.get(USER_BIRTHDAY));
                Avatar avatar = convertJSONToAvatar((JSONObject) personJSON.get(USER_AVATAR));
                ArrayList<UserProgress> userProgress = convertJSONToUserProgress(
                        (JSONArray) personJSON.get(USERPROGRESS));

                // create user object
                User newUser = new User(id, firstName, lastName, username, password, emailAddress, birthday, avatar,
                        points, userProgress);
                // System.out.println(newUser);

                // record the user's friends as a list of UUIDs
                friendsHash.put(newUser, convertFriendsToArrayList((JSONArray) personJSON.get(FRIENDS)));
                users.add(newUser);
            }

            // 2. iteratively link all friend objects (referencing UUIDs) to each user
            for (int i = 0; i < users.size(); i++) {
                User updatedUser = users.get(i);
                ArrayList<UUID> friendsUUIDs = (ArrayList<UUID>) friendsHash.get(updatedUser);

                // create user list of friends for each user
                if (friendsUUIDs == null) {
                    // System.out.println("User #" + i + " has no friends.");
                } else {
                    // iterate through each friend
                    for (int j = 0; j < friendsUUIDs.size(); j++) {
                        User friend = findUserByUUID(users, friendsUUIDs.get(j));
                        if (friend != null) {
                            updatedUser.addFriend(friend);
                            // System.out.println(
                            // updatedUser.getFirstName() + " is now friended with " +
                            // friend.getFirstName());
                        }
                    }
                }
            }
            reader.close();
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Converts a String data type to the Date data type
     * 
     * @param birthday Takes a String of format yyyy-MM-dd
     * @return Returns a Date object
     */
    public static Date convertStringToDate(String str) {
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;
        try {
            date = simpleDate.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * Converts a JSON Array of Friends to a ArrayList
     * 
     * @param json Takes a JSON Array of friends
     * @return Returns an ArrayList of friends
     */
    private static ArrayList<UUID> convertFriendsToArrayList(JSONArray json) {
        ArrayList<UUID> ret = new ArrayList<UUID>();
        if (json != null) {
            for (int i = 0; i < json.size(); i++) {
                ret.add(UUID.fromString((String) json.get(i)));
            }
        }
        return ret;
    }

    /**
     * Finds a user by its UUID
     * 
     * @param users requires the array list of users in order to search
     * @param id    requires the specific id in order to find
     * @return returns the user if found
     */
    private static User findUserByUUID(ArrayList<User> users, UUID id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUUID().equals(id))
                return users.get(i);
        }
        return null;
    }

    /**
     * Converts the json to an avatar
     * 
     * @param json requires the json object in order to create the avatar
     * @return returns the avatar
     */
    private static Avatar convertJSONToAvatar(JSONObject json) {
        String character = (String) json.get(CHARACTER);
        String hat = (String) json.get(HAT);
        return new Avatar(character, hat);
    }

    /**
     * Converts the json to a user progress
     * 
     * @param json requires the json object in order to create the user progress
     * @return returns the user progress
     */
    private static ArrayList<UserProgress> convertJSONToUserProgress(JSONArray json) {
        ArrayList<UserProgress> ret = new ArrayList<UserProgress>();

        for (int i = 0; i < json.size(); i++) {
            JSONObject currentUserProgress = (JSONObject) json.get(i);

            int userDifficulty = Math.toIntExact((long) currentUserProgress.get(USERPROGRESS_DIFFICULTY));
            int currentStory = Math.toIntExact((long) currentUserProgress.get(USERPROGRESS_CURRENTSTORY));

            try {
                Language language = getLanguageByUUID(
                        UUID.fromString((String) currentUserProgress.get(USERPROGRESS_LANGUAGE)));
                HashMap<Phrase, Integer> phraseProgress = convertJSONToPhraseProgress(
                        (JSONArray) currentUserProgress.get(USERPROGRESS_PHRASEPROGRESS), language.getPhrases());
                HashMap<Word, Integer> wordProgress = convertJSONToWordProgress(
                        (JSONArray) currentUserProgress.get(USERPROGRESS_WORDPROGRESS), language.getWords());
                ret.add(new UserProgress(language, userDifficulty, currentStory, phraseProgress, wordProgress));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    private static Language getLanguageByUUID(UUID id) {
        for (Language language : languagesArr) {
            if (language.getUUID().equals(id)) {
                return language;
            }
            return null;
        }
        return null;
    }

    /**
     * Converts the json to a phrase progress
     * 
     * @param json requires the json array in order to create the phrase progress
     * @return returns the phrase progress
     */
    public static HashMap<Phrase, Integer> convertJSONToPhraseProgress(JSONArray json, ArrayList<Phrase> phrases) {
        HashMap<Phrase, Integer> ret = new HashMap<Phrase, Integer>();
        for (int i = 0; i < json.size(); i++) {
            // 1. get key-value pair
            JSONObject keyValuePair = (JSONObject) json.get(i);

            // 2. get phrase
            UUID uuidOfPhrase = UUID.fromString((String) keyValuePair.get(USERPROGRESS_PHRASEPROGRESS_PHRASE));
            Phrase phrase = getPhraseByUUID(phrases, uuidOfPhrase);

            // 3. get value
            int integer = Math.toIntExact((long) keyValuePair.get(USERPROGRESS_PHRASEPROGRESS_INTEGER));
            ret.put(phrase, integer);
        }
        return ret;
    }

    /**
     * Converts the json to a word progress
     * 
     * @param json requires the json array in order to create the word progress
     * @return returns the word progress
     */
    public static HashMap<Word, Integer> convertJSONToWordProgress(JSONArray json, ArrayList<Word> words) {
        HashMap<Word, Integer> ret = new HashMap<Word, Integer>();
        for (int i = 0; i < json.size(); i++) {
            // 1. get key-value pair
            JSONObject keyValuePair = (JSONObject) json.get(i);

            // 2. get phrase
            UUID uuidOfWord = UUID.fromString((String) keyValuePair.get(USERPROGRESS_WORDPROGRESS_WORD));
            Word word = getWordByUUID(words, uuidOfWord);

            // TODO fix word above

            // 3. get value
            int integer = Math.toIntExact((long) keyValuePair.get(USERPROGRESS_WORDPROGRESS_INTEGER));
            ret.put(word, integer);
        }
        return ret;
    }

    public static Phrase getPhraseByUUID(ArrayList<Phrase> phrases, UUID id) {
        for (Phrase phrase : phrases) {
            if (phrase.getUUID().equals(id))
                return phrase;
        }
        return null;
    }

    public static Word getWordByUUID(ArrayList<Word> words, UUID id) {
        for (Word word : words) {
            if (word.getUUID().equals(id))
                return word;
        }
        return null;
    }

    private static BufferedReader getReaderFromFile(String fileName, String jsonFileName){
        try {
            if(isJUnitTest()){
                InputStream inputStream = DataLoader.class.getResourceAsStream(jsonFileName);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                return new BufferedReader(inputStreamReader);
            } else {
                FileReader reader = new FileReader(fileName);
                return new BufferedReader(reader);
            }
        } catch(Exception e){
            System.out.println("Can't load");
            return null;
        }
            
    }
}



