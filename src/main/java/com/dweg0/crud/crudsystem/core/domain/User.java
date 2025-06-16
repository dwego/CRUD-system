package com.dweg0.crud.crudsystem.core.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class User {
    private String name;
    private Email email;
    private String password;
    private List<Order> orders = new ArrayList<>();
}
