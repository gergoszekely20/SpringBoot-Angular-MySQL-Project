package com.example.GuitarSite.entity.entity;

import com.example.GuitarSite.entity.dto.CartItemsDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Entity
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double price;
    private double quantity;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", referencedColumnName = "idGuitar", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Guitar product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "idUser", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "idOrder", nullable = false)
    private Order order;

    public CartItemsDTO getCartDTO() {
        CartItemsDTO cartItemsDTO = new CartItemsDTO();

        cartItemsDTO.setId(id);
        cartItemsDTO.setPrice(price);
        cartItemsDTO.setIdGuitar(product.getIdGuitar());
        cartItemsDTO.setQuantity(quantity);
        cartItemsDTO.setIdUser(user.getIdUser());
        cartItemsDTO.setGuitarName(product.getName());

        try {
            byte[] imageBytes = Files.readAllBytes(Path.of("D:/Egyetem/PS/Proiecte/GuitarSite/src/main/resources/Images/" + product.getImage()));
            cartItemsDTO.setImage(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cartItemsDTO;
    }

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

    public Guitar getProduct() {
        return product;
    }

    public void setProduct(Guitar product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


}
