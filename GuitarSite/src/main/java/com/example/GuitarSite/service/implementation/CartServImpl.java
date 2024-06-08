package com.example.GuitarSite.service.implementation;

import com.example.GuitarSite.entity.entity.CartItems;
import com.example.GuitarSite.entity.entity.Guitar;
import com.example.GuitarSite.entity.entity.Order;
import com.example.GuitarSite.entity.entity.User;
import com.example.GuitarSite.entity.dto.AddProductToCartDTO;
import com.example.GuitarSite.entity.dto.CartItemsDTO;
import com.example.GuitarSite.entity.dto.OrderDTO;
import com.example.GuitarSite.entity.dto.PlaceOrderDTO;
import com.example.GuitarSite.entity.enums.OrderStatus;
import com.example.GuitarSite.repository.CartItemsRep;
import com.example.GuitarSite.repository.GuitarRep;
import com.example.GuitarSite.repository.OrderRep;
import com.example.GuitarSite.repository.UserRep;
import com.example.GuitarSite.service.CartServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServImpl implements CartServ {
    private OrderRep orderRep;
    private UserRep userRep;
    private CartItemsRep cartItemsRep;
    private GuitarRep guitarRep;

    @Autowired
    public CartServImpl(OrderRep orderRep, UserRep userRep, CartItemsRep cartItemsRep, GuitarRep guitarRep) {
        this.orderRep = orderRep;
        this.userRep = userRep;
        this.cartItemsRep = cartItemsRep;
        this.guitarRep = guitarRep;
    }

    public ResponseEntity addProductToCart(AddProductToCartDTO addProductToCartDTO) {
        Order activeOrder = orderRep.findByUserIdUserAndOrderStatus(addProductToCartDTO.getIdUser(), OrderStatus.PENDING);

        if (activeOrder == null) {
            Order order = new Order();
            Optional<User> user = userRep.findById(addProductToCartDTO.getIdUser());

            if (user.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User is not found");
            }

            order.setAmount(0L);
            order.setTotalAmount(0L);
            order.setDiscount(0L);
            order.setUser(user.get());
            order.setOrderStatus(OrderStatus.PENDING);
            activeOrder = orderRep.save(order);
        }

        Optional<CartItems> optionalCartItems = cartItemsRep.findByProductIdGuitarAndOrderIdOrderAndUserIdUser(
                addProductToCartDTO.getIdProduct(), activeOrder.getIdOrder(), addProductToCartDTO.getIdUser());
        Optional<Guitar> optionalGuitar = guitarRep.findByIdAndQuantityGreaterThanZero(addProductToCartDTO.getIdProduct());
        Optional<User> optionalUser = userRep.findById(addProductToCartDTO.getIdUser());

        if (optionalGuitar.isPresent() && optionalUser.isPresent()) {
            Guitar product = optionalGuitar.get();

            CartItems cartItem;

            if (optionalCartItems.isPresent()) {
                cartItem = optionalCartItems.get();
                cartItem.setQuantity(cartItem.getQuantity() + 1);

            } else {
                cartItem = new CartItems();
                cartItem.setProduct(product);
                cartItem.setPrice(product.getPrice());
                cartItem.setQuantity(1L);
                cartItem.setUser(optionalUser.get());
                cartItem.setOrder(activeOrder);

            }

            cartItemsRep.save(cartItem);

            activeOrder.setTotalAmount(activeOrder.getTotalAmount() + cartItem.getPrice());
            activeOrder.setAmount(activeOrder.getAmount() + cartItem.getPrice());
            activeOrder.getCartItems().add(cartItem);

            orderRep.save(activeOrder);

            product.setQuantity(product.getQuantity() - 1);
            guitarRep.save(product);

            return ResponseEntity.status(HttpStatus.CREATED).body(product.getQuantity());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Guitar not found");
    }

    public OrderDTO getCartByUSerID(int idUser) {
        Order activeOrder = orderRep.findByUserIdUserAndOrderStatus(idUser, OrderStatus.PENDING);

        List<CartItemsDTO> cartItemsDTOList = activeOrder.getCartItems().stream().map(CartItems::getCartDTO).collect(Collectors.toList());

        for (CartItemsDTO cartItem : cartItemsDTOList
        ) {
            Optional<Guitar> optionalGuitar = guitarRep.findById((cartItem.getIdGuitar()));
            cartItem.setCanDecreaseQuantity(cartItem.getQuantity() > 1);
            cartItem.setCanIncreaseQuantity(optionalGuitar.get().getQuantity() > 0);
        }

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setAmount(activeOrder.getAmount());
        orderDTO.setIdOrder(activeOrder.getIdOrder());
        orderDTO.setOrderStatus(activeOrder.getOrderStatus());
        orderDTO.setDiscount(activeOrder.getDiscount());
        orderDTO.setTotalAmount(activeOrder.getTotalAmount());

        orderDTO.setCartItems(cartItemsDTOList);

        return orderDTO;
    }


    public OrderDTO increaseProductQuantity(AddProductToCartDTO addProductToCartDTO) {
        Order activeOrder = orderRep.findByUserIdUserAndOrderStatus(addProductToCartDTO.getIdUser(), OrderStatus.PENDING);
        Optional<Guitar> optionalGuitar = guitarRep.findByIdAndQuantityGreaterThanZero((addProductToCartDTO.getIdProduct()));

        Optional<CartItems> optionalCartItems = cartItemsRep.findByProductIdGuitarAndOrderIdOrderAndUserIdUser(
                addProductToCartDTO.getIdProduct(), activeOrder.getIdOrder(), addProductToCartDTO.getIdUser());

        if (optionalGuitar.isPresent() && optionalCartItems.isPresent()) {
            CartItems cartItems = optionalCartItems.get();
            Guitar product = optionalGuitar.get();

            if (cartItems.getQuantity() == product.getQuantity()) {
                return null;
            }

            activeOrder.setAmount(activeOrder.getAmount() + product.getPrice());
            activeOrder.setTotalAmount(activeOrder.getTotalAmount() + product.getPrice());

            cartItems.setQuantity(cartItems.getQuantity() + 1);

            cartItemsRep.save(cartItems);
            orderRep.save(activeOrder);

            product.setQuantity(product.getQuantity() - 1);
            guitarRep.save(product);

            return activeOrder.getOrderDTO();
        }
        return null;
    }

    public OrderDTO decreaseProductQuantity(AddProductToCartDTO addProductToCartDTO) {
        Order activeOrder = orderRep.findByUserIdUserAndOrderStatus(addProductToCartDTO.getIdUser(), OrderStatus.PENDING);
        Optional<Guitar> optionalGuitar = guitarRep.findById((addProductToCartDTO.getIdProduct()));

        Optional<CartItems> optionalCartItems = cartItemsRep.findByProductIdGuitarAndOrderIdOrderAndUserIdUser(
                addProductToCartDTO.getIdProduct(), activeOrder.getIdOrder(), addProductToCartDTO.getIdUser());

        if (optionalGuitar.isPresent() && optionalCartItems.isPresent()) {
            CartItems cartItems = optionalCartItems.get();
            Guitar product = optionalGuitar.get();

            if (cartItems.getQuantity() == 1) {
                return null;
            }

            activeOrder.setAmount(activeOrder.getAmount() - product.getPrice());
            activeOrder.setTotalAmount(activeOrder.getTotalAmount() - product.getPrice());

            cartItems.setQuantity(cartItems.getQuantity() - 1);

            cartItemsRep.save(cartItems);
            orderRep.save(activeOrder);

            product.setQuantity(product.getQuantity() + 1);
            guitarRep.save(product);

            return activeOrder.getOrderDTO();
        }
        return null;
    }

    public OrderDTO placeOrder(PlaceOrderDTO placeOrderDTO) {
        Order activeOrder = orderRep.findByUserIdUserAndOrderStatus(placeOrderDTO.getIdUser(), OrderStatus.PENDING);
        Optional<User> optionalUser = userRep.findById(placeOrderDTO.getIdUser());

        if (activeOrder != null && optionalUser.isPresent()) {
            activeOrder.setOrderDescription(placeOrderDTO.getOrderDescription());
            activeOrder.setAddress(placeOrderDTO.getAddress());
            activeOrder.setDate(new Date());
            activeOrder.setOrderStatus(OrderStatus.PLACED);

            orderRep.save(activeOrder);

            return activeOrder.getOrderDTO();
        } else {
            return null;
        }
    }

    public OrderDTO changeOrderStatus(int idOrder, String status) {
        Optional<Order> orderOptional = orderRep.findById(idOrder);
        Order order;
        if (orderOptional.isPresent()) {
            order = orderOptional.get();

            if (Objects.equals(status, "SHIPPED")){
                order.setOrderStatus(OrderStatus.SHIPPED);
            }else if (Objects.equals(status, "DELEVERD")) {
                order.setOrderStatus(OrderStatus.DELEVERD);
            }
            return orderRep.save(order).getOrderDTO();
        }
        return null;
    }

    public List<OrderDTO> getMyPlacedOrders(int idUser){
        return orderRep.findByUserIdUserAndOrderStatusIn(idUser,List.of(OrderStatus.PLACED,OrderStatus.SHIPPED,OrderStatus.DELEVERD))
                        .stream().map(Order::getOrderDTO).collect(Collectors.toList());
    }
}
