package com.wcp.error;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(500, "check your key");

    private final int status;
    private final String message;
}
