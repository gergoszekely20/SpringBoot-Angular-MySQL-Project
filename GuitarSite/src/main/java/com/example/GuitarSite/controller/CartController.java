package com.example.GuitarSite.controller;

import com.example.GuitarSite.entity.dto.AddProductToCartDTO;
import com.example.GuitarSite.entity.dto.OrderDTO;
import com.example.GuitarSite.entity.dto.PlaceOrderDTO;
import com.example.GuitarSite.service.CartServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {
    @Autowired
    private CartServ cartServ;

    @PostMapping("/addToCart")
    public ResponseEntity addProductToCart(@RequestBody AddProductToCartDTO addProductToCartDTO) {
        return cartServ.addProductToCart(addProductToCartDTO);
    }

    @GetMapping("/getCartDetails/{idUser}")
    public ResponseEntity getCartByUserID(@PathVariable int idUser) {
        OrderDTO orderDTO = cartServ.getCartByUSerID(idUser);

        if (orderDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(orderDTO);
    }

    @PostMapping("/increaseQuantity")
    public ResponseEntity increaseQuantity(@RequestBody AddProductToCartDTO addProductToCartDTO) {
        OrderDTO orderDTO = cartServ.increaseProductQuantity(addProductToCartDTO);

        if (orderDTO == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Could not increase quantity");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(orderDTO);
    }

    @PostMapping("/decreaseQuantity")
    public ResponseEntity decreaseQuantity(@RequestBody AddProductToCartDTO addProductToCartDTO) {
        OrderDTO orderDTO = cartServ.decreaseProductQuantity(addProductToCartDTO);

        if (orderDTO == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Could not decrease quantity");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(orderDTO);
    }

    @PostMapping("/saveOrder")
    public ResponseEntity saveOrder(@RequestBody PlaceOrderDTO placeOrderDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartServ.placeOrder(placeOrderDTO));
    }
    @GetMapping("/changeStatus/{idOrder}/{status}")
    public ResponseEntity changeORderStatus(@PathVariable int idOrder,@PathVariable String status ){
        OrderDTO orderDTO = cartServ.changeOrderStatus(idOrder,status);
        if(orderDTO == null){
            return new ResponseEntity<>("Something went wrong!",HttpStatus.BAD_REQUEST);
        }
        return  ResponseEntity.status(HttpStatus.OK).body(orderDTO);
    }
    @GetMapping("/myorders/{idUser}")
    public ResponseEntity<List<OrderDTO>> getMyPlacedOrders(@PathVariable int idUser){
        return ResponseEntity.ok(cartServ.getMyPlacedOrders(idUser));
    }

}
