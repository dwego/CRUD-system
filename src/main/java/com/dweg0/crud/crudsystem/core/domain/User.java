package com.dweg0.crud.crudsystem.core.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {
    private String name;
    private Email email;
    private String password;
}
