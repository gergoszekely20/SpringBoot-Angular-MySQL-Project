package com.example.GuitarSite.repository.repositoryImpl;

import com.example.GuitarSite.event.OrderSentEvent;
import com.example.GuitarSite.entity.entity.Order;
import com.example.GuitarSite.repository.OrderRep;
import com.example.GuitarSite.repository.repositoryContract.OrderRepContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepContractImpl implements OrderRepContract {
    private OrderRep repository;
    private ApplicationEventPublisher eventPublisher;

    @Autowired

    public OrderRepContractImpl(OrderRep repository, ApplicationEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    /**
     * this is a post http request that saves an order maded by an user
     *
     * @param order
     * @return
     */
    public Order orderSave(Order order) {
        Order newOrder = repository.save(order);

        eventPublisher.publishEvent(new OrderSentEvent(this, newOrder));

        return newOrder;
    }

    /**
     * this is a post http request that saves us all the orders that are new
     *
     * @param orders
     * @return
     */
    public List<Order> ordersToSave(List<Order> orders) {
        return repository.saveAll(orders);
    }

    /**
     * this is a get http request that shows us all the orders
     *
     * @return
     */
    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    /**
     * this is a get http request that helps us to find order by id
     *
     * @param id
     * @return
     */
    public Order orderById(int id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * this is a delete http request that deletes the order by the given id
     *
     * @param id
     * @return
     */
    public String orderDelete(int id) {
        repository.deleteById(id);
        return "Order deleted successfully";
    }

    /**
     * this is a put http request that finds the order by id and updates the modified filds
     *
     * @param order
     * @return
     */
    public Order orderUpdate(Order order) {
        Order existingOrder = repository.findById(order.getIdOrder()).orElse(null);
        if (existingOrder != null) {
            existingOrder.setOrderDescription(order.getOrderDescription());
            existingOrder.setDate(order.getDate());
            existingOrder.setAmount(order.getAmount());
            existingOrder.setAddress(order.getAddress());
            existingOrder.setPayment(order.getPayment());
            existingOrder.setTotalAmount(order.getTotalAmount());
            existingOrder.setDiscount(order.getDiscount());
            existingOrder.setOrderStatus(order.getOrderStatus());
            return repository.save(existingOrder);
        } else {
            return null;
        }
    }
}
