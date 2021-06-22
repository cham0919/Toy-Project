package com.wcp.auth;

import com.wcp.user.UserDto;
import org.springframework.http.ResponseCookie;

import javax.security.auth.login.LoginException;

public interface AuthService {

    String signIn(UserDto userDto, String validateToken) throws LoginException;

    ResponseCookie logout();

    ResponseCookie privideAccessToken(String token);

}
