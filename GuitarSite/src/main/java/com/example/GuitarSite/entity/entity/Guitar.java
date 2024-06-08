package com.example.GuitarSite.entity.entity;

import com.example.GuitarSite.entity.enums.GuitarType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * This class models a guitar
 */
@Entity
@Table(name = "Guitar")

public class Guitar {

    /**
     * the guitar class includes the following attributes: idGuitar,name,type, price, guitarInfo and qunatity
     * in this class the idGuitar represents the primary key in the mySQL database
     */
    @Id
    @GeneratedValue
    private int idGuitar;
    private String name;
    private GuitarType type;
    private float price;
    private String guitarInfo;
    private int quantity;
    private String image;

    /**
     * this is the constructor for the Guitar class which initialize the objects
     *
     * @param idGuitar this is the id fot the guitar also it a primary key in the Guitar table
     * @param name its tha name of the guitar
     * @param type  we have an enum for the type of the guitar which will help us to select the category for the guit
     * @param price its specifys how much the guitar is
     * @param guitarInfo what do we have to know about the guitar
     * @param quantity how many of this guitar is stored in the deposit
     * @param image in this i store the name of the image what will be displayed on the site
     */

    public Guitar(int idGuitar, String name, GuitarType type, float price, String guitarInfo, int quantity, String image) {
        this.idGuitar = idGuitar;
        this.name = name;
        this.type = type;
        this.price = price;
        this.guitarInfo = guitarInfo;
        this.quantity = quantity;
        this.image = image;
    }

    public Guitar() {
    }


    public int getIdGuitar() {
        return idGuitar;
    }

    public void setIdGuitar(int idGuitar) {
        this.idGuitar = idGuitar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GuitarType getType() {
        return type;
    }

    public void setType(GuitarType type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getGuitarInfo() {
        return guitarInfo;
    }

    public void setGuitarInfo(String guitarInfo) {
        this.guitarInfo = guitarInfo;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}