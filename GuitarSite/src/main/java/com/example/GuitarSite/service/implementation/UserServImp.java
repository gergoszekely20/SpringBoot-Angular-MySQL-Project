package com.example.GuitarSite.service.implementation;

import com.example.GuitarSite.entity.entity.User;
import com.example.GuitarSite.repository.repositoryContract.UserRepContract;
import com.example.GuitarSite.service.UserServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * this is the class UserServImp where I implemented the get, post, put and delete endpoints
 */
@Service
public class UserServImp implements UserServ {
    private final UserRepContract repository;

    @Autowired
    public UserServImp(UserRepContract repository) {
        this.repository = repository;
    }

    /**
     * this is a post http request that saves an user in the database
     *
     * @param u
     * @return
     */
    public User saveUser(User u) {
        return repository.userSave(u);

    }

    /**
     * this is a post http request that saves users in the database
     *
     * @param u
     * @return
     */
    public List<User> saveUsers(List<User> u) {
        return repository.usersSave(u);

    }

    /**
     * this is a get http request that finds all the users from the database
     *
     * @return
     */
    public List<User> getUsers() {
        return repository.getAllUsers();
    }

    /**
     * this is a get http request finds us the user by the specified id
     *
     * @param id
     * @return
     */
    public User getUserById(int id) {
        return repository.userById(id);
    }

    /**
     * this is a get http request finds us the user by the specified name
     *
     * @param name
     * @return
     */
    public User getUserByName(String name) {
        return repository.userByName(name);
    }

    /**
     * this is a delete http request deletes the user by the given id
     *
     * @param id
     * @return
     */
    public String deleteUser(int id) {
        repository.userDelete(id);
        return "deleted successfully";
    }

    /**
     * this is a put http request that finds the user by id and updates the modified filds
     *
     * @param user
     * @return
     */
    public User updateUser(User user) {
        User existingUser;
        existingUser = repository.userById(user.getIdUser());
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setAddress(user.getAddress());
            existingUser.setTelefon(user.getTelefon());
            existingUser.setPassword(user.getPassword());
            return repository.userSave(existingUser);
        } else {
            return null;
        }
    }

    /**
     * this is a get http request that finds the user by email it will help us when the user wants to register
     *
     * @param email
     * @return
     */

    public User getUserByEmail(String email) {
        return repository.userByEmail(email);
    }
}
