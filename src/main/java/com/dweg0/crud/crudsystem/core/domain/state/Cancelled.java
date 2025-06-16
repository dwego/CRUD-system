package com.dweg0.crud.crudsystem.core.domain.state;

import com.dweg0.crud.crudsystem.core.domain.Order;
import com.dweg0.crud.crudsystem.core.domain.SendReport;

public class Cancelled implements OrderStatus{
    private final SendReport sendReport;

    public Cancelled(SendReport sendReport, Order order) {
        this.sendReport = sendReport;
        order.getUser().getOrders().remove(order);
    }

    public void cancel(Order order) {
        throw new IllegalStateException("Can't cancel a cancelled order.");
    }

    public void ship(Order order) {
        throw new IllegalStateException("Can't cancel a cancelled order.");
    }

    public void deliver(Order order) {
        throw new IllegalStateException("Can't deliver a pending order.");
    }

    public String getName() {
        return "Cancelled";
    }
}
