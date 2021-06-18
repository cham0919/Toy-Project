package com.wcp.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Token {

    ACCESSTOKEN("accesstoken"),
    VALIDATETOKEN("validatetoken");

    private String token;
}
