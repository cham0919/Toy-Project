package com.wcp.user;

import com.wcp.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        save(user);
        return UserMapper.INSTANCE.toDto(user);
    }

    @Override
    public User save(User user){
        return userRepository.save(user);
    }

    @Override
    public User fetchByUserId(String userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> fetchById(String userKey) {
        if (StringUtils.isEmpty(userKey) || !StringUtils.isNumeric(userKey)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check Id : "+ userKey);
        }
        return fetchById(Long.valueOf(userKey));
    }

    @Override
    public Optional<User> fetchById(Long userKey) {
        return userRepository.findById(userKey);
    }

    @Override
    public List<User> fetchAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(User user) {
        User fetchUser = fetchById(user.getKey()).get();
        fetchUser = user;
        return user;
    }

    // 회원데이터 삭제 delete
    @Override
    public User delete(User user){
        userRepository.delete(user);
        return user;
    }

    @Override
    public void deleteById(String id) {
        if (StringUtils.isEmpty(id) || !StringUtils.isNumeric(id)) {
            throw new IllegalArgumentException("id should not be empty or String. Please Check Id : "+ id);
        }
        deleteById(Long.valueOf(id));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return userRepository.count();
    }


}
