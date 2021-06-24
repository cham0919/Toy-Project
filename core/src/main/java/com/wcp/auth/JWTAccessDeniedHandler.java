package com.wcp.auth;

import org.springframework.http.ResponseCookie;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.wcp.auth.Token.ACCESSTOKEN;

@Component
public class JWTAccessDeniedHandler implements AccessDeniedHandler {


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("text/html");

        // TODO: 클라이언트 쪽에서 권한인증 로직 보강 시 accessToken 쿠키 초기화 로직 제거 필요
        ResponseCookie accessTokenCookie = ResponseCookie.from(ACCESSTOKEN.getToken(), null)
                .sameSite("Strict")
                .maxAge(0)
                .path("/wcp")
                .httpOnly(true)
                .build();
        response.addHeader("Set-Cookie", accessTokenCookie.toString());
    }
}
