package com.example.GuitarSite.repository.repositoryContract;

import com.example.GuitarSite.entity.entity.Order;

import java.util.List;

public interface OrderRepContract {
    Order orderSave(Order order);

    Order orderUpdate(Order order);

    String orderDelete(int id);

    Order orderById(int id);

    List<Order> getAllOrders();

    List<Order> ordersToSave(List<Order> orders);
}
