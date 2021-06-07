package com.wcp.auth;

import com.wcp.user.UserDto;

public interface AuthService {

    String signIn(UserDto userDto, String ip) throws Throwable;

}
