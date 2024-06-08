package com.example.GuitarSite.entity.entity;

import com.example.GuitarSite.entity.dto.OrderDTO;
import com.example.GuitarSite.entity.enums.OrderStatus;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * this is the class for the Orders
 */
@Entity
@Table(name = "Order_info")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOrder;
    private String orderDescription;
    private Date date;
    private double amount;
    private String address;
    private String payment;
    private double totalAmount;
    private double discount;
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "idUser", nullable = false)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<CartItems> cartItems = new ArrayList<>();

    public Order() {

    }

    public Order(int idOrder, String orderDescription, Date date, double amount, String address, String payment, double totalAmount, double discount, OrderStatus orderStatus) {
        this.idOrder = idOrder;
        this.orderDescription = orderDescription;
        this.date = date;
        this.amount = amount;
        this.address = address;
        this.payment = payment;
        this.totalAmount = totalAmount;
        this.discount = discount;
        this.orderStatus = orderStatus;
    }

    public OrderDTO getOrderDTO() {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setIdOrder(idOrder);
        orderDTO.setOrderDescription(orderDescription);
        orderDTO.setDate(date);
        orderDTO.setAmount(amount);
        orderDTO.setAddress(address);
        orderDTO.setTotalAmount(totalAmount);
        orderDTO.setDiscount(discount);
        orderDTO.setOrderStatus(orderStatus);

        return orderDTO;
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


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItems> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItems> cartItems) {
        this.cartItems = cartItems;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
