package com.example.GuitarSite.entity.dto;

import com.example.GuitarSite.entity.enums.GuitarType;

public class GuitarDTO {
    private int idGuitar;
    private String name;
    private GuitarType type;
    private float price;
    private String guitarInfo;
    private int quantity;
    private byte[] image;

    public GuitarDTO() {
    }

    public GuitarDTO(int idGuitar, String name, GuitarType type, float price, String guitarInfo, int quantity, byte[] image) {
        this.idGuitar = idGuitar;
        this.name = name;
        this.type = type;
        this.price = price;
        this.guitarInfo = guitarInfo;
        this.quantity = quantity;
        this.image = image;
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

    public String getGuitarInfo() {
        return guitarInfo;
    }

    public void setGuitarInfo(String guitarInfo) {
        this.guitarInfo = guitarInfo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
