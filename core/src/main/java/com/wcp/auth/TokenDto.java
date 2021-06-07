package com.wcp.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TokenDto {

    private String id;
    private String role;
    private String ip;
    private String uuid;
    private String token;
}
