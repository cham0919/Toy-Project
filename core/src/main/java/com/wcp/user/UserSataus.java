package com.wcp.user;

import com.wcp.security.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum UserSataus {

    ACTIVATE("S"),
    DEACTIVATE("F"),
    INACTIVATE("H");

    private String value;

    public static UserSataus enumOf(String status) {
        return Arrays.stream(UserSataus.values())
                .filter(t -> t.getValue().equalsIgnoreCase(status))
                .findAny().orElse(null);
    }
}
