package com.dweg0.crud.crudsystem.core.domain;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
public class Product {
    private final UUID id = UUID.randomUUID();
    private final String name;
    private final String description;
    private final BigDecimal price;
    private int stock;


    public void decreaseStock(int amount) {
        stock-=amount;
    }

    public String toString() {
        return
                "id: '" + id + '\'' +
                "name: '" + name + '\'' +
                "description: '" + description + '\'' +
                "price: " + price +
                "stock: " + stock +
                '}';
    }
}


