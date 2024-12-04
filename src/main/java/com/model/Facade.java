package com.model;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author kinsawills
 */
public class Facade {
    public static final int NUMBER_OF_TIMES_PHRASE_SEEN = 1;

    private static Facade facade;
    private static Scanner keyboard = new Scanner(System.in);
    private User currentUser;
    private Language currentLanguage;
    private UserList users;
    private LanguageList languages;
    private ArrayList<Phrase> phrases;

    /**
     * Creates a facade
     */
    Facade() {
        languages = LanguageList.getInstance();
        users = UserList.getInstance();
        phrases = new ArrayList<>();
    }

    /**
     * Gets the instance of the facade
     * 
     * @return returns the instance of the facade
     */
    public static Facade getInstance() {
        if (facade == null)
            facade = new Facade();
        return facade;
    }

    /**
     * Sets the phrases for the user
     * 
     * @param phrases requires the array list of phrases in order to set
     */
    public void setPhrases(ArrayList<Phrase> phrases) {
        this.phrases = phrases;
    }

    /**
     * Logs in the user
     * 
     * @param username requires the username in order to log in
     * @param password requires the password in order to log in
     * @return returns a boolean if the login was successful
     */
    public boolean login(String username, String password) {
        if (users.getUser(username) == null)
            return false;
        if (!users.getUser(username).getPassword().equals(password))
            return false;
        this.currentUser = users.getUser(username);
        this.currentLanguage = currentUser.getUserProgress().get(0).getLanguage();

        return true;
    }

    /**
     * Creates a user
     * 
     * @param id           requires the id in order to create the user
     * @param firstName    requires the first name
     * @param lastName     requires the last name
     * @param username     requires a username
     * @param password     requires a password
     * @param emailAddress requires an email address
     * @param birthday     requires a birthday
     * @param avatar       requires an avatar
     * @param friends      requires an array list of friends
     * @param points       requires the points
     * @param userProgress requires the user progress
     * @return returns a boolean if the user was created
     */
    public boolean createUser(UUID id, String firstName, String lastName, String username,
            String password, String emailAddress, String birthdayStr, Avatar avatar,
            ArrayList<User> friends, int points, UserProgress userProgress) {
        return users.addUser(id, firstName, lastName, username, password,
                emailAddress, new Date(), avatar, friends, points, userProgress);
    }

    /**
     * Creates an account
     * 
     * @param firstName requires the first name
     * @param lastName  requires the last name
     * @param username  requires the username
     * @param password  requires the password
     * @param email     requires the email
     * @return returns a boolean if the account was created
     */
    public User createAccount(String firstName, String lastName, String userName, String password,
            String birthdayStr, String email, Language language) {

        if (users.getUser(userName) != null) {
            System.out.println(userName + " already exists");
            return null;
        }
        java.util.Date birthdayUtil = DataLoader.convertStringToDate(birthdayStr);
        if (birthdayUtil == null) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            return null;
        }
        java.util.Date birthday = new java.util.Date(birthdayUtil.getTime());
        UUID id = UUID.randomUUID();
        Avatar avatar = new Avatar();
        ArrayList<User> friends = new ArrayList<>();
        int points = 0;
        UserProgress userProgress = new UserProgress(language);
        ArrayList<UserProgress> userProgressList = new ArrayList<UserProgress>();
        userProgressList.add(userProgress);

        User user = new User(id, firstName, lastName, userName, password, email, birthday, avatar, friends, points,
                userProgressList);
        users.addUser(user);
        users.saveUsers();
        return user;
    }

    public User createAccount(String firstName, String lastName, String userName, String password,
            Date birthday, String email, Language language) {

        if (users.getUser(userName) != null) {
            System.out.println(userName + " already exists");
            return null;
        }
        UUID id = UUID.randomUUID();
        Avatar avatar = new Avatar();
        ArrayList<User> friends = new ArrayList<>();
        int points = 0;
        UserProgress userProgress = new UserProgress(language);
        ArrayList<UserProgress> userProgressList = new ArrayList<UserProgress>();
        userProgressList.add(userProgress);

        User user = new User(id, firstName, lastName, userName, password, email, birthday, avatar, friends, points,
                userProgressList);
        users.addUser(user);
        users.saveUsers();
        return user;
    }


    /**
     * Logs out the user
     * 
     * @return returns a boolean if the logout was successful
     */
    public boolean logout() {
        if (currentUser != null) {
            boolean saved = users.saveUsers();
            if (saved) {
                currentUser = null;
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the avatar
     * 
     * @param character requires the new character option
     * @param hat       requires the new hat option
     */
    public void updateAvatar(CharacterOptions character, HatOptions hat) {
        currentUser.setAvatar(character, hat);
    }

    /**
     * Updates the user
     * 
     * @param id           requires the id in order to update the user
     * @param firstName    requires the first name
     * @param lastName     requires the last name
     * @param username     requires a username
     * @param password     requires a password
     * @param emailAddress requires an email address
     * @param birthday     requires a birthday
     * @param avatar       requires an avatar
     * @param friends      requires an array list of friends
     * @param points       requires the points
     * @param userProgress requires the user progress
     * @return returns a boolean if the user was updated
     */
    public boolean updateUser(UUID id, String firstName, String lastName, String username,
            String password, String emailAddress, Date birthday, Avatar avatar,
            ArrayList<User> friends, int points, UserProgress userProgress) {
        return users.editUser(id, firstName, lastName, username, password,
                emailAddress, birthday, avatar, friends, points, userProgress);
    }

    /**
     * Updates the credentials
     * 
     * @param username requires the new username
     * @param password requires the new password
     */
    public void updateCredentials(String username, String password) {
        currentUser.setUsername(username);
        currentUser.setPassword(password);
    }

    /**
     * Reads and displays the current story
     */
    public void startStory() {
        System.out.println(currentUser.getUserProgress(currentLanguage).getCurrentStory().displayStory());
        currentUser.getUserProgress(currentLanguage).getCurrentStory().speakStory();
    }

    /**
     * Sets the current language
     * 
     * @param language requires the language in order to set
     * @return returns a boolean if the language was set
     */
    public boolean setCurrentLangauge(Language language) {
        if (language != null) {
            currentLanguage = language;
            return true;
        }
        return false;
    }

    /**
     * Gets the current language
     * 
     * @return returns the current language
     */
    public Language getCurrentLanguage() {
        return languages.getLanguageByUUID(currentLanguage.getUUID());
    }

    /**
     * Starts the lesson
     */
    public void startLesson(int threshold) {
        System.out.println("Please turn up your volume for the listening questions!");
        if (this.currentUser != null && this.currentLanguage != null) {
            Lesson lesson = new Lesson(this.currentUser.getUserProgress(currentLanguage), this.currentLanguage,
                    threshold);
            ArrayList<Question> questions = lesson.getQuestions();

            for (Question question : questions) {
                System.out.println(question.getQuestion());
                String input = keyboard.nextLine();
                if (question.isCorrect(input)) {
                    this.currentUser.getUserProgress(this.currentLanguage).countCorrectPhrase(question.getPhrase());
                    lesson.increaseScore();
                    System.out.println("Correct! Great work! Your score is now " + lesson.getScore());
                } else {
                    this.currentUser.getUserProgress(this.currentLanguage).countIncorrectPhrase(question.getPhrase());
                    System.out.println("Oh no, that was incorrect. Keep going!");
                }
            }

            System.out.println("Lesson complete! Your final score is " + lesson.getScore());
        }
    }

    /**
     * Gets the language
     * 
     * @return returns the language
     */
    public Language getLanguage() {
        if (currentLanguage != null) {
            return currentLanguage;
        } else {
            return this.currentUser.getUserProgress().get(0).getLanguage();
        }
    }

    /**
     * Gets the current user
     * 
     * @return returns the current user
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Display the user's current progress with the words and phrases
     * they are currently struggling with
     * 
     * @return The user's struggle words and phrases as a string
     */
    public String displayProgress() {
        String progress = "";
        if (currentUser.getUserProgress(currentLanguage).displayHardPhrases() != "") {
            progress += "Problem Phrases:\n" + currentUser.getUserProgress(currentLanguage).displayHardPhrases() + "\n";
        } else {
            progress += "No Problem Phrases!\n";
        }
        return progress + getPercentageThroughCourse();
    }

    public ArrayList<Phrase> getProblemProgress() {
        ArrayList<Phrase> allPhrases = currentUser.getUserProgress(currentLanguage).getPhraseList();
        ArrayList<Phrase> problemPhrases = new ArrayList<>();

        for (Phrase phrase : allPhrases) {
            if (currentUser.getUserProgress(currentLanguage).getPhraseProgress(phrase) < 1) {
                problemPhrases.add(phrase);
            }
        }

        return problemPhrases;
    }

    /**
     * Gets the percentage of the user through the course
     * 
     * @return The percentage of the user through the course
     */
    public double getPercentageThroughCourse() {
        int numerator = 0;
        HashMap<Phrase, Integer> progress = currentUser.getUserProgress(currentLanguage).getPhraseProgress();

        for (Map.Entry<Phrase, Integer> entry : progress.entrySet()) {
            if (entry.getValue() >= 1) {
                numerator++;
            }
        }

        int denominator = currentLanguage.getPhrases().size();
        double percentage = ((double) numerator / denominator) * 100;
        return percentage;
    }

    /**
     * Create a text file with the user's current progress
     * 
     * @return A boolean if the file was created successfully
     */
    public boolean printProgress() {
        try (FileWriter file = new FileWriter("Narriation/speek/user-progress/" + currentUser.getUsername() + ".txt")) {
            file.write(displayProgress());
            file.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}