package com.example.GuitarSite.repository;

import com.example.GuitarSite.entity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * this is the interface where the UserRep extends the JpaRepository
 * where we can find the repository functions which communicates with the database
 */
public interface UserRep extends JpaRepository<User, Integer> {

    User findByName(String name);

    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);
}
