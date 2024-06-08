package com.example.GuitarSite.service;

import com.example.GuitarSite.entity.entity.User;
import com.example.GuitarSite.entity.enums.UserType;
import com.example.GuitarSite.repository.repositoryContract.UserRepContract;
import com.example.GuitarSite.service.implementation.UserServImp;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the UserServ class.
 */
public class UserServTest {
    @Mock
    private UserRepContract userRepMock;

    private UserServ userServ;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userServ = new UserServImp(userRepMock);
    }
    /**
     * Tests the saveUser method of UserServ.
     * Verifies that the saveUser method of UserRepContract is called with the provided user.
     * Also verifies that the returned user matches the input user.
     */
    @Test
    public void testSaveUser() {
        //given
        User user = new User(

                "Abel",
                "abel@gmail.com",
                "Aggadon 45",
                "0789654112",
                "habarnam1230",
                UserType.USER);;

        //when
        when(userRepMock.userSave(user)).thenReturn(user);

        //verify
        User savedUser = userServ.saveUser(user);
        Mockito.verify(userRepMock).userSave(user);
        assertEquals(user, savedUser);
    }
    /**
     * Tests the saveUsers method of UserServ.
     * Verifies that the usersSave method of UserRepContract is called with the provided list of users.
     * Also verifies that the returned list of users matches the input list.
     */
    @Test
    public void testSaveUsers() {
        //given
        User user = new User(

                "Abel",
                "abel@gmail.com",
                "Aggadon 45",
                "0789654112",
                "habarnam1230",
                UserType.USER);

        User user2 = new User(

                "Abel12",
                "abel212@gmail.com",
                "Aggadon12 45",
                "078965412112",
                "habarn121am1230",
                UserType.USER);


        List users = new ArrayList<User>();
        users.add(user);

        users.add(user2);

        //when
        when(userRepMock.usersSave(users)).thenReturn(users);

        //verify
        List savedUsers = userServ.saveUsers(users);
        Mockito.verify(userRepMock).usersSave(users);
        assertEquals(users, savedUsers);
    }
    /**
     * Tests the getUsers method of UserServ.
     * Verifies that the getAllUsers method of UserRepContract is called.
     * Also verifies that the returned list of users matches the expected list.
     */
    @Test
    public void testGetUsers() {
        User user = new User(

                "Abel",
                "abel@gmail.com",
                "Aggadon 45",
                "0789654112",
                "habarnam1230",
                UserType.USER);

        User user2 = new User(

                "Abel12",
                "abel212@gmail.com",
                "Aggadon12 45",
                "078965412112",
                "habarn121am1230",
                UserType.USER);


        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user2);

        //when
        when(userRepMock.getAllUsers()).thenReturn(userList);

        //verify
        List<User> retrievedUsers = userServ.getUsers();
        Mockito.verify(userRepMock).getAllUsers();
        assertEquals(userList, retrievedUsers);
    }
    /**
     * Tests the getUserById method of UserServ.
     * Verifies that the userById method of UserRepContract is called with the provided user ID.
     * Also verifies that the returned user matches the expected user.
     */
    @Test
    public void testGetUserById() {

        int userId = 3;
        User expectedUser = new User(

                "Abel",
                "abel@gmail.com",
                "Aggadon 45",
                "0789654112",
                "habarnam1230",
                UserType.USER);

        // When
        when(userRepMock.userById(userId)).thenReturn(expectedUser);

        // Then
        User retrievedUser = userServ.getUserById(userId);
        assertEquals(expectedUser, retrievedUser);
        Mockito.verify(userRepMock).userById(userId);
    }

    /**
     * Tests the getUserByName method of UserServ.
     * Verifies that the userByName method of UserRepContract is called with the provided username.
     * Also verifies that the returned user matches the expected user.
     */
    @Test
    public void testGetUserByName() {
        User user = new User(

                "Abel",
                "abel@gmail.com",
                "Aggadon 45",
                "0789654112",
                "habarnam1230",
                UserType.USER);


        //when
        when(userRepMock.userByName("Abel")).thenReturn(user);

        //verify
       User retrievedUser = userServ.getUserByName("Abel");
        Mockito.verify(userRepMock).userByName("Abel");
        assertEquals(user, retrievedUser);
    }
    /**
     * Tests the getUserByEmail method of UserServ.
     * Verifies that the userByEmail method of UserRepContract is called with the provided email.
     * Also verifies that the returned user matches the expected user.
     */
    @Test
    public void testGetUserByEmail() {
        User expectedUser = new User(

                "Abel",
                "abel@gmail.com",
                "Aggadon 45",
                "0789654112",
                "habarnam1230",
                UserType.USER);

        //when
        when(userRepMock.userByEmail("abel@gmail.com")).thenReturn(expectedUser);

        // Then
        User retrievedUser = userServ.getUserByEmail("abel@gmail.com");
        assertEquals(expectedUser, retrievedUser);
        Mockito.verify(userRepMock).userByEmail("abel@gmail.com");
    }
    /**
     * Tests the updateUser method of UserServ.
     * Verifies that the userById method of UserRepContract is called with the user's ID.
     * Verifies that the userSave method of UserRepContract is called with the updateUser user.
     * Also verifies that the returned user matches the updated user.
     */
    @Test
    public void testUpdateUser() {
        User user = new User(
                "Abel",
                "abel@gmail.com",
                "Aggadon 45",
                "0789654112",
                "habarnam1230",UserType.USER);

        User updatedUser = new User(

                "Updated Name",
                "updated_email@gmail.com",
                "Updated Address",
                "Updated Phone",
                "Updated Password",
                UserType.USER);

        // Mock user repository behavior
        when(userRepMock.userById(user.getIdUser())).thenReturn(user);
        when(userRepMock.userSave(user)).thenReturn(updatedUser);

        // Test successful update
        User retrievedUser = userServ.updateUser(user);
        assertEquals(updatedUser, retrievedUser);

        // Verify findById and updated user information saved
        Mockito.verify(userRepMock).userById(user.getIdUser());
        Mockito.verify(userRepMock).userSave(user);
    }



    /**
     * Tests the deleteUser method of UserServ.
     * Verifies that the userDelete method of UserRepContract is called with the provided user ID.
     * Also verifies that the deletion message is as expected.
     */
    @Test
    public void testDeleteUser() {
        int userIdToDelete = 3;
        when(userRepMock.userDelete(userIdToDelete)).thenReturn("deleted successfully");
        String deletionMessage = userServ.deleteUser(userIdToDelete);
        assertEquals("deleted successfully", deletionMessage);
        Mockito.verify(userRepMock).userDelete(userIdToDelete);
    }



}
