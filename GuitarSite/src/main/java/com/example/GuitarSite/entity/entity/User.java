package com.example.GuitarSite.entity.entity;

import com.example.GuitarSite.entity.enums.UserType;
import jakarta.persistence.*;

/**
 * this is the User class
 */
@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue
    private int idUser;
    private String name;

    @Column(unique = true)
    private String email;
    private String address;
    private String telefon;

    private String password;
    private UserType userType;

    /**
     * this is the constructor for the User object
     *
     * @param name     users name
     * @param email    the connection option with the client
     * @param address  where to ship the item
     * @param telefon  the connection option with the client
     * @param password it will be the password for the login and the sing up
     */

    public User(String name, String email, String address, String telefon, String password, UserType userType) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.telefon = telefon;
        this.password = password;
        this.userType = userType;
    }

    public User() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdUser() {
        return idUser;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
