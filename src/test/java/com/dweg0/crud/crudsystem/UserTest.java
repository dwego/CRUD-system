package com.dweg0.crud.crudsystem;

import static org.junit.jupiter.api.Assertions.*;

import com.dweg0.crud.crudsystem.core.domain.Email;
import com.dweg0.crud.crudsystem.core.domain.Order;
import com.dweg0.crud.crudsystem.core.domain.Password;
import com.dweg0.crud.crudsystem.core.domain.User;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;


public class UserTest {

    @Test
    void testUser() {

        assertAll(() -> User.builder()
                .name("test")
                .password(new Password("12345678", null))
                .email(new Email("test@gmail.com"))
                .orders(null)
                .build()
        );

        User user = User.builder()
                .name("test")
                .password(new Password("12345678", null))
                .email(new Email("test@gmail.com"))
                .orders(null)
                .build();

        System.out.println(user.toString());
    }
}
