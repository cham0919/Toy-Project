package com.wcp.user;

import com.wcp.security.Role;

import java.util.List;

public interface UserPersistenceManager {

    // 회원가입 save
    User save(User user);

    // 회원 이름 수정
    User updateUserName(Long key, String name);
    User updateUserName(String id, String name);

    // 회원 비밀번호 수정
    User updateUserPassword(Long key, String pw);
    User updateUserPassword(String id, String pw);

    // 회원 아이디 수정
    User updateUserId(Long key, String id);
    User updateUserId(String id, String updateId);


    // 회원 핸드폰번호 수정
    User updateUserPhone(Long key, String phone);
    User updateUserPhone(String id, String phone);


    // 회원 권한 수정
    User updateUserRole(Long key, Role role);
    User updateUserRole(String id, Role role);


    // 회원 상태 수정
    User updateUserStatus(Long key, UserSataus Status);
    User updateUserStatus(String id, UserSataus Status);

    // 회원정보 전체 검색
    List<User> findAll();

    // 회원정보 검색 key로 조회
    User findByMemberNumber(String key);
    User findByMemberNumber(Long key);

    // 회원정보 검색 id로 조회
    User findByUserId(String id);

    // 아이디 중복 검사 조회
    List<User> checkDuplicatedId(String id);

    // 회원데이터 삭제 delete
    User delete(User user);

}
