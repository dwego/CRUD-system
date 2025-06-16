package com.dweg0.crud.crudsystem.core.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Builder
public class User {
    private String name;
    private Email email;
    private Password password;
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        if (orders.contains(order)) {
            throw new IllegalArgumentException("Order already exists");
        }
        orders.add(order);
    }

    public void removeOrder(Order order) {
        if (!orders.contains(order)) {
            throw new IllegalArgumentException("Order does not exist");
        }
        orders.remove(order);
    }

    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }

    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email.getEmail() + '\'' +
                ", orders=" + getOrders() +
                '}';
    }
}
