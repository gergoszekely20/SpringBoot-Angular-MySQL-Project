package com.example.GuitarSite.repository.repositoryImpl;

import com.example.GuitarSite.entity.entity.User;
import com.example.GuitarSite.repository.UserRep;
import com.example.GuitarSite.repository.repositoryContract.UserRepContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepContractImpl implements UserRepContract {
    private UserRep repository;

    @Autowired
    public UserRepContractImpl(UserRep repository) {
        this.repository = repository;
    }

    /**
     * this is a post http request that saves an user in the database
     *
     * @param u
     * @return
     */
    public User userSave(User u) {
        return repository.save(u);

    }

    /**
     * this is a post http request that saves users in the database
     *
     * @param u
     * @return
     */
    public List<User> usersSave(List<User> u) {
        return repository.saveAll(u);

    }

    /**
     * this is a get http request that finds all the users from the database
     *
     * @return
     */
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    /**
     * this is a get http request finds us the user by the specified id
     *
     * @param id
     * @return
     */
    public User userById(int id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * this is a get http request finds us the user by the specified name
     *
     * @param name
     * @return
     */
    public User userByName(String name) {
        return repository.findByName(name);
    }

    /**
     * this is a delete http request deletes the user by the given id
     *
     * @param id
     * @return
     */
    public String userDelete(int id) {
        repository.deleteById(id);
        return "deleted successfully";
    }

    /**
     * this is a put http request that finds the user by id and updates the modified filds
     *
     * @param user
     * @return
     */
    public User userUpdate(User user) {
        User existingUser;
        existingUser = repository.findById(user.getIdUser()).orElse(null);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setAddress(user.getAddress());
            existingUser.setTelefon(user.getTelefon());
            existingUser.setPassword(user.getPassword());
            return repository.save(existingUser);
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

    public User userByEmail(String email) {
        return repository.findByEmail(email);
    }
}
