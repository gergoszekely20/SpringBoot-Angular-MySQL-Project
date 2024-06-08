package com.example.GuitarSite.service.implementation;

import com.example.GuitarSite.entity.dto.UserRegistrationDTO;
import com.example.GuitarSite.entity.enums.UserType;
import com.example.GuitarSite.event.UserRegisteredEvent;
import com.example.GuitarSite.entity.entity.LoginData;
import com.example.GuitarSite.entity.entity.User;
import com.example.GuitarSite.repository.OrderRep;
import com.example.GuitarSite.repository.UserRep;
import com.example.GuitarSite.service.AuthServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.context.ApplicationEventPublisher;

@Service
public class AuthServiceImp implements AuthServ {
    @Autowired
    private UserRep userRep;
    @Autowired
    private OrderRep orderRep;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public AuthServiceImp(UserRep userRep, ApplicationEventPublisher eventPublisher){
        this.userRep = userRep;
        this.eventPublisher = eventPublisher;
    }

    /**
     * this method verifies that if the password and the email is in the database, it means they are correct
     *
     * @param loginData
     * @return
     */
    @Override
    public User login(LoginData loginData) {
        return userRep.findByEmailAndPassword(loginData.getEmail(), loginData.getPassword());
    }

    /**
     * the register method adds a new user to the database if there is no user with the specified email
     *
     * @param user
     * @return
     */

    @Override
    public User register(UserRegistrationDTO user) {
        User existingUser = userRep.findByEmail(user.getEmail());

        User userEntity = new User(user.getName(), user.getEmail(), user.getAddress(), user.getTelefon(), user.getPassword(), UserType.USER);

        if (existingUser == null) {
            User createdUser = userRep.save(userEntity);

            eventPublisher.publishEvent(new UserRegisteredEvent(this, createdUser));

            return createdUser;
        }

        return null;
    }
}
