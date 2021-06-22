package com.wcp.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Token {

    ACCESSTOKEN("accessToken"),
    VALIDATETOKEN("validateToken");

    private String token;
}
