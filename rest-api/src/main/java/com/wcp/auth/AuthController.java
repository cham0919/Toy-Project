package com.wcp.auth;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wcp.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static com.wcp.auth.Token.*;

@Controller
@RequestMapping(value = "/wcp/auth")
@RequiredArgsConstructor
public class AuthController {

    private final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final Gson gson = new GsonBuilder().setPrettyPrinting()
            .disableHtmlEscaping()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            .create();
    private final AuthService authService;



    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> signIn(HttpServletRequest req,
                                            HttpServletResponse res,
                                            @RequestBody UserDto userDto)
    {
        try{
            Cookie validateTokenCookie = WebUtils.getCookie(req, VALIDATETOKEN.getToken());
            String accessToken = authService.signIn(userDto, validateTokenCookie.getValue());
            ResponseCookie accessTokenCookie = authService.privideAccessToken(accessToken);
            res.addHeader("Set-Cookie", accessTokenCookie.toString());
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch (Throwable t){
            log.error(t.getMessage(), t);
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/check", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> authCheck(HttpServletRequest req,
                                         HttpServletResponse res)
    {
        try{
            Cookie accessTokenCookie = WebUtils.getCookie(req, ACCESSTOKEN.getToken());
            Map<String, Boolean> map = new HashMap();
            map.put("result", accessTokenCookie != null);
            return new ResponseEntity<String>(gson.toJson(map), HttpStatus.OK);
        }catch (Throwable t){
            log.error(t.getMessage(), t);
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> logout(HttpServletRequest req,
                                            HttpServletResponse res)
    {
        try{
            ResponseCookie accessTokenCookie = authService.logout();
            res.addHeader("Set-Cookie", accessTokenCookie.toString());
            return new ResponseEntity<String>(HttpStatus.OK);
        }catch (Throwable t){
            log.error(t.getMessage(), t);
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
