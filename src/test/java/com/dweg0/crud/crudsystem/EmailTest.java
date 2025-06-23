package com.dweg0.crud.crudsystem;

import com.dweg0.crud.crudsystem.core.domain.Email;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmailTest {

    @Test
    void testEmail() {
        assertThrows(IllegalArgumentException.class,
                () -> new Email(""));
        assertThrows(IllegalArgumentException.class,
                () -> new Email("test"));
        assertThrows(IllegalArgumentException.class,
                () -> new Email(null));
    }

}
