package com.wcp.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wcp.auth.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.LinkedList;

@RestController
@RequestMapping(value = "/wcp")
@RequiredArgsConstructor
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);
    private final Gson gson = new GsonBuilder().setPrettyPrinting()
            .disableHtmlEscaping()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            .create();
    private final UserService userService;


    // 회원가입
    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(HttpServletRequest req,
                                         HttpServletResponse res,
                                         @RequestBody UserDto userDto) {
        userDto = userService.signUp(userDto);
        return new ResponseEntity(gson.toJson(userDto), HttpStatus.OK);
    }

    // 회원탈퇴
    @DeleteMapping("/user")
    public ResponseEntity<String> deactivateUser(HttpServletRequest req,
                                                 HttpServletResponse res,
                                                 Authentication authentication) {
        String userKey = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.deleteById(userKey);
        return new ResponseEntity(HttpStatus.OK);
    }

    // 아이디 찾기
    @GetMapping("/user/id")
    public ResponseEntity<String> findId(HttpServletRequest req,
                                         HttpServletResponse res) {
        String userKey = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDto dto = userService.fetchById(userKey);
        return new ResponseEntity(gson.toJson(dto.getId()), HttpStatus.OK);
    }

    // 비밀번호 수정
    @PatchMapping("/user/pw")
    public ResponseEntity<String> findPW(HttpServletRequest req,
                                         HttpServletResponse res,
                                         @RequestBody UserDto userDto) {
        String userKey = SecurityContextHolder.getContext().getAuthentication().getName();
        userDto.setKey(userKey);
        userService.update(userDto);
        return new ResponseEntity(HttpStatus.OK);
    }


    // 회원정보 조회
    @GetMapping("/user")
    public ResponseEntity<String> findUser(HttpServletRequest req,
                                           HttpServletResponse res){
        String userKey = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDto dto = userService.fetchById(userKey);
        return new ResponseEntity<String>(gson.toJson(dto),HttpStatus.OK);
    }
}
