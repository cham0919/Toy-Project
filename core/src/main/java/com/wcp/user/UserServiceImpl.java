package com.wcp.user;


import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.wcp.mapper.UserMapper.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto signUp(UserDto userDto){
        User user = USER_MAPPER.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return USER_MAPPER.toDto(user);
    }

    @Override
    public UserDto save(UserDto dto){
        User user = USER_MAPPER.toEntity(dto);
        userRepository.save(user);
        return dto;
    }

    @Override
    public User fetchByUserId(String userId) {
        return userRepository.findById(userId);
    }

    @Override
    public UserDto fetchById(String userKey) {
        User user = fetchById(Long.valueOf(userKey));
        return USER_MAPPER.toDto(user);
    }

    private User fetchById(Long userKey) {
        return userRepository.findById(userKey).get();
    }

    @Override
    public List<UserDto> fetchAll() {
        List<User> users = userRepository.findAll();
        List<UserDto> dtos = new ArrayList<>();
        users.forEach(v -> {
            dtos.add( USER_MAPPER.toDto(v) );
        });
        return dtos;
    }

    @Override
    public UserDto update(UserDto dto) {
        User user = fetchById(Long.valueOf(dto.getKey()));
        USER_MAPPER.updateFromDto(dto, user);
        return dto;
    }

    // 회원데이터 삭제 delete
    @Override
    public UserDto delete(UserDto dto){
        User user = USER_MAPPER.toEntity(dto);
        userRepository.delete(user);
        return dto;
    }

    @Override
    public void deleteById(String id) {
        deleteById(Long.valueOf(id));
    }

    private void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return userRepository.count();
    }


}
