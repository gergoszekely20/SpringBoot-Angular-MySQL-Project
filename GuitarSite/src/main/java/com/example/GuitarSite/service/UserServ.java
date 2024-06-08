package com.example.GuitarSite.service;

import com.example.GuitarSite.entity.entity.User;

import java.util.List;

/**
 * an interface for the UserServImp
 * here are implemented the methods that are used in the UserController
 */

public interface UserServ {
    User saveUser(User u);
    List<User> saveUsers(List<User> u);
    List<User> getUsers();
    User getUserById(int id);
    User getUserByName(String name);
    String deleteUser(int id);
    User updateUser(User user);
    User getUserByEmail(String email);
}
