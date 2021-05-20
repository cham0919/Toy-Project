package com.wcp.user;

import com.wcp.security.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class UserPersistence implements UserPersistenceManager{

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    // 회원가입 save
    @Override
    public User save(User user){
        return userRepository.save(user);
    }

    // 회원수정 modify

    @Override
    @Transactional
    public User updateUserName(Long key, String name) {
        User user = findByMemberNumber(key);
        user.setName(name);
        return user;
    }

    @Override
    @Transactional
    public User updateUserName(String id, String name) {
        User user = findByUserId(id);
        user.setName(name);
        return user;
    }

    @Override
    @Transactional
    public User updateUserPassword(Long key, String pw) {
        User user = findByMemberNumber(key);
        user.setPassword(pw);
        return user;
    }

    @Override
    @Transactional
    public User updateUserPassword(String id, String pw) {
        User user = findByUserId(id);
        user.setPassword(pw);
        return user;
    }

    @Override
    @Transactional
    public User updateUserEmail(Long key, String email) {
        User user = findByMemberNumber(key);
        user.setEmail(email);
        return user;
    }

    @Override
    @Transactional
    public User updateUserEmail(String id, String email) {
        User user = findByUserId(id);
        user.setEmail(email);
        return user;
    }

    @Override
    @Transactional
    public User updateUserId(Long key, String id) {
        User user = findByMemberNumber(key);
        user.setId(id);
        return user;
    }

    @Override
    @Transactional
    public User updateUserId(String id, String updateId) {
        User user = findByUserId(id);
        user.setId(updateId);
        return user;
    }

    @Override
    @Transactional
    public User updateUserPhone(Long key, String phone) {
        User user = findByMemberNumber(key);
        user.setPhone(phone);
        return user;
    }

    @Override
    @Transactional
    public User updateUserPhone(String id, String phone) {
        User user = findByUserId(id);
        user.setPhone(phone);
        return user;
    }

    @Override
    @Transactional
    public User updateUserRole(Long key, Role role) {
        User user = findByMemberNumber(key);
        user.setRole(role);
        return user;
    }

    @Override
    @Transactional
    public User updateUserRole(String id, Role role) {
        User user = findByUserId(id);
        user.setRole(role);
        return user;
    }

    @Override
    @Transactional
    public User updateUserStatus(Long key, UserSataus Status) {
        User user = findByMemberNumber(key);
        user.setStatus(Status);
        return user;
    }

    @Override
    @Transactional
    public User updateUserStatus(String id, UserSataus Status) {
        User user = findByUserId(id);
        user.setStatus(Status);
        return user;
    }

    // 회원정보 검색 key로 조회
    @Override
    public User findByMemberNumber(Long key){
        //TODO. Optional 활용해 에러 감지
        User user = userRepository.findById(key).get();
        return user;
    }

    // 회원정보 검색. ID로 조회
    @Override
    public User findByUserId(String id) {
        // TODO. null 에러 감지
        User user = userRepository.findById(id);
        return user;
    }

    @Override
    public List<User> findAll(){
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @Override
    public List<User> checkDuplicatedId(String id) {
        return null;
    }

    // 회원데이터 삭제 delete
    @Override
    public User delete(User user){
        userRepository.delete(user);
        return user;
    }
}
