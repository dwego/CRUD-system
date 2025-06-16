package com.dweg0.crud.crudsystem.core.domain;

import com.dweg0.crud.crudsystem.core.domain.state.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Order {
    private User user;
    List<Product> products = new ArrayList<>();
    OrderStatus status;
}
