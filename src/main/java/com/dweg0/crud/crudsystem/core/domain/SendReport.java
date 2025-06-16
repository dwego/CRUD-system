package com.dweg0.crud.crudsystem.core.domain;

public interface SendReport {
    void send(Order order);
    String getReport();
}
