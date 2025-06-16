package com.dweg0.crud.crudsystem.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Product {
    private final String name;
    private final String description;
    private final double price;
    private int stock;

    public void decreaseStock(int amount) {
        stock-=amount;
    }
}


