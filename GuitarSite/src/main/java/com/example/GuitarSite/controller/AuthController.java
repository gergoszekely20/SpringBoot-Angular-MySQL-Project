package com.example.GuitarSite.controller;

import com.example.GuitarSite.entity.entity.LoginData;
import com.example.GuitarSite.entity.entity.User;
import com.example.GuitarSite.entity.dto.LoggedInUserDTO;
import com.example.GuitarSite.entity.dto.UserRegistrationDTO;
import com.example.GuitarSite.service.AuthServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * this is the AuthController class that makes the connection with the interface and here we can see he http requests mapped
 * also here we can see the endpoints for each methode that was implemented in the AuthServImp
 */
@RestController
public class AuthController {
    @Autowired
    private AuthServ authService;

    /**
     * Controller handling authentication-related HTTP requests.
     * Handles user login via HTTP POST request.
     */
    @PostMapping("/auth/login")
    public ResponseEntity login(@RequestBody LoginData loginData) {
        User loggedInUser = authService.login(loginData);

        if (loggedInUser != null) {
            LoggedInUserDTO loggedInUserDTO = new LoggedInUserDTO(loggedInUser.getIdUser(), loggedInUser.getName(), loggedInUser.getEmail(), loggedInUser.getAddress(), loggedInUser.getTelefon(), loggedInUser.getUserType());

            return new ResponseEntity<>(loggedInUserDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Controller handling user registration via HTTP POST request.
     */
    @PostMapping("/auth/register")
    public User register(@RequestBody UserRegistrationDTO user) {
        return authService.register(user);
    }
}
