package com.wcp.token;

import com.wcp.user.UserDto;

import java.time.LocalDateTime;

public class CertificationTokenDto {

    private String key;
    private UserDto user;
    private String token;
    private String type;
    private String expired;
    private LocalDateTime createdAt;
    private LocalDateTime usedAt;

}
