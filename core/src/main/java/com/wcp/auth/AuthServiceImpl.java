package com.wcp.auth;


import com.wcp.user.User;
import com.wcp.user.UserDto;
import com.wcp.user.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.UUID;


import static com.wcp.auth.Token.*;
import static com.wcp.mapper.UserMapper.USER_MAPPER;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider JwtTokenProvider;


    @Override
    public String signIn(UserDto userDto, String validateToken) throws LoginException {
        User user = USER_MAPPER.toEntity(userDto);
        User fetchUser = userService.fetchByUserId(user.getId());
        if (validatePassword(user, fetchUser)) {
            updateDto(fetchUser, userDto);
            TokenDto tokenDto = initTokenDto(fetchUser, validateToken);
            return JwtTokenProvider.createToken(tokenDto);
        } else {
            throw new LoginException();
        }
    }

    private void updateDto(User fetchUser, UserDto userDto) {
        userDto.setPassword(null)
                .setRole(fetchUser.getRole())
                .setNickname(fetchUser.getNickname());
    }

    @Override
    public ResponseCookie logout() {
        return ResponseCookie.from(ACCESSTOKEN.getToken(), null)
                .sameSite("Strict")
                .maxAge(0)
                .path("/wcp")
                .httpOnly(true)
                .build();
    }

    @Override
    public ResponseCookie privideAccessToken(String token) {
        return ResponseCookie.from(ACCESSTOKEN.getToken(), token)
                .sameSite("Strict")
                .path("/wcp")
                .httpOnly(true)
                .build();
    }

    private TokenDto initTokenDto(User user, String validateToken){
        return TokenDto.builder()
                .key(String.valueOf(user.getKey()))
                .role(user.getRole().getValue())
                .validateToken(validateToken)
                .build();
    }

    private boolean validatePassword(User user, User fetchUser) {
        boolean isValidPassword = passwordEncoder.matches(user.getPassword(), fetchUser.getPassword());
        return isValidPassword;
    }

}
