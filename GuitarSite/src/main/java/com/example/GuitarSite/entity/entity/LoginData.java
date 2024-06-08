package com.example.GuitarSite.entity.entity;

/**
 * this is the LoginData class which contains the data that is necessary for the user login
 */
public class LoginData {
    private String email;
    private String password;

    public LoginData() {
    }

    public LoginData(String email, String password) {
        this.email = email;
        this.password=password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
