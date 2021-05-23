package com.wcp.user;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserPersistenceManager userPersistenceManager;

    // 회원가입
    public User signUp(User user){
        user = userPersistenceManager.save(user);
        return user;
    }

    // 아이디 찾기
    public String findById(String id){
        User user = userPersistenceManager.findByUserId(id);
        return user.getId();
    }

    public String findByMembershipNumber(Long id){
        User user = userPersistenceManager.findByMemberNumber(id);
        return user.getId();
    }

    // 비밀번호 변경
    public void changePasswordByUserId(String id, String pw){
        userPersistenceManager.updateUserPassword(id,pw);
    }

    public void changePWByMemberNumber(Long key, String pw){
        userPersistenceManager.updateUserPassword(key,pw);
    }

    // 아이디 중복 결과
    public boolean checkDuplicatedId(String id){
        return userPersistenceManager.checkDuplicatedId(id).size() == 0 ? true : false;
    }

    // 회원탈퇴
    public void deactivateUserById(String id){
        userPersistenceManager.updateUserStatus(id, UserSataus.DEACTIVATE);
    }

    public void deactivateUserByMemberNumber(Long key){
        userPersistenceManager.updateUserStatus(key, UserSataus.DEACTIVATE);
    }

    // 회원정보 조회
    public User findUserById(String id){
        return userPersistenceManager.findByUserId(id);
    }

    public User findUserByMemberNumber(Long key){
        return userPersistenceManager.findByMemberNumber(key);
    }

    // 회원정보 전체 조회
    public List<User> findAllUsers(){
        return userPersistenceManager.findAll();
    }

    // 현재 로그인 여부


    // 현재 로그인 유저 조회

    // 로그인
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userPersistenceManager.findByUserId(id);
        List<GrantedAuthority> authoritie = new ArrayList<>();
        authoritie.add(new SimpleGrantedAuthority(user.getRole().getValue()));
        return new org.springframework.security.core.userdetails.
                User(user.getId(), user.getPassword(), authoritie);
    }
}
