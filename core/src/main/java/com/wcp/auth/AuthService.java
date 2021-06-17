package com.wcp.auth;

import com.wcp.user.UserDto;

public interface AuthService {

    String signIn(UserDto userDto, String validateToken) throws Throwable;

}
