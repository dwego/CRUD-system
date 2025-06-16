package com.dweg0.crud.crudsystem.core.domain.state;

import com.dweg0.crud.crudsystem.core.domain.Order;

public interface OrderStatus {
    void cancel(Order order);
    void ship(Order order);
    void deliver(Order order);
    String getName();
}