package com.language.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;

import com.model.DataWriter;
import com.model.User;
import com.model.UserList;
import com.model.UserProgress;
import com.model.Avatar;

public class DataWriterTest {

    private UserList users = UserList.getInstance();
    private ArrayList<User> userList = users.getUsers();

    @Test
    public void testTesting() {
        assertTrue(true);
    }

    @Before
    public void setup() {
        UserList.getInstance().getUsers();
        userList.clear();
        DataWriter.saveUsers(userList);
    }

    @Test
    public void testWritingZeroUsers() {
        assertEquals(0, userList.size());
    }

    @Test
    public void testAddingSingleUser() {
        User user = new User(UUID.randomUUID(), "Jhon", "Doe", "jhonD", "123", "jhon@gmail.com",
                new Date(), new Avatar(), 0, new ArrayList<UserProgress>());
        userList.add(user);
        DataWriter.saveUsers(userList);
        assertEquals(1, userList.size());
    }

    @Test
    public void testAddingMultipleUsers() {
        User user1 = new User(UUID.randomUUID(), "Jhon", "Doe", "jhonD", "123", "jhon@gmail.com",
                new Date(), new Avatar(), 0, new ArrayList<UserProgress>());
        User user2 = new User(UUID.randomUUID(), "Risha", "Patel", "Rishap", "456", "Risha@gmail.com",
                new Date(), new Avatar(), 0, new ArrayList<UserProgress>());
        User user3 = new User(UUID.randomUUID(), "Coddy", "Smith", "CodyS", "177", "coddy@gmail.com",
                new Date(), new Avatar(), 0, new ArrayList<UserProgress>());
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        DataWriter.saveUsers(userList);
        assertEquals(3, userList.size());
        assertEquals("jhonD", userList.get(0).getUsername());
    }

}
