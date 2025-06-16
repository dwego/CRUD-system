package com.dweg0.crud.crudsystem.core.domain.state;

import com.dweg0.crud.crudsystem.core.domain.Order;
import com.dweg0.crud.crudsystem.core.domain.SendReport;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Pending implements OrderStatus{
    private final SendReport sendReport;

    public void cancel(Order order) {
        order.setStatus(new Cancelled(sendReport, order));
        sendReport.send(order);
    }

    public void ship(Order order) {
        order.setStatus(new Shipped(sendReport));
        sendReport.send(order);
    }

    public void deliver(Order order) {
        throw new IllegalStateException("Can't deliver a pending order.");
    }

    public String getName() {
        return "Pending";
    }
}
