package com.wcp.user;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/wcp/user")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final Gson gson = new GsonBuilder().setPrettyPrinting()
            .disableHtmlEscaping()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            .create();

    @RequestMapping(value = "/check", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> checkUser(HttpServletRequest req,
                                            HttpServletResponse res)
    {
        try{
            log.info("success");
            return new ResponseEntity<String>(HttpStatus.OK);
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
            log.info("success");
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch (Throwable t){
            return new ResponseEntity<String>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 회원탈퇴

    // 로그인

    // 로그아웃

    // 아이디 찾기

    // 비밀번호 찾기

    // 비밀번호 수정

    // 회원정보 조회

}
