package com.dweg0.crud.crudsystem.core.domain;

import com.dweg0.crud.crudsystem.core.domain.state.OrderStatus;
import com.dweg0.crud.crudsystem.core.domain.state.Pending;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
public class Order {
    private final UUID id;
    private final User user;
    private final List<OrderItem> items;

    @Setter
    OrderStatus status;

    public Order(User user, List<OrderItem> items) {
        this.id = UUID.randomUUID();
        this.user = user;
        this.items = new ArrayList<>(items);
        this.status = new Pending();
    }

    public void addItem(OrderItem item) {
        this.items.add(item);
    }

    public void removeItem(OrderItem item) {
        this.items.remove(item);
    }

    public void cancel() {
        status.cancel(this);
    }

    public void ship() {
        status.ship(this);
    }

    public void deliver() {
        status.deliver(this);
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }


    public BigDecimal getTotal() {
        return items.stream()
                .map(OrderItem::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public String toString() {
        return "Order:" +
                "id: '" + id + '\'' +
                "user: " + user.getName() +
                "\nitems: " + items.toString() + "\n" +
                "status: " + status.getName();
    }
}
