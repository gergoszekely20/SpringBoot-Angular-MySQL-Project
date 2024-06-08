package com.example.GuitarSite.controller;

import com.example.GuitarSite.service.UserServ;
import com.example.GuitarSite.entity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * this is the UserController class that makes the connection with the interface and here we can see he http requests mapped
 * also here we can see the endpoints for each methode that was implemented in the UserServImp
 */
@RestController
public class UserController {
    @Autowired
    private UserServ userService;
    /**
     * Controller handling user-related HTTP requests.
     * Adds a new user via HTTP POST request.
     */
    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
    /**
     * Controller handling bulk user addition via HTTP POST request.
     */
    @PostMapping("/addUsers")
    public List<User> addUsers(@RequestBody List<User> users) {
        return userService.saveUsers(users);
    }
    /**
     * Retrieves all users via HTTP GET request.
     */
    @GetMapping("/showUsers")
    public List<User> findUsers() {
        return userService.getUsers();
    }
    /**
     * Retrieves a user by ID via HTTP GET request.
     */
    @GetMapping("/userById/{id}")
    public User findUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }
    /**
     * Retrieves a user by name via HTTP GET request.
     */
    @GetMapping("/userByName/{name}")
    public User findUserByName(@PathVariable String name) {
        return userService.getUserByName(name);
    }
    /**
     * Updates a user via HTTP PUT request.
     */
    @PutMapping("/userUpdate")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }
    /**
     * Deletes a user by ID via HTTP DELETE request.
     */
    @DeleteMapping("/userDelete/{id}")
    public String deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }
    /**
     * Retrieves a user by email via HTTP GET request.
     */
    @GetMapping("/userByEmail/{email}")
    public User findUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }
}
