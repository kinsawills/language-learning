package com.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.UUID;

/**
 * The RegisteredUser class extends UserList and contains list of registered
 * users for
 * Language learning app
 * 
 * @author Risha Patel
 */

public class User {
    private UUID id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String emailAddress;
    private Date birthday;
    private Avatar avatar;
    private ArrayList<User> friends;
    private int points;
    private ArrayList<UserProgress> userProgress;

    /**
     * Creates a new user with the parameters
     * 
     * @param id           takes in a UUID to store the user
     * @param firstName    takes in the first name of the user
     * @param lastName     takes in the last name of the user
     * @param username     takes in the username of the user
     * @param password     takes in the password of the user
     * @param emailAddress takes in the email address of the user
     * @param birthday2    takes in the birthday of the user
     * @param avatar       takes in the avatar of the user (default unless changed)
     * @param friends      takes in the friends of the user if they have any
     * @param points       sets up a point system for the user
     * @param userProgress tracks the user's progress
     */
    public User(UUID id, String firstName, String lastName, String username, String password, String emailAddress,
            Date birthday,
            Avatar avatar, ArrayList<User> friends, int points, ArrayList<UserProgress> userProgress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.birthday = birthday;
        this.avatar = avatar;
        this.friends = new ArrayList<User>();
        this.points = 0;
        this.userProgress = userProgress;
    }

    /**
     * Constructor for User objects designed for the Data Loader
     * 
     * @author Christian Ruff
     * @param id           takes in the id of the user
     * @param firstName    takes in the first name of the user
     * @param lastName     takes in the last name of the user
     * @param username     takes in the username of the user
     * @param password     takes in the password of the user
     * @param emailAddress takes in the email address of the user
     * @param birthday     takes in the birthday of the user
     * @param avatar       takes in the avatar of the user (default unless changed)
     * @param points       sets up a point system for the user
     * @param userProgress tracks the user's progress
     */
    public User(UUID id, String firstName, String lastName, String username, String password, String emailAddress,
            Date birthday, Avatar avatar, int points, ArrayList<UserProgress> userProgress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
        this.birthday = birthday;
        this.avatar = avatar;
        this.friends = new ArrayList<User>();
        this.points = 0;
        this.userProgress = userProgress;
    }

    /**
     * Creates a new user with the parameters
     * 
     * @param firstName takes in the first name of the user
     * @param lastName  takes in the last name of the user
     * @param string2
     * @param i
     * @param string
     */
    public User(String firstName, String lastName, String string, int i, String string2) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Adds a language to the user
     * 
     * @param language takes in the language to add
     */
    public void addLanguage(Language language) {
        this.userProgress.add(new UserProgress(language));
    }

    /**
     * Removes a language from the user
     * 
     * @param language takes in the language to remove
     */
    public void removeLanguage(UserProgress userProgress) {
        this.userProgress.remove(userProgress);
    }

    /**
     * Adds a friend to the user
     * 
     * @param friend takes in the friend to add
     */
    public void addFriend(User friend) {
        if (friend == null) {
            System.out.println("Friend not found!");
            return;
        }
        this.friends.add(friend);
    }

    /**
     * Adds points to the user
     * 
     * @param numOfPoints takes in the number of points to add
     */
    public void addPoints(int numOfPoints) {
        points += numOfPoints;
    }

    /**
     * Gets the first name of the user
     * 
     * @return returns the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user
     * 
     * @param newFirstName takes in the new first name
     */
    public void setFirstName(String newFirstName) {
        firstName = newFirstName;
    }

    /**
     * Gets the last name of the user
     * 
     * @return returns the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user
     * 
     * @param newLastName takes in the new last name
     */
    public void setLastName(String newLastName) {
        lastName = newLastName;
    }

    /**
     * Gets the username of the user
     * 
     * @return returns the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user
     * 
     * @param newUsername takes in the new username
     * @return returns a boolean if the username was set
     */
    public boolean setUsername(String newUsername) {
        if (newUsername.equals("")) {
            System.out.println("Please enter a valid username");
            return false;
        }
        for (User user : UserList.getInstance().getUsers()) {
            if (user.getUsername().equals(newUsername)) {
                System.out.println("This username is already in use");
                return false;
            }
        }
        this.username = newUsername;
        return true;
    }

    /**
     * Gets the password of the user
     * 
     * @return returns the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user
     * 
     * @param newPassword takes in the new password
     */
    public void setPassword(String newPassword) {
        if (newPassword.equals("")) {
            System.out.println("Please enter a valid password");
            return;
        }
        this.password = newPassword;
    }

    /**
     * Gets the email address of the user
     * 
     * @return returns the email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the email address of the user
     * 
     * @param newEmailAddress takes in the new email address
     * @return returns a boolean if the email address was set
     */
    public boolean setEmailAddress(String newEmailAddress) {
        if (newEmailAddress.equals("")) {
            System.out.println("Please enter a valid email address");
            return false;
        }
        for (User user : UserList.getInstance().getUsers()) {
            if (user.getEmailAddress().equals(newEmailAddress)) {
                System.out.println("This email address is already in use");
                return false;
            }
        }
        this.emailAddress = newEmailAddress;
        return true;
    }

    /**
     * Gets the birthday of the user
     * 
     * @return returns the birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Sets the birthday of the user
     * 
     * @param newBirthday takes in the new birthday
     */
    public void setBirthday(Date newBirthday) {
        if (newBirthday == null) {
            System.out.println("Please enter a valid birthday");
            return;
        }
        this.birthday = newBirthday;
    }

    /**
     * Gets the avatar of the user
     * 
     * @return returns the avatar
     */
    public Avatar getAvatar() {
        return avatar;
    }

    /**
     * Gets the friends of the user
     * 
     * @return returns the friends
     */
    public ArrayList<User> getFriends() {
        return friends;
    }

    /**
     * Adds friends to the user
     * 
     * @param friend takes in the friend to add to the user
     */
    public void addFriends(User friend) {
        if (friend == null) {
            System.out.println("You have no friends");
            return;
        }
        this.friends.add(friend);
    }

    /**
     * Gets the points of the user
     * 
     * @return returns the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Adds points to the user to keep track of the user's progress
     */
    public void addPoints() {
        points++;
    }

    /**
     * Gets the user progress of the user
     * 
     * @return returns the user progress
     */
    public UserProgress getUserProgress(Language language) {
        if (this.userProgress == null) {
            System.out.println("User has no progress");
            return null;
        }
        for (UserProgress progress : this.userProgress) {
            if (progress.getLanguage().equals(language))
                return progress;
        }
        System.out.println("No progress found for " + language);
        return null;
    }

    /**
     * Gets the user progress
     * 
     * @return the user progress
     */
    public ArrayList<UserProgress> getUserProgress() {
        return this.userProgress;
    }

    /**
     * Sets the avatar of the user
     * 
     * @param character takes in the character to set
     * @param hat       takes in the hat to set
     */
    public void setAvatar(CharacterOptions character, HatOptions hat) {
        if (avatar != null) {
            avatar.setCharacter(character);
            avatar.setHat(hat);
        }
    }

    /**
     * Gets the UUID of the user
     * 
     * @return returns the UUID
     */
    public UUID getUUID() {
        return this.id;
    }

    /**
     * Gets a string representation of the user
     * 
     * @return returns the string
     */
    public String toString() {
        return "UUID: " + this.id + " | Name: " + this.firstName + " " + this.lastName + " | Username: " + this.username
                + " | Password: " + this.password + " | Email Address: " + this.emailAddress + " | Date: "
                + this.birthday + " | Avatar: " + this.avatar + " | Points: " + this.points + " | User Progress: "
                + this.userProgress;
    }

}
