package com.dweg0.crud.crudsystem.core.domain.state;

import com.dweg0.crud.crudsystem.core.domain.Order;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Pending implements OrderStatus{

    public void cancel(Order order) {
        order.setStatus(new Cancelled());
    }

    public void ship(Order order) {
        order.setStatus(new Shipped());
    }

    public void deliver(Order order) {
        throw new IllegalStateException("Can't deliver a pending order.");
    }

    public String getName() {
        return "Pending";
    }
}
