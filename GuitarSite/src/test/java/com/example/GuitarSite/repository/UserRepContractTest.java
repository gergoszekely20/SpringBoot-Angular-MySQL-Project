package com.example.GuitarSite.repository;

import com.example.GuitarSite.entity.entity.User;
import com.example.GuitarSite.entity.enums.UserType;
import com.example.GuitarSite.repository.repositoryContract.UserRepContract;
import com.example.GuitarSite.repository.repositoryImpl.UserRepContractImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the {@link UserRepContractImpl} class.
 */
public class UserRepContractTest {
    @Mock
    private UserRep userRepMock;

    private UserRepContract userRepContract;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userRepContract = new UserRepContractImpl(userRepMock);
    }
    /**
     * Tests the {@link UserRepContract #userSave(User)} method.
     * Verifies that the {@link UserRep #save(User)} method of UserRep is called with the provided user.
     * Also verifies that the returned user matches the input user.
     */
    @Test
    public void testUserSave() {
        //given
        User user = new User(

                "Abel",
                "abel@gmail.com",
                "Aggadon 45",
                "0789654112",
                "habarnam1230",
                UserType.USER);

        //when
        when(userRepMock.save(user)).thenReturn(user);

        //verify
        User savedUser = userRepContract.userSave(user);
        Mockito.verify(userRepMock).save(user);
        assertEquals(user, savedUser);
    }
    /**
     * Tests the {@link UserRepContract #usersSave(List)} method.
     * Verifies that the {@link UserRep #saveAll(List)} method of UserRep is called with the provided list of users.
     * Also verifies that the returned list of users matches the input list.
     */
    @Test
    public void testUsersSave() {
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
        when(userRepMock.saveAll(users)).thenReturn(users);

        //verify
        List savedUsers = userRepContract.usersSave(users);
        Mockito.verify(userRepMock).saveAll(users);
        assertEquals(users, savedUsers);
    }
    /**
     * Tests the {@link UserRepContract#getAllUsers()} method.
     * Verifies that the {@link UserRep#findAll()} method of UserRep is called.
     * Also verifies that the returned list of users matches the expected list.
     */
    @Test
    public void testGetAllUsers() {
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
        when(userRepMock.findAll()).thenReturn(userList);

        //verify
        List<User> retrievedUsers = userRepContract.getAllUsers();
        Mockito.verify(userRepMock).findAll();
        assertEquals(userList, retrievedUsers);
    }
    /**
     * Tests the getUserById method of UserServ.
     * Verifies that the findById method of UserRep is called with the provided user ID.
     * Also verifies that the returned user matches the expected user.
     */
    @Test
    public void testUserById() {

        int userId = 3;
        User expectedUser = new User(

                "Abel",
                "abel@gmail.com",
                "Aggadon 45",
                "0789654112",
                "habarnam1230",
                UserType.USER);

        // When
        when(userRepMock.findById(userId)).thenReturn(Optional.of(expectedUser));

        // Then
        User retrievedUser = userRepContract.userById(userId);
        assertEquals(expectedUser, retrievedUser);
        Mockito.verify(userRepMock).findById(userId);
    }

    /**
     * Tests the {@link UserRepContract #userByName(String)} method.
     * Verifies that the {@link UserRep #findByName(String)} method of UserRep is called with the provided username.
     * Also verifies that the returned user matches the expected user.
     */
    @Test
    public void testUserByName() {
        User user = new User(

                "Abel",
                "abel@gmail.com",
                "Aggadon 45",
                "0789654112",
                "habarnam1230",
                UserType.USER);


        //when
        when(userRepMock.findByName("Abel")).thenReturn(user);

        //verify
       User retrievedUser = userRepContract.userByName("Abel");
        Mockito.verify(userRepMock).findByName("Abel");
        assertEquals(user, retrievedUser);
    }
    /**
     * Tests the {@link UserRepContract #userByEmail(String)} method.
     * Verifies that the {@link UserRep #findByEmail(String)} method of UserRep is called with the provided email.
     * Also verifies that the returned user matches the expected user.
     */
    @Test
    public void testUserByEmail() {
        User expectedUser = new User(

                "Abel",
                "abel@gmail.com",
                "Aggadon 45",
                "0789654112",
                "habarnam1230",
                UserType.USER);

        //when
        when(userRepMock.findByEmail("abel@gmail.com")).thenReturn(expectedUser);

        // Then
        User retrievedUser = userRepContract.userByEmail("abel@gmail.com");
        assertEquals(expectedUser, retrievedUser);
        Mockito.verify(userRepMock).findByEmail("abel@gmail.com");
    }
    /**
     * Tests the {@link UserRepContract #userUpdate(User)} method.
     * Verifies that the {@link UserRep #findById(int)} method of UserRep is called with the user's ID to retrieve the user.
     * Verifies that the retrieved user is not null.
     * Then, verifies that the {@link UserRep #save(User)} method of UserRep is called with the updated user information.
     * Also verifies that the returned user matches the updated user.
     */
    @Test
    public void testUserUpdate() {
        User user = new User(

                "Abel",
                "abel@gmail.com",
                "Aggadon 45",
                "0789654112",
                "habarnam1230",
                UserType.USER);

        User updatedUser = new User(

                "Updated Name",
                "updated_email@gmail.com",
                "Updated Address",
                "Updated Phone",
                "Updated Password",
                UserType.USER);

        // Mock user repository behavior
        when(userRepMock.findById(user.getIdUser())).thenReturn(Optional.of(user));
        when(userRepMock.save(user)).thenReturn(updatedUser);

        // Test successful update
        User retrievedUser = userRepContract.userUpdate(user);
        assertEquals(updatedUser, retrievedUser);

        // Verify findById and updated user information saved
        Mockito.verify(userRepMock).findById(user.getIdUser());
        Mockito.verify(userRepMock).save(user);
    }



    /**
     * Tests the {@link UserRepContract #userDelete(int)} method.
     * Verifies that the {@link UserRep #deleteById(int)} method of UserRep is called with the provided user ID.
     * Also verifies that the deletion message is "deleted successfully".
     */
    @Test
    public void testUserDelete() {
        int userIdToDelete = 3;
        Mockito.doNothing().when(userRepMock).deleteById(userIdToDelete);
        String deletionMessage = userRepContract.userDelete(userIdToDelete);
        assertEquals("deleted successfully", deletionMessage);
        Mockito.verify(userRepMock).deleteById(userIdToDelete);
    }



}
