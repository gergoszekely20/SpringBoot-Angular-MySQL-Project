package com.example.GuitarSite.service;

import com.example.GuitarSite.entity.entity.LoginData;
import com.example.GuitarSite.entity.entity.User;
import com.example.GuitarSite.entity.dto.UserRegistrationDTO;

/**
 * an interface for the AuthServImp
 * here are implemented the methods that are used in the AuthController
 */
public interface AuthServ {
    User login(LoginData loginData);

    User register(UserRegistrationDTO user);
}
