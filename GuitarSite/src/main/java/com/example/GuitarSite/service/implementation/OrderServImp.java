package com.example.GuitarSite.service.implementation;

import com.example.GuitarSite.event.OrderSentEvent;
import com.example.GuitarSite.repository.repositoryContract.OrderRepContract;
import com.example.GuitarSite.service.OrderServ;
import com.example.GuitarSite.entity.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * this is the class OrderServImp where I implemented the get, post, put and delete endpoints
 */
@Service
public class OrderServImp implements OrderServ {
    private OrderRepContract repository;
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    public OrderServImp(OrderRepContract repository, ApplicationEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    /**
     * this is a post http request that saves an order maded by an user
     *
     * @param order
     * @return
     */
    public Order saveOrder(Order order) {
        Order newOrder = repository.orderSave(order);

        eventPublisher.publishEvent(new OrderSentEvent(this, newOrder));

        return newOrder;
    }

    /**
     * this is a post http request that saves us all the orders that are new
     *
     * @param orders
     * @return
     */
    public List<Order> saveOrders(List<Order> orders) {
        return repository.ordersToSave(orders);
    }

    /**
     * this is a get http request that shows us all the orders
     *
     * @return
     */
    public List<Order> getOrders() {
        return repository.getAllOrders();
    }

    /**
     * this is a get http request that helps us to find order by id
     *
     * @param id
     * @return
     */
    public Order getOrderById(int id) {
        return repository.orderById(id);
    }

    /**
     * this is a delete http request that deletes the order by the given id
     *
     * @param id
     * @return
     */
    public String deleteOrder(int id) {
        repository.orderDelete(id);
        return "Order deleted successfully";
    }

    /**
     * this is a put http request that finds the order by id and updates the modified filds
     *
     * @param order
     * @return
     */
    public Order updateOrder(Order order) {
        Order existingOrder = repository.orderById(order.getIdOrder());
        if (existingOrder != null) {
            existingOrder.setOrderDescription(order.getOrderDescription());
            existingOrder.setDate(order.getDate());
            existingOrder.setAmount(order.getAmount());
            existingOrder.setAddress(order.getAddress());
            existingOrder.setPayment(order.getPayment());
            existingOrder.setTotalAmount(order.getTotalAmount());
            existingOrder.setDiscount(order.getDiscount());
            return repository.orderSave(existingOrder);
        } else {
            return null;
        }
    }
}
