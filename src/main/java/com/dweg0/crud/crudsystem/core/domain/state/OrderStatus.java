package com.dweg0.crud.crudsystem.core.domain;

public interface OrderStatus {
    void proceed(Order order);
    String getName();
}
