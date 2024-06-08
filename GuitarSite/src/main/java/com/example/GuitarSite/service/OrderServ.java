package com.example.GuitarSite.service;

import com.example.GuitarSite.entity.entity.Order;

import java.util.List;

/**
 * an interface for the OrderServImp
 * here are implemented the methods that are used in the OrderController
 */
public interface OrderServ {
    Order saveOrder(Order order);
    Order updateOrder(Order order);
    String deleteOrder(int id);
    Order getOrderById(int id);
    List<Order> getOrders();
    List<Order> saveOrders(List<Order> orders);
}
