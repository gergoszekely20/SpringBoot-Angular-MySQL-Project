package com.example.GuitarSite.controller;

import com.example.GuitarSite.entity.dto.OrderDTO;
import com.example.GuitarSite.entity.enums.OrderStatus;
import com.example.GuitarSite.service.OrderServ;
import com.example.GuitarSite.entity.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * this is the OrderController class that makes the connection with the interface and here we can see he http requests mapped
 * also here we can see the endpoints for each methode that was implemented in the OrderServImp
 */
@RestController
public class OrderController {

    @Autowired
    private OrderServ orderService;
    /**
     * Controller handling order-related HTTP requests.
     * Adds a new order via HTTP POST request.
     */
    @PostMapping("/addOrder")
    public Order addOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }
    /**
     * Controller handling bulk order addition via HTTP POST request.
     */
    @PostMapping("/addOrders")
    public List<Order> addOrders(@RequestBody List<Order> orders) {
        return orderService.saveOrders(orders);
    }
    /**
     * Retrieves all orders via HTTP GET request.
     */
    @GetMapping("/showOrders")
    public List<OrderDTO> findOrders() {

        List<Order> orders = orderService.getOrders();
        List<OrderDTO> orderDTOS = new ArrayList<>();

        for (Order order : orders) {

            OrderDTO orderDTO = null;
            if (order.getOrderStatus() != OrderStatus.PENDING) {
            }
                orderDTO = new OrderDTO(order.getIdOrder(), order.getOrderDescription(), order.getDate(), order.getAmount(), order.getAddress(), order.getTotalAmount(), order.getDiscount(), order.getOrderStatus());

            orderDTOS.add(orderDTO);
        }
        return orderDTOS;
    }
    /**
     * Retrieves an order by ID via HTTP GET request.
     */
    @GetMapping("/orderById/{id}")
    public Order findOrderById(@PathVariable int id) {
        return orderService.getOrderById(id);
    }
    /**
     * Updates an order via HTTP PUT request.
     */

    @PutMapping("/orderUpdate")
    public Order updateOrder(@RequestBody Order order) {
        return orderService.updateOrder(order);
    }

    /**
     * Deletes an order by ID via HTTP DELETE request.
     */
    @DeleteMapping("/orderDelete/{id}")
    public String deleteOrder(@PathVariable int id) {
        return orderService.deleteOrder(id);
    }
}
