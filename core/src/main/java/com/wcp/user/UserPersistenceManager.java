package com.wcp.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserPersistenceManager {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;

    // 회원가입 save
    public User save(User user){
        return userRepository.save(user);
    }


    // 회원수정 modify
    public User modify(User user){
        User fetchUser = findById(user.getKey());
        fetchUser = user;
        return fetchUser;
    }

    // 회원정보 검색 id로 조회
    public User findById(Long key){
        User user = userRepository.findById(key).get();
        return user;
    }

    // 회원정보 전체 검색
    public List<User> findAll(){
        List<User> userList = userRepository.findAll();
        return userList;
    }

    // 아이디 중복 검사 조회
//    public List<User> findAll(String id){
//        List<User> userList = userRepository.;
//        return userList;
//    }


    // 회원데이터 삭제 delete
    public User delete(User user){
        userRepository.delete(user);
        return user;
    }


    // 비밀번호 변경



}
