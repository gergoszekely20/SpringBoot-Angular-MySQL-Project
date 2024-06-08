package com.example.GuitarSite.event;

import com.example.GuitarSite.entity.entity.User;
import org.springframework.context.ApplicationEvent;

/**
 * this is the UserRegisteredEvent class
 */
public class UserRegisteredEvent extends ApplicationEvent {
    private final User user;

    /**
     * which will be published when a user makes a registration
     * @param source
     * @param user
     */
    public UserRegisteredEvent(Object source, User user) {
        super(source);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
