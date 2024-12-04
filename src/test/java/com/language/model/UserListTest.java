package com.language.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.model.Avatar;
import com.model.Language;
//import com.narriation.DataLoader;
//import com.narriation.Language;
import com.model.User;
import com.model.UserList;
import com.model.UserProgress;

public class UserListTest {
    private UserList userList;
    private User invalidUser;
    private User user1;
    private User user2;
    private User user3;

    @Before
    public void setup() {

        userList = UserList.getInstance();
        userList.getUsers().clear();

        invalidUser = new User(null, null, null, null, null, null, null, null, null, 0, null);

        user1 = new User(UUID.randomUUID(), "John", "Doe", "jDoe", "doe", "jdoe@gmail.com",
                new Date(), new Avatar(), 0, new ArrayList<UserProgress>());

        user2 = new User(UUID.randomUUID(), "Jane", "Smith", "jSmith", "smith", "jsmith@gmail.com",
                new Date(), new Avatar(), 0, new ArrayList<UserProgress>());

        user3 = new User(UUID.randomUUID(), "Jane", "Doe", "jhoneD", "123", "jane@gmail.com",
                new Date(), new Avatar(), 0, new ArrayList<UserProgress>());

        userList.addUser(user1);
        userList.addUser(user2);
        // userList.addUser(user3);
    }

    @Test
    public void TestTesting() {
        assertTrue(true);
    }

    @Test
    public void testAddUser() {
        assertEquals(2, userList.getUsers().size());
        userList.addUser(user3);
        assertEquals(3, userList.getUsers().size());
        assertTrue(userList.getUsers().contains(user3));
    }

    @Test
    public void testGetNonExistentUserName() {
        User foudUser = userList.getUser("Mike");
        assertNull(foudUser);
    }

    @Test
    public void testGetUser() {
        User foundUser = userList.getUser(user1.getUsername());
        assertEquals(user1, foundUser);
    }

    @Test
    public void testGetUserProgress() {
        userList.addUser(user3);
        ArrayList<UserProgress> userProgress = userList.getUsersProgress();
        assertEquals(user3.getUserProgress().size(), userProgress.size());
    }

    @Test
    public void testEditUser() {
        userList.addUser(user3);
        UUID existingUserId = user3.getUUID();
        boolean edited = userList.editUser(
                existingUserId,
                "Jane",
                "Doe",
                "janedoe",
                "updatedPassword123",
                "janedoe@gmail.com",
                new Date(),
                new Avatar("", ""),
                new ArrayList<>(),
                0,
                new UserProgress(null, 0, 0, null, null));
        assertTrue(edited);

        User updatedUser = userList.getUser("janedoe");
        assertNotNull(updatedUser);
        assertEquals("Jane", updatedUser.getFirstName());
        assertEquals("Doe", updatedUser.getLastName());
        assertEquals("janedoe", updatedUser.getUsername());
        assertEquals("updatedPassword123", updatedUser.getPassword());
        assertEquals("janedoe@gmail.com", updatedUser.getEmailAddress());
        assertEquals(0, updatedUser.getPoints());
        assertEquals("", updatedUser.getAvatar().getCharacter());
        assertEquals("", updatedUser.getAvatar().getHat());
    }

    @Test
    public void testGetUsersProgressForMultipleUsers() {
        user1.getUserProgress().add(new UserProgress(null, 50, 100, null, null));
        user2.getUserProgress().add(new UserProgress(null, 30, 100, null, null));

        ArrayList<UserProgress> usersProgress = userList.getUsersProgress();
        assertEquals(2, usersProgress.size());
        assertTrue(usersProgress.containsAll(user1.getUserProgress()));
        assertTrue(usersProgress.containsAll(user2.getUserProgress()));
    }

    @Test
    public void testGetUserProgressForEmptyUserList() {
        userList.getUsers().clear();
        ArrayList<UserProgress> usersProgress = userList.getUsersProgress();
        assertEquals(0, usersProgress.size());
    }

    @Test
    public void testSaveUsers() {
        userList.addUser(user3);
        boolean saved = userList.saveUsers();
        assertTrue(saved);
        assertEquals(3, userList.getUsers().size());
    }

    @Test
    public void testAddUserWithInvalidNullData() {
        userList.addUser(invalidUser);
        assertEquals("User list should not have been modified", 3, userList.getUsers().size());
    }

    @Test
    public void testAddUserWithSameFirstNameAndLastName() {
        // Add the initial user
        boolean firstAdded = userList.addUser(
                UUID.randomUUID(), "Tammy", "Tammy", "Ttammy", "123", "Tammy@gmail.com",
                new Date(), new Avatar(), new ArrayList<>(), 0, new UserProgress(null, 0, 0, null, null));

        assertTrue("Adding the first user should succeed", firstAdded);

        // Attempt to add another user with the same first and last name
        boolean secondAdded = userList.addUser(
                UUID.randomUUID(), "Tammy", "Tammy", "Ttammy2", "456", "Tammy2@gmail.com",
                new Date(), new Avatar(), new ArrayList<>(), 0, new UserProgress(null, 0, 0, null, null));

        // Assert that adding the second user fails
        assertFalse("Adding another user with the same first and last name should fail", secondAdded);

        // Ensure the size of the users list reflects only the first addition
        assertEquals(1, userList.getUsers().size());

        // Ensure the first user is accessible
        assertNotNull(userList.getUser("Ttammy"));
        assertNull(userList.getUser("Ttammy2"));
    }

}
