package com.dweg0.crud.crudsystem.core.domain.state;

import com.dweg0.crud.crudsystem.core.domain.Order;
import com.dweg0.crud.crudsystem.core.domain.SendReport;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Delivered implements OrderStatus{
    private final SendReport sendReport;

    public void cancel(Order order) {
        throw new IllegalStateException("Order already delivered.");
    }

    public void ship(Order order) {
        throw new IllegalStateException("Order already delivered.");
    }

    public void deliver(Order order) {
        order.getUser().getOrders().remove(order);
        sendReport.send(order);
    }

    public String getName() {
        return "Delivered";
    }
}
