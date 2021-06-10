package com.wcp.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {


    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;


    @Test
    public void signUp_Success_NotNull(){
        UserDto dto = userService.signUp(mock(UserDto.class));

        assertNotNull(dto);
    }

    @Test
    public void save_Success_IOEquals(){
        UserDto dto = mock(UserDto.class);

        UserDto returnDto = userService.save(dto);

        assertEquals(dto, returnDto);
    }


    @Test
    public void fetchById_Success_NotNull() {
        String userKey = "1";

        given(userRepository.findById(anyLong()))
                .willReturn(Optional.ofNullable(mock(User.class)));

        UserDto dto = userService.fetchById(userKey);

        assertNotNull(dto);
    }

    @Test
    public void fetchById_IsNotNumericId_ExceptionThrown() {
        String userKey = "A";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.fetchById(userKey);
        });
    }

    @Test
    public void fetchAll_Success_NotNull() {
        given(userRepository.findAll())
                .willReturn(mock(List.class));

        List<UserDto> dtos = userService.fetchAll();

        assertNotNull(dtos);
    }

    @Test
    public void update_Success_IOEquals() {
        UserDto dto = new UserDto().setKey("1");

        given(userRepository.findById(anyLong()))
                .willReturn(Optional.ofNullable(mock(User.class)));

        UserDto returnDto = userService.update(dto);

        assertEquals(dto, returnDto);
    }

    @Test
    public void update_IsNotNumericId_ExceptionThrown() {
        UserDto dto = new UserDto().setKey("A");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.update(dto);
        });
    }

    @Test
    public void delete_Success_IOEquals(){
        UserDto dto = mock(UserDto.class);

        UserDto returnDto = userService.delete(dto);

        assertEquals(dto, returnDto);
    }

    @Test
    public void deleteById_IsNotNumericId_ExceptionThrown() {
        String id = "A";

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.deleteById(id);
        });
    }

}
