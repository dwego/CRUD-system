package com.dweg0.crud.crudsystem.core.domain;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class Product {
    private final UUID id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private int stock;

    public Product(String name, String description, BigDecimal price, int stock) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public void decreaseStock(int amount) {
        stock-=amount;
    }
}


