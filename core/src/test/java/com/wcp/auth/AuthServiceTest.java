package com.wcp.auth;

import com.wcp.security.Role;
import com.wcp.user.User;
import com.wcp.user.UserDto;
import com.wcp.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.security.auth.login.LoginException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {


    @InjectMocks
    private AuthServiceImpl authService;
    @Mock
    private UserService userService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtTokenProvider JwtTokenProvider;


    @Test
    public void signIn_Success_NoAction() throws Throwable {
        String test = "test";
        User user = new User()
                .setPassword(test)
                .setRole(Role.MEMBER);
        UserDto dto = new UserDto()
                .setId(test);

        given(userService.fetchByUserId(anyString()))
                .willReturn(user);
        given(passwordEncoder.matches(any(), any()))
                .willReturn(true);
        given(JwtTokenProvider.createToken(any(TokenDto.class)))
                .willReturn(test);

        String result = authService.signIn(dto, test);

        assertEquals(result, test);
    }

    @Test
    public void signIn_FailedValidate_ExceptionThrown(){
        String test = "test";
        User user = new User()
                .setPassword(test)
                .setRole(Role.MEMBER);
        UserDto dto = new UserDto()
                .setId(test);

        given(userService.fetchByUserId(anyString()))
                .willReturn(user);
        given(passwordEncoder.matches(any(), any()))
                .willReturn(false);

        Assertions.assertThrows(LoginException.class, () -> {
            authService.signIn(dto, test);
        });

    }
}
