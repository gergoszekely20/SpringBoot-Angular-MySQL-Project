package com.example.GuitarSite.repository;

import com.example.GuitarSite.entity.entity.Order;
import com.example.GuitarSite.entity.enums.OrderStatus;
import com.example.GuitarSite.repository.repositoryContract.OrderRepContract;
import com.example.GuitarSite.repository.repositoryImpl.OrderRepContractImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
/**
 * Unit tests for the {@link OrderRepContract} class.
 */
public class OrderRepContractTest {
    @Mock
    private OrderRep orderRepMock;
    @Mock
    private ApplicationEventPublisher eventPublisherMock;

    private OrderRepContract orderRepContract;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        orderRepContract = new OrderRepContractImpl(orderRepMock,eventPublisherMock);
    }
    /**
     * Tests the {@link OrderRepContract #orderSave(Order)} method.
     * Verifies that the {@link OrderRep #save(Order)} method of OrderRep is called with the provided order.
     * Also verifies that the returned order matches the input order.
     */
    @Test
    public void testOrderSave() {
        Date date = new Date();
        //given
        Order order = new Order(1,"nice order",date,23,"str andrei","lei",478569,10, OrderStatus.PLACED);

        //when
        when(orderRepMock.save(order)).thenReturn(order);

        //verify
        Order savedOrder = orderRepContract.orderSave(order);
        Mockito.verify(orderRepMock).save(order);
        assertEquals(order, savedOrder);
    }
    /**
     * Tests the {@link OrderRepContract #ordersToSave(List)} method.
     * Verifies that the {@link OrderRep #saveAll(List)} method of OrderRep is called with the provided list of orders.
     * Also verifies that the returned list of orders matches the input list.
     */
    @Test
    public void testOrdersToSave() {
        //given
        Date date = new Date();
        //given
        Order order = new Order(1,"nice order",date,23,"str andrei","lei",478569,10, OrderStatus.PLACED);
        Order order2 = new Order(2,"nice orders",date,232,"str andrei","leidc",789,98, OrderStatus.PLACED);
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        orders.add(order2);


        //when
        when(orderRepMock.saveAll(orders)).thenReturn(orders);

        //verify
        List<Order> savedOrders = orderRepContract.ordersToSave(orders);
        Mockito.verify(orderRepMock).saveAll(orders);
        assertEquals(orders, savedOrders);
    }
    /**
     * Tests the {@link OrderRepContract #getAllOrders()} method.
     * Verifies that the {@link OrderRep #findAll()} method of the OrderRep implementation is called.
     * Also verifies that the returned list of orders matches the expected list.
     */
    @Test
    public void testGetAllOrders() {
        Date date = new Date();
        //given
        Order order = new Order(1,"nice order",date,23,"str andrei","lei",478569,10, OrderStatus.PLACED);
        Order order2 = new Order(2,"nice orders",date,232,"str andrei","leidc",789,98, OrderStatus.PLACED);

        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        orderList.add(order2);

        //when
        when(orderRepMock.findAll()).thenReturn(orderList);

        //verify
        List<Order> retrievedOrders = orderRepContract.getAllOrders();
        Mockito.verify(orderRepMock).findAll();
        assertEquals(orderList, retrievedOrders);
    }
    /**
     * Tests the {@link OrderRepContract #orderById(int)} method.
     * Verifies that the {@link OrderRep #findById(int)} method of the OrderRep implementation is called with the provided order ID.
     * Also verifies that the returned order matches the expected order (if present).
     */
    @Test
    public void testOrderById() {
        int orderId = 3;
        Date date = new Date();
        //given
        Order expectedOrder = new Order(1,"nice order",date,23,"str andrei","lei",478569,10, OrderStatus.PLACED);



        // When
        when(orderRepMock.findById(orderId)).thenReturn(Optional.of(expectedOrder));

        // Then
        Order retrievedOrder = orderRepContract.orderById(orderId);
        assertEquals(expectedOrder, retrievedOrder);
        Mockito.verify(orderRepMock).findById(orderId);
    }

    /**
     * Tests the {@link OrderRepContract #orderUpdate(Order)} method.
     * Verifies that the {@link OrderRep #findById(int)} method of the OrderRep implementation is called with the order's ID to retrieve the order.
     * Verifies that the retrieved order is not null.
     * Then, verifies that the {@link OrderRep #save(Order)} method of the OrderRep implementation is called with the updated order information.
     * Also verifies that the returned order matches the updated order.
     */
    @Test
    public void testOrderUpdate() {
        Date date = new Date();
        //given
        Order order = new Order(1,"nice order",date,23,"str andrei","lei",478569,10, OrderStatus.PLACED);
        Order updatedOrder = new Order(2,"nice orders",date,232,"str andrei","leidc",789,98, OrderStatus.PLACED);


        // Mock order repository behavior
        when(orderRepMock.findById(order.getIdOrder())).thenReturn(Optional.of(order));
        when(orderRepMock.save(order)).thenReturn(updatedOrder);

        // Test successful update
        Order retrievedOrder = orderRepContract.orderUpdate(order);
        assertEquals(updatedOrder, retrievedOrder);
        Mockito.verify(orderRepMock).findById(order.getIdOrder());
        Mockito.verify(orderRepMock).save(order);
    }
    /**
     * Tests the {@link OrderRepContract#orderDelete(int)} method.
     * Verifies that the {@link OrderRep #deleteById(int)} method of the OrderRep implementation is called with the provided order ID.
     * Also verifies that the deletion message is "Order deleted successfully".
     */
    @Test
    public void testOrderDelete() {
        int orderIdToDelete = 3;
        Mockito.doNothing().when(orderRepMock).deleteById(orderIdToDelete);
        String deletionMessage = orderRepContract.orderDelete(orderIdToDelete);
        assertEquals("Order deleted successfully", deletionMessage);
        Mockito.verify(orderRepMock).deleteById(orderIdToDelete);
    }

}

