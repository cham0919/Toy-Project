package com.wcp.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TokenDto {

    private String key;
    private String role;
    private String accessToken;
    private String validateToken;
}
