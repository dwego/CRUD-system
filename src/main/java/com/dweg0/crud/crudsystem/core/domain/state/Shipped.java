package com.dweg0.crud.crudsystem.core.domain.state;

import com.dweg0.crud.crudsystem.core.domain.Order;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Shipped implements OrderStatus{

    public void cancel(Order order) {
        throw new IllegalStateException("Can't cancel a shipped order.");
    }

    public void ship(Order order) {
        throw new IllegalStateException("Can't ship a shipped order.");
    }

    public void deliver(Order order) {
        order.setStatus(new Delivered());
    }

    public String getName() {
        return "Shipped";
    }
}
