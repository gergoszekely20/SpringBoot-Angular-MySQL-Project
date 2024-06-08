package com.example.GuitarSite.service;

import com.example.GuitarSite.entity.entity.Order;
import com.example.GuitarSite.entity.enums.OrderStatus;
import com.example.GuitarSite.repository.repositoryContract.OrderRepContract;
import com.example.GuitarSite.service.implementation.OrderServImp;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
/**
 * Unit tests for the OrderServ class.
 */
public class OrderServTest {
    @Mock
    private OrderRepContract orderRepMock;
    @Mock
    private ApplicationEventPublisher eventPublisherMock;

    private OrderServ orderServ;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        orderServ = new OrderServImp(orderRepMock,eventPublisherMock);
    }
    /**
     * Tests the saveOrder method of OrderServ.
     * Verifies that the orderSave method of OrderRepContract is called with the provided order.
     * Also verifies that the returned order matches the input order.
     */
    @Test
    public void testSaveOrder() {
        //given
        Date date = new Date();
        //given
        Order order = new Order(1,"nice order",date,23,"str andrei","lei",478569,10, OrderStatus.PLACED);


        //when
        when(orderRepMock.orderSave(order)).thenReturn(order);

        //verify
        Order savedOrder = orderServ.saveOrder(order);
        Mockito.verify(orderRepMock).orderSave(order);
        assertEquals(order, savedOrder);
    }
    /**
     * Tests the saveOrders method of OrderServ.
     * Verifies that the ordersToSave method of OrderRepContract is called with the provided list of orders.
     * Also verifies that the returned list of orders matches the input list.
     */
    @Test
    public void testSaveOrders() {
        //given
        Date date = new Date();
        //given
        Order order = new Order(1,"nice order",date,23,"str andrei","lei",478569,10, OrderStatus.PLACED);
        Order order2 = new Order(2,"nice orders",date,232,"str andrei","leidc",789,98, OrderStatus.PLACED);

        List<Order> orders = new ArrayList<>();
        orders.add(order);
        orders.add(order2);


        //when
        when(orderRepMock.ordersToSave(orders)).thenReturn(orders);

        //verify
        List<Order> savedOrders = orderServ.saveOrders(orders);
        Mockito.verify(orderRepMock).ordersToSave(orders);
        assertEquals(orders, savedOrders);
    }
    /**
     * Tests the getOrders method of OrderServ.
     * Verifies that the getAllOrders method of OrderRepContract is called.
     * Also verifies that the returned list of orders matches the expected list.
     */
    @Test
    public void testGetOrders() {
        Date date = new Date();
        //given
        Order order = new Order(1,"nice order",date,23,"str andrei","lei",478569,10, OrderStatus.PLACED);
        Order order2 = new Order(2,"nice orders",date,232,"str andrei","leidc",789,98, OrderStatus.PLACED);

        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        orderList.add(order2);

        //when
        when(orderRepMock.getAllOrders()).thenReturn(orderList);

        //verify
        List<Order> retrievedOrders = orderServ.getOrders();
        Mockito.verify(orderRepMock).getAllOrders();
        assertEquals(orderList, retrievedOrders);
    }
    /**
     * Tests the getOrderById method of OrderServ.
     * Verifies that the orderById method of OrderRepContract is called with the provided order ID.
     * Also verifies that the returned order matches the expected order.
     */
    @Test
    public void testGetOrderById() {
        int orderId = 3;

        Date date = new Date();
        //given
        Order expectedOrder = new Order(1,"nice order",date,23,"str andrei","lei",478569,10, OrderStatus.PLACED);

        // When
        when(orderRepMock.orderById(orderId)).thenReturn(expectedOrder);

        // Then
        Order retrievedOrder = orderServ.getOrderById(orderId);
        assertEquals(expectedOrder, retrievedOrder);
        Mockito.verify(orderRepMock).orderById(orderId);
    }

    /**
     * Tests the updateOrder method of OrderServ.
     * Verifies that the orderById method of OrderRepContract is called with the order's ID.
     * Verifies that the orderSave method of OrderRepContract is called with the updateOrder order.
     * Also verifies that the returned order matches the updated order.
     */
    @Test
    public void testUpdateOrder() {
        Date date = new Date();
        //given
        Order order = new Order(1,"nice order",date,23,"str andrei","lei",478569,10, OrderStatus.PLACED);
        Order updatedOrder = new Order(2,"nice orders",date,232,"str andrei","leidc",789,98, OrderStatus.PLACED);


        // Mock order repository behavior
        when(orderRepMock.orderById(order.getIdOrder())).thenReturn(order);
        when(orderRepMock.orderSave(order)).thenReturn(updatedOrder);

        // Test successful update
        Order retrievedOrder = orderServ.updateOrder(order);
        assertEquals(updatedOrder, retrievedOrder);
        Mockito.verify(orderRepMock).orderById(order.getIdOrder());
        Mockito.verify(orderRepMock).orderSave(order);
    }
    /**
     * Tests the deleteOrder method of OrderServ.
     * Verifies that the orderDelete method of OrderRepContract is called with the provided order ID.
     * Also verifies that the deletion message is as expected.
     */
    @Test
    public void testDeleteOrder() {
        int orderIdToDelete = 3;
        when(orderRepMock.orderDelete(5)).thenReturn("Order deleted successfully");
        String deletionMessage = orderServ.deleteOrder(orderIdToDelete);
        assertEquals("Order deleted successfully", deletionMessage);
        Mockito.verify(orderRepMock).orderDelete(orderIdToDelete);
    }

}

