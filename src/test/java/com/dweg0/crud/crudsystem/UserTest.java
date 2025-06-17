package com.dweg0.crud.crudsystem;

import com.dweg0.crud.crudsystem.core.domain.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class UserTest {

    @Test
    void testUser() {

        assertAll(() -> User.builder()
                .name("test")
                .password(new Password("12345678", null))
                .email(new Email("test@gmail.com"))
                .build()
        );

        User user = User.builder()
                .name("test")
                .password(new Password("12345678", null))
                .email(new Email("test@gmail.com"))
                .build();

        System.out.println(user.toString());
    }

    @Test
    void testAddOrderSuccessfully() {
        User user = User.builder()
                .name("test-user")
                .email(new Email("test-user@gmail.com"))
                .password(new Password("validPassword", null))
                .build();

        Order order = new Order(user, List.of());
        user.addOrder(order);

        assertTrue(user.getOrders().contains(order), "The order should be added successfully.");
        assertEquals(1, user.getOrders().size(), "The orders list size should be 1 after adding.");

        System.out.println(user.toString());
    }

    @Test
    void testAddOrderWithProducts() {
        User user = User.builder()
                .name("test-user")
                .email(new Email("test-user@gmail.com"))
                .password(new Password("validPassword", null))
                .build();

        Product glass = Product.builder()
                .name("glass")
                .price(BigDecimal.valueOf(190.00))
                .stock(10)
                .build();
        Product hat = Product.builder()
                .name("hat")
                .price(BigDecimal.valueOf(150.00))
                .stock(15)
                .build();
        OrderItem glassItem = new OrderItem(glass, 2);
        OrderItem hatItem = new OrderItem(hat, 1);
        Order order = new Order(user, List.of(glassItem, hatItem));
        user.addOrder(order);

        assertTrue(user.getOrders().contains(order), "The order with products should be added successfully.");
        assertEquals(1, user.getOrders().size(), "The orders list size should be 1 after adding.");
        assertEquals(2, order.getItems().size(), "The order should contain 2 products.");

        System.out.println(user.toString());
    }

    @Test
    void testRemoveOrderSuccessfully() {
        User user = User.builder()
                .name("test-user")
                .email(new Email("test-user@gmail.com"))
                .password(new Password("validPassword", null))
                .build();

        Order order = new Order(user, List.of());
        user.addOrder(order);
        user.removeOrder(order);

        assertFalse(user.getOrders().contains(order), "The order should be removed successfully.");
        assertEquals(0, user.getOrders().size(), "The orders list size should be 0 after removing.");
    }

    @Test
    void testRemoveNonExistentOrderThrowsException() {
        User user = User.builder()
                .name("test-user")
                .email(new Email("test-user@gmail.com"))
                .password(new Password("validPassword", null))
                .build();

        Order order = new Order(user, List.of());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            user.removeOrder(order);
        });

        assertEquals("Order does not exist", exception.getMessage(), "The exception message should match.");
    }

    @Test
    void testAddDuplicateOrderThrowsException() {
        User user = User.builder()
                .name("test-user")
                .email(new Email("test-user@gmail.com"))
                .password(new Password("validPassword", null))
                .build();

        Order order = new Order(user, List.of());
        user.addOrder(order);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            user.addOrder(order);
        });

        assertEquals("Order already exists", exception.getMessage(), "The exception message should match.");
    }
}
