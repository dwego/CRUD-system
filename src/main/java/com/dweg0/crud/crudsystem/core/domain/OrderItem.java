package com.dweg0.crud.crudsystem.core.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class OrderItem {
    @Setter(AccessLevel.NONE)
    private final UUID id;

    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        setQuantity(quantity);
        this.id = UUID.randomUUID();
        this.product = product;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public String toString() {
        return
                "id: '" + id + '\'' +
                ",\nProducts: " + product.toString() + "\n" +
                ", quantity: " + quantity;
    }
}
