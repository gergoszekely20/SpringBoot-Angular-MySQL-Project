package com.example.GuitarSite.entity.dto;

import com.example.GuitarSite.entity.enums.UserType;

public class LoggedInUserDTO {
    private int idUser;
    private String name;
    private String email;
    private String address;
    private String telefon;
    private int userType;

    public LoggedInUserDTO(int idUser, String name, String email, String address, String telefon, UserType userType) {
        this.idUser = idUser;
        this.name = name;
        this.email = email;
        this.address = address;
        this.telefon = telefon;
        this.userType = userType.ordinal();
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
