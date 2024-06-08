package com.example.GuitarSite.repository;

import com.example.GuitarSite.entity.entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemsRep extends JpaRepository<CartItems,Integer> {
    Optional<CartItems> findByProductIdGuitarAndOrderIdOrderAndUserIdUser(long idProduct, int idOrder, long idUser);
}
