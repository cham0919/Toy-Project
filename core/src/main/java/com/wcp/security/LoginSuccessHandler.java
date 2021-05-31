package com.wcp.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {


        // IP, 세션 ID
        WebAuthenticationDetails web = (WebAuthenticationDetails) authentication.getDetails();
        log.debug("IP : " + web.getRemoteAddress());
        log.debug("Session ID : " + web.getSessionId());

        // 인증 ID
        log.debug("name : " + authentication.getName());

        // 권한 리스트
        List<GrantedAuthority> authList = (List<GrantedAuthority>) authentication.getAuthorities();
        log.debug("권한 : ");
        for(int i = 0; i< authList.size(); i++) {
            log.debug(authList.get(i).getAuthority() + " ");
        }
//        response.sendRedirect("http://localhost:3000/components");
    }
}
