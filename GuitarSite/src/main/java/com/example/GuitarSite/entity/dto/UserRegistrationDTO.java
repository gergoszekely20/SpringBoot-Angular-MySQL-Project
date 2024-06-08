package com.example.GuitarSite.entity.dto;

import com.example.GuitarSite.entity.enums.UserType;

public class UserRegistrationDTO {
    private String name;
    private String email;
    private String address;
    private String telefon;
    private String password;
    private UserType userType;

    public UserRegistrationDTO(String name, String email, String address, String telefon, String password) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.telefon = telefon;
        this.password = password;
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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
