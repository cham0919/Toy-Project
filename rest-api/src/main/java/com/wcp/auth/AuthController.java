package com.wcp.auth;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wcp.common.http.HttpUtils;
import com.wcp.user.User;
import com.wcp.user.UserDto;
import lombok.RequiredArgsConstructor;
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
@RequestMapping(value = "/wcp/auth")
@RequiredArgsConstructor
public class AuthController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final Gson gson = new GsonBuilder().setPrettyPrinting()
            .disableHtmlEscaping()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            .create();
    private final AuthService authService;



    @RequestMapping(value = {"/", ""}, method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> checkUser(HttpServletRequest req,
                                            HttpServletResponse res,
                                            @RequestBody UserDto userDto)
    {
        try{
            String token = authService.signIn(userDto, HttpUtils.getClientIp(req));
            return new ResponseEntity<String>(token, HttpStatus.OK);
        }catch (Throwable t){
            log.error(t.getMessage(), t);
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = {"/test"}, method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> checkUser(HttpServletRequest req,
                                            HttpServletResponse res)
    {
        try{
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch (Throwable t){
            log.error(t.getMessage(), t);
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
