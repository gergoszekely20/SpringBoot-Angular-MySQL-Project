package com.example.GuitarSite.listener;

import com.example.GuitarSite.event.UserRegisteredEvent;
import com.example.GuitarSite.entity.entity.User;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationListener {
    /**
     * this is a listener that follows the UserRegisteredEvent and sends a welcome email
     * @param event
     */
    @EventListener
    public void handleUserRegisteredEvent(UserRegisteredEvent event) {
        User registeredUser = event.getUser();

        System.out.println("Welcome email sent to the new user registered with the following email: " + registeredUser.getEmail());
    }
}
