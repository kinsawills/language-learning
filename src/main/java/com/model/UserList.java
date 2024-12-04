package com.model;

import java.util.Date;
/**
 * @author kinsawills
 */
import java.util.ArrayList;
import java.util.UUID;

/**
 * Creates a class to store users
 */
public class UserList {
    private static UserList userList;
    private ArrayList<User> users;

    /**
     * The singleton constructor for UserList
     */
    private UserList() {
        users = DataLoader.getUsers();
        if (users == null) {
            users = new ArrayList<>();
        }
    }

    /**
     * Adds a user to the user list
     * 
     * @param user
     */
    public void addUser(User user) {
        users.add(user);
    }

    /**
     * Gets the instance of the user list
     * 
     * @return returns the instance of the user list
     */
    public static UserList getInstance() {
        if (userList == null)
            userList = new UserList();
        return userList;
    }

    // addUser Method to test DataWriter
    public boolean addUser(UUID id, String firstName, String lastName, String userName, String password,
            String emailAddress, Date birthday, Avatar avatar, ArrayList<User> friends, int points,
            UserProgress userProgress) {
        for (User user : users) {
            if (user.getUsername().equals(userName) || user.getEmailAddress().equals(emailAddress)) {
                return false;
            }
        }
        ArrayList<UserProgress> newUserProgress = new ArrayList<UserProgress>();
        newUserProgress.add(userProgress);

        User newUser = new User(id, firstName, lastName, userName, password, emailAddress, birthday, avatar, friends,
                points, newUserProgress);
        users.add(newUser);
        return saveUsers();
    }

    /**
     * Gets a user by their username
     * 
     * @param username takes in the username of the user
     * @return returns the user if found
     */
    public User getUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    /**
     * Edits a user by their id
     * 
     * @param id           takes in the id of the user
     * @param firstName    takes in the first name of the user
     * @param lastName     takes in the last name of the user
     * @param username     takes in the username of the user
     * @param password     takes in the password of the user
     * @param emailAddress takes in the email address of the user
     * @param birthday     takes in the birthday of the user
     * @param avatar       takes in the avatar of the user (default unless changed)
     * @param friends      takes in the friends of the user if they have any
     * @param points       sets up a point system for the user
     * @param userProgress tracks the user's progress
     * @return returns a boolean if the user was edited
     */
    public boolean editUser(UUID id, String firstName, String lastName, String username,
            String password, String emailAddress, Date birthday, Avatar avatar,
            ArrayList<User> friends, int points, UserProgress userProgress) {

        ArrayList<UserProgress> newUserProgress = new ArrayList<UserProgress>();
        newUserProgress.add(userProgress);
        User user = new User(id, firstName, lastName, username, password,
                emailAddress, birthday, avatar, friends, points, newUserProgress);
        for (int i = 0; i < users.size(); i++) {
            if (user.equals(users.get(i))) {
                users.set(i, user);
                return true;
            }
        }
        return false;
    }

    /**
     * Saves the users to a json file
     * 
     * @return returns a boolean if the save was successful
     */
    public boolean saveUsers() {
        return DataWriter.saveUsers(users);
    }

    /**
     * Gets the users
     * 
     * @return returns the users
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<UserProgress> getUsersProgress() {
        ArrayList<UserProgress> usersProgress = new ArrayList<>();
        for (User user : users) {
            usersProgress.addAll(user.getUserProgress());
        }
        return usersProgress;

    }
}
