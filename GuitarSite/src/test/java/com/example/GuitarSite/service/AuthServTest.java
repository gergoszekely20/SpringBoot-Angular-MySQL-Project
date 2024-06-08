package com.example.GuitarSite.service;

import com.example.GuitarSite.entity.entity.LoginData;
import com.example.GuitarSite.entity.entity.User;
import com.example.GuitarSite.entity.dto.UserRegistrationDTO;
import com.example.GuitarSite.entity.enums.UserType;
import com.example.GuitarSite.repository.OrderRep;
import com.example.GuitarSite.repository.UserRep;
import com.example.GuitarSite.service.implementation.AuthServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
/**
 * This class contains JUnit test cases for the AuthService implementation.
 */
public class AuthServTest {

    @Mock
    private UserRep userRepMock;
    @Mock
    private ApplicationEventPublisher eventPublisherMock;
    @Mock
    private OrderRep orderRep;

    private AuthServ userServ;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userServ = new AuthServiceImp(userRepMock,eventPublisherMock,orderRep);
    }

    /**
     * This test case verifies that the findByEmailAndPassword method of the User Repository
     * is called with the correct email and password when the login method is called.
     *
     * @throws Exception if any unexpected exception occurs during the test.
     */
    @Test
    public void testLoginUser() {
        // Given (user with login data)
        String email = "abel@gmail.com";
        String password = "habarnam1230";
        LoginData loginData = new LoginData(email, password);

        User returnedUser = userServ.login(loginData);

        Mockito.verify(userRepMock).findByEmailAndPassword(email, password);


    }
    /**
     * This test case verifies the behavior of the register method.
     * It mocks the findByEmail and save methods of the User Repository.
     * The test asserts that the returned user from register is the same as the provided user
     * and verifies that the correct methods are called on the mocks.
     *
     * @throws Exception if any unexpected exception occurs during the test.
     */

    @Test
    public void testRegisterUser() {
        UserRegistrationDTO user = new UserRegistrationDTO("Abel", "abel@gmail.com", "Aggadon 45", "0789654112", "habarnam1230");
        User userEntity = new User("Abel", "abel@gmail.com", "Aggadon 45", "0789654112", "habarnam1230", UserType.USER);

        when(userRepMock.findByEmail(user.getEmail())).thenReturn(null);
        when(userRepMock.save(userEntity)).thenReturn(userEntity);

        User registeredUser = userServ.register(user);

        assertEquals(user, registeredUser);
        Mockito.verify(userRepMock).findByEmail(user.getEmail());
        Mockito.verify(userRepMock).save(userEntity);


    }

}
