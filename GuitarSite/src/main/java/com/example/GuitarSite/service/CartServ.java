package com.example.GuitarSite.service;

import com.example.GuitarSite.entity.dto.AddProductToCartDTO;
import com.example.GuitarSite.entity.dto.OrderDTO;
import com.example.GuitarSite.entity.dto.PlaceOrderDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartServ {
    ResponseEntity addProductToCart(AddProductToCartDTO addProductToCartDTO);
    OrderDTO getCartByUSerID(int idUser);
    OrderDTO increaseProductQuantity(AddProductToCartDTO addProductToCartDTO);
    OrderDTO decreaseProductQuantity(AddProductToCartDTO addProductToCartDTO);
    OrderDTO placeOrder(PlaceOrderDTO placeOrderDTO);

    OrderDTO changeOrderStatus(int idOrder, String status);
    List<OrderDTO> getMyPlacedOrders(int idUser);
}
