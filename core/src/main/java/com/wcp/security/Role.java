package com.wcp.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum Role {

    ADMIN("ROLE_ADMIN"),
    MEMBER("ROLE_MEMBER"),
    ANONYMOUS("ROLE_ANONYMOUS");

    private String value;

    public static Role enumOf(String role) {
        return Arrays.stream(Role.values())
                .filter(t -> t.getValue().equalsIgnoreCase(role))
                .findAny().orElse(null);
    }
}
