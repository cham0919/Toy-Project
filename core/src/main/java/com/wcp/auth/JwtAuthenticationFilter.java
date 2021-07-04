package com.wcp.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements Filter {

    private final JwtTokenProvider jwtTokenProvider;

    private final AnonymousAuthentication anonymousAuthentication;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        Cookie accessTokenCookie = WebUtils.getCookie(request, Token.ACCESSTOKEN.getToken());
        Cookie validateTokenCookie = WebUtils.getCookie(request, Token.VALIDATETOKEN.getToken());

        if (!hasCookie(validateTokenCookie)) { // 최초 요청 판별 여부
            applyValidateTokenCookie(response);
        } else if (hasCookie(accessTokenCookie)) { // 로그인 유저 판별 여부
            TokenDto dto = createTokenDto(accessTokenCookie, validateTokenCookie);
            setAuthentication(dto);
        } else {
            setAnonymousAuthentication(); // 익명 권한 부여
        }
        chain.doFilter(req, res);
    }

    private TokenDto createTokenDto(Cookie accessTokenCookie, Cookie validateTokenCookie) {
        TokenDto dto = TokenDto.builder()
                .accessToken(accessTokenCookie.getValue())
                .validateToken(validateTokenCookie.getValue())
                .build();
        return dto;
    }

    private boolean hasCookie(Cookie cookie) {
        return cookie != null && cookie.getValue() != null;
    }

    private void applyValidateTokenCookie(HttpServletResponse response) {
        ResponseCookie validateTokenCookie = ResponseCookie.from("validateToken", String.valueOf(UUID.randomUUID()))
                .httpOnly(true)
                .path("/wcp")
                .build();
        response.addHeader("Set-Cookie", validateTokenCookie.toString());
        setAnonymousAuthentication();
    }

    private void filter(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        req.getRequestDispatcher("/logout").forward(request, response);
    }

    private void setAuthentication(TokenDto dto) {
        if (isValidateToken(dto)) {

            JwtAuthentication authentication = new JwtAuthentication(dto);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            setAnonymousAuthentication();
        }
    }

    private boolean isValidateToken(TokenDto dto){
        return jwtTokenProvider.validateWebToken(dto);
    }

    private void setAnonymousAuthentication() {
        SecurityContextHolder.getContext().setAuthentication(anonymousAuthentication);
    }
}
