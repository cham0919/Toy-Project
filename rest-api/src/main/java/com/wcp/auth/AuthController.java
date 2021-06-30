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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.security.auth.login.LoginException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static com.wcp.auth.Token.*;

@RestController
@RequestMapping(value = "/wcp/auth")
@RequiredArgsConstructor
public class AuthController {

    private final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final Gson gson = new GsonBuilder().setPrettyPrinting()
            .disableHtmlEscaping()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            .create();
    private final AuthService authService;



    @PostMapping({"", "/"})
    public ResponseEntity<String> signIn(HttpServletRequest req,
                                         HttpServletResponse res,
                                         @RequestBody UserDto userDto) throws LoginException {
        Cookie validateTokenCookie = WebUtils.getCookie(req, VALIDATETOKEN.getToken());
        String accessToken = authService.signIn(userDto, validateTokenCookie.getValue());
        ResponseCookie accessTokenCookie = authService.privideAccessToken(accessToken);
        res.addHeader("Set-Cookie", accessTokenCookie.toString());
        return new ResponseEntity<String>(gson.toJson(userDto),HttpStatus.OK);
    }

    @GetMapping("/check")
    public ResponseEntity<String> authCheck(HttpServletRequest req,
                                            HttpServletResponse res) {
        Cookie accessTokenCookie = WebUtils.getCookie(req, ACCESSTOKEN.getToken());
        Map<String, Boolean> map = new HashMap();
        map.put("result", accessTokenCookie != null);
        return new ResponseEntity<String>(gson.toJson(map), HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest req,
                                         HttpServletResponse res) {
        ResponseCookie accessTokenCookie = authService.logout();
        res.addHeader("Set-Cookie", accessTokenCookie.toString());
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
