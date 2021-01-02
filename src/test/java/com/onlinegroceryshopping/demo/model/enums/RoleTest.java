package com.onlinegroceryshopping.demo.model.enums;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class RoleTest {

    @Test
    void getAuthorityTest() {
        Role role = Role.USER;

        assertEquals(Role.USER.toString(), role.getAuthority());
        assertNotEquals(Role.ADMIN.toString(), role.getAuthority());
    }

    @Test
    void valuesTest() {
        Role[] expectedRoles = {Role.ADMIN, Role.USER};
        Role[] actualRoles = Role.values();

        assertEquals(Arrays.toString(expectedRoles), Arrays.toString(actualRoles));
    }

    @Test
    void valueOfTest() {
        Role expectedRole = Role.valueOf("ADMIN");
        Role notExpectedRole = Role.valueOf("USER");
        Role actualRole = Role.ADMIN;

        assertEquals(expectedRole, actualRole);
        assertNotEquals(notExpectedRole, actualRole);
    }
}