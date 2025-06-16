package com.dweg0.crud.crudsystem;

import static org.junit.jupiter.api.Assertions.*;

import com.dweg0.crud.crudsystem.core.domain.Email;
import com.dweg0.crud.crudsystem.core.domain.User;
import org.junit.jupiter.api.Test;


public class UserTest {

    @Test
    void testUser() {
        assertAll(() -> User.builder()
                .name("test")
                .password("123456")
                .email(new Email("test@gmail.com"))
                .build());
    }
}
