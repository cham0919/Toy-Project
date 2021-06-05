package com.wcp.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wcp.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.LinkedList;

@Controller
@RequestMapping(value = "/wcp/user")
@RequiredArgsConstructor
public class UserController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final Gson gson = new GsonBuilder().setPrettyPrinting()
            .disableHtmlEscaping()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            .create();
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;


    @RequestMapping(value = "/check", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> checkUser(HttpServletRequest req,
                                            HttpServletResponse res,
                                            Principal principal)
    {
        try{
            if(principal == null)
                return new ResponseEntity<String>( HttpStatus.INTERNAL_SERVER_ERROR);
            else
            return new ResponseEntity<String>("test", HttpStatus.OK);
        }catch (Throwable t){
            return new ResponseEntity<String>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 회원가입
    @RequestMapping(value = "/signUp", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> signUp(HttpServletRequest req,
                                            HttpServletResponse res,
                                         @RequestBody User user)
    {
        try{
            user = userService.signUp(user);
            return new ResponseEntity<String>(gson.toJson(user), HttpStatus.OK);
        }catch (Throwable t){
            return new ResponseEntity<String>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 회원탈퇴
    @RequestMapping(value = "/deactivate", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> deactivateUser(HttpServletRequest req,
                                         HttpServletResponse res,
                                         @RequestBody String id)
    {
        try{
            userService.deactivateUserById(id);
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch (Throwable t){
            return new ResponseEntity<String>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // 로그인
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> login(HttpServletRequest req,
                                         HttpServletResponse res,
                                        @RequestBody UserDto dto)
    {
        try{
//            UserDetails user = userService.loadUserByUsername(dto.getId());
            String token = jwtTokenProvider.createToken("test", new LinkedList(){{ add("ROLE_USER");}});
            return new ResponseEntity<String>(token, HttpStatus.OK);
        }catch (Throwable t){
            log.error("error",t);
            return new ResponseEntity<String>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // 로그아웃

    // 아이디 찾기
    @RequestMapping(value = "/findId", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> findId(HttpServletRequest req,
                                     HttpServletResponse res,
                                     @RequestBody String id)
    {
        try{
            id = userService.findById(id);
            return new ResponseEntity<String>(gson.toJson(id), HttpStatus.OK);
        }catch (Throwable t){
            return new ResponseEntity<String>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 비밀번호 수정
    @RequestMapping(value = "/findPW", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> findPW(HttpServletRequest req,
                                         HttpServletResponse res,
                                         @RequestBody User user)
    {
        try{
            userService.changePasswordByUserId(user.getId(), user.getPassword());
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch (Throwable t){
            return new ResponseEntity<String>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // 회원정보 조회
    @RequestMapping(value = "/find/info", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> findUser(HttpServletRequest req,
                                         HttpServletResponse res,
                                         @RequestBody User user)
    {
        try{
            user = userService.findUserById(user.getId());
            return new ResponseEntity<String>(gson.toJson(user),HttpStatus.OK);
        }catch (Throwable t){
            return new ResponseEntity<String>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
