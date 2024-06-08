package com.example.GuitarSite.repository;

import com.example.GuitarSite.entity.entity.Order;
import com.example.GuitarSite.entity.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * this is the interface where the OrderRep extends the JpaRepository
 * where we can find the repository functions which communicates with the database
 */
public interface OrderRep extends JpaRepository<Order, Integer> {
    Order findByUserIdUserAndOrderStatus(int idUser, OrderStatus orderStatus);
    List<Order> findByUserIdUserAndOrderStatusIn(int idUser, List<OrderStatus> orderStatus);
}
