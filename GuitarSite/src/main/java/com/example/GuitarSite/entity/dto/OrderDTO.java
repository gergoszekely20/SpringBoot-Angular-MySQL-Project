package com.example.GuitarSite.entity.dto;

import com.example.GuitarSite.entity.enums.OrderStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDTO {
    private int idOrder;
    private String orderDescription;
    private Date date;
    private double amount;
    private String address;
    private String payment;
    private double totalAmount;
    private double discount;
    private OrderStatus orderStatus;
    private String userName;
    private List<CartItemsDTO> cartItems = new ArrayList<>();

    public OrderDTO(){}

    public OrderDTO(int idOrder, String orderDescription, Date date, double amount, String address, double totalAmount, double discount, OrderStatus orderStatus) {
        this.idOrder = idOrder;
        this.orderDescription = orderDescription;
        this.date = date;
        this.amount = amount;
        this.address = address;
        this.totalAmount = totalAmount;
        this.discount = discount;
        this.orderStatus = orderStatus;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<CartItemsDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemsDTO> cartItems) {
        this.cartItems = cartItems;
    }
}
