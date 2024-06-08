package com.example.GuitarSite.listener;

import com.example.GuitarSite.event.OrderSentEvent;
import com.example.GuitarSite.entity.entity.Order;
import com.example.GuitarSite.entity.entity.User;
import com.example.GuitarSite.service.UserServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NewOrderListener {
    @Autowired
    UserServ userServ;

    /**
     * this is a listener that follows the OrderSentEvent and sends a notification with the order details
     * @param event
     */
    @EventListener
    public void handleUserRegisteredEvent(OrderSentEvent event) {
        Order newOrder = event.getOrder();
        User user = userServ.getUserById(newOrder.getUser().getIdUser());

        System.out.println("A new order with id " + newOrder.getIdOrder() + " from " + user.getName() + " was successfully placed");
    }
}
