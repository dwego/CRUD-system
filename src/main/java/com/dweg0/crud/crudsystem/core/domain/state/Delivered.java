package com.dweg0.crud.crudsystem.core.domain.state;

import com.dweg0.crud.crudsystem.core.domain.Order;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Delivered implements OrderStatus{

    public void cancel(Order order) {
        throw new IllegalStateException("Order already delivered.");
    }

    public void ship(Order order) {
        throw new IllegalStateException("Order already delivered.");
    }

    public void deliver(Order order) {

    }

    public String getName() {
        return "Delivered";
    }
}
