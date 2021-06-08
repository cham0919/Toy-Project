package com.wcp.auth;


import com.wcp.common.AESUtils;
import com.wcp.mapper.UserMapper;
import com.wcp.user.User;
import com.wcp.user.UserDto;
import com.wcp.user.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider JwtTokenProvider;


    @Override
    public String signIn(UserDto userDto, String ip) throws Throwable {
        User user = UserMapper.INSTANCE.toEntity(userDto);
        User fetchUser = userService.fetchByUserId(user.getId());
        if (validatePassword(user, fetchUser)) {
            TokenDto tokenDto = initTokenDto(fetchUser, ip);
            return JwtTokenProvider.createToken(tokenDto);
        } else {
            throw new LoginException();
        }
    }

    private TokenDto initTokenDto(User user, String ip) throws Throwable {
        return TokenDto.builder()
                .id(user.getId())
                .role(user.getRole().getValue())
                .ip(AESUtils.encrypt(ip))
                .uuid(String.valueOf(UUID.randomUUID()))
                .build();
    }

    private boolean validatePassword(User user, User fetchUser) {
        boolean isValidPassword = passwordEncoder.matches(user.getPassword(), fetchUser.getPassword());
        return isValidPassword;
    }

}
