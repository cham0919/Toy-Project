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
    @RequestMapping(value = "/signUp", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public ResponseEntity<String> signUp(HttpServletRequest req,
                                            HttpServletResponse res,
                                         @RequestBody UserDto userDto)
    {
        try{
            userDto = userService.signUp(userDto);
            return new ResponseEntity<String>(gson.toJson(userDto), HttpStatus.OK);
        }catch (Throwable t){
            return new ResponseEntity<String>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 회원탈퇴
    @RequestMapping(value = "/user", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
    public ResponseEntity<String> deactivateUser(HttpServletRequest req,
                                         HttpServletResponse res,
                                         Authentication authentication)
    {
        try{
            String userKey = SecurityContextHolder.getContext().getAuthentication().getName();
            userService.deleteById(userKey);
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch (Throwable t){
            return new ResponseEntity<String>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 아이디 찾기
    @RequestMapping(value = "/user/id", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ResponseEntity<String> findId(HttpServletRequest req,
                                     HttpServletResponse res)
    {
        try{
            String userKey = SecurityContextHolder.getContext().getAuthentication().getName();
            UserDto dto = userService.fetchById(userKey);
            return new ResponseEntity<String>(gson.toJson(dto.getId()), HttpStatus.OK);
        }catch (Throwable t){
            return new ResponseEntity<String>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 비밀번호 수정
    @RequestMapping(value = "/user/pw", method = RequestMethod.PATCH, produces = "application/json; charset=utf-8")
    public ResponseEntity<String> findPW(HttpServletRequest req,
                                         HttpServletResponse res,
                                         @RequestBody UserDto userDto)
    {
        try{
            String userKey = SecurityContextHolder.getContext().getAuthentication().getName();
            userDto.setKey(userKey);
            userService.update(userDto);
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch (Throwable t){
            return new ResponseEntity<String>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // 회원정보 조회
    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public ResponseEntity<String> findUser(HttpServletRequest req,
                                         HttpServletResponse res){
        try{
            String userKey = SecurityContextHolder.getContext().getAuthentication().getName();
            UserDto dto = userService.fetchById(userKey);
            return new ResponseEntity<String>(gson.toJson(dto),HttpStatus.OK);
        }catch (Throwable t){
            return new ResponseEntity<String>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
