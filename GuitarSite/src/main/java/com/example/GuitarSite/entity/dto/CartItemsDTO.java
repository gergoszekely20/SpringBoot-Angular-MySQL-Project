package com.example.GuitarSite.entity.dto;

public class CartItemsDTO {
    private int id;

    private double price;
    private double quantity;
    private int idGuitar;
    private  int idOrder;
    private  String guitarName;
    private byte[] image;
    private  int idUser;

    private boolean canIncreaseQuantity;
    private boolean canDecreaseQuantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public int getIdGuitar() {
        return idGuitar;
    }

    public void setIdGuitar(int idGuitar) {
        this.idGuitar = idGuitar;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public String getGuitarName() {
        return guitarName;
    }

    public void setGuitarName(String guitarName) {
        this.guitarName = guitarName;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public boolean isCanIncreaseQuantity() {
        return canIncreaseQuantity;
    }

    public void setCanIncreaseQuantity(boolean canIncreaseQuantity) {
        this.canIncreaseQuantity = canIncreaseQuantity;
    }

    public boolean isCanDecreaseQuantity() {
        return canDecreaseQuantity;
    }

    public void setCanDecreaseQuantity(boolean canDecreaseQuantity) {
        this.canDecreaseQuantity = canDecreaseQuantity;
    }
}
