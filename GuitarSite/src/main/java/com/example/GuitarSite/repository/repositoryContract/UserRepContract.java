package com.example.GuitarSite.repository.repositoryContract;

import com.example.GuitarSite.entity.entity.User;

import java.util.List;

public interface UserRepContract {
    User userSave(User u);

    List<User> usersSave(List<User> u);

    List<User> getAllUsers();

    User userById(int id);

    User userByName(String name);

    String userDelete(int id);

    User userUpdate(User user);

    User userByEmail(String email);
}
