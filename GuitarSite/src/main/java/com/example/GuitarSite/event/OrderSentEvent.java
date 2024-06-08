package com.example.GuitarSite.event;

import com.example.GuitarSite.entity.entity.Order;
import org.springframework.context.ApplicationEvent;

/**
 * this is the OrderSentEvent class
 */
public class OrderSentEvent extends ApplicationEvent {
    private final Order order;

    /**
     * which will be published when a user places an order
     * @param source
     * @param order
     */

    public OrderSentEvent(Object source, Order order) {
        super(source);
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}
