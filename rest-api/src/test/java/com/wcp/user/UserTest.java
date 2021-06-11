//package com.wcp.user;
//
//import com.wcp.boot.Boot;
//import com.wcp.security.Role;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
//public class UserTest extends Boot {
//
//    @Autowired
//    private UserPersistenceManager userPersistenceManager;
//
//
//    // 회원가입 save
//    @Test
//    public void save(){
//        User user = new User();
//        user.setId("test@test");
//        user.setPassword("1234");
//        user.setName("김참이");
//        user.setStatus(UserSataus.ACTIVATE);
//        user.setRole(Role.ADMIN);
//        userPersistenceManager.save(user);
//        System.out.println(user.toString());
//
//        user = userPersistenceManager.findByUserId("test@test");
//        System.out.println(user.getStatus());
//        System.out.println(user.getStatus().getValue());
//        System.out.println(user.getRole());
//        System.out.println(user.getRole().getValue());
//    }
//
//    // 회원수정
//    @Test
//    public void update(){
//        User fetchUser = userPersistenceManager.updateUserName(1L, "정동욱");
//    }
//
//    // 회원정보 전체 검색
//    @Test
//    public void findAll(){
//        List<User> users  = userPersistenceManager.findAll();
//        System.out.println(users.size());
//        System.out.println(users.get(0).getId());
//    }
//
//    // 회원정보 검색 key로 조회
//    @Test
//    public void findByKey(){
//        User user = userPersistenceManager.findByMemberNumber(1L);
//        System.out.println(user.toString());
//    };
//
//    // 회원정보 검색 id로 조회
//    @Test
//    public void findByPassword(){
//        User user = userPersistenceManager.findByUserId("test@test");
//        System.out.println(user.toString());
//    }
//
//    // 아이디 중복 검사 조회
////    public List<User> checkDuplicatedId(String id);
//
//    // 회원데이터 삭제 delete
////    public User delete(User user);
//
//    @Test
//    public void findbyid(){
//    }
//}
