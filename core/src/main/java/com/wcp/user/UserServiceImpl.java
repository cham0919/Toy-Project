package com.wcp.user;

import com.wcp.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto signUp(UserDto userDto){
        User user = UserMapper.INSTANCE.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return UserMapper.INSTANCE.toDto(user);
    }

    @Override
    public UserDto save(UserDto dto){
        User user = UserMapper.INSTANCE.toEntity(dto);
        userRepository.save(user);
        return dto;
    }

    @Override
    public User fetchByUserId(String userId) {
        return userRepository.findById(userId);
    }

    @Override
    public UserDto fetchById(String userKey) {
//        if (StringUtils.isEmpty(userKey) || !StringUtils.isNumeric(userKey)) {
//            throw new IllegalArgumentException("id should not be empty or String. Please Check Id : "+ userKey);
//        }
        User user = fetchById(Long.valueOf(userKey));
        return UserMapper.INSTANCE.toDto(user);
    }

    private User fetchById(Long userKey) {
        return userRepository.findById(userKey).get();
    }

    @Override
    public List<UserDto> fetchAll() {
        List<User> users = userRepository.findAll();
        List<UserDto> dtos = new ArrayList<>();
        users.forEach(v -> {
            dtos.add(
                    UserMapper.INSTANCE.toDto(v)
            );
        });
        return dtos;
    }

    @Override
    public UserDto update(UserDto dto) {
        String id = dto.getKey();
        if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check Id : "+ id);
        }
        User user = fetchById(Long.valueOf(id));
        UserMapper.INSTANCE.updateFromDto(dto, user);
        return dto;
    }

    // 회원데이터 삭제 delete
    @Override
    public UserDto delete(UserDto dto){
        User user = UserMapper.INSTANCE.toEntity(dto);
        userRepository.delete(user);
        return dto;
    }

    @Override
    public void deleteById(String id) {
        if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check Id : "+ id);
        }
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
