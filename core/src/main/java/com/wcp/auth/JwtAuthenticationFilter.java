package com.wcp.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    private final AnonymousAuthentication anonymousAuthentication;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 쿠키 JWT 를 받아옵니다.
        Cookie accessTokenCookie = WebUtils.getCookie(request, "accessToken");
        Cookie validateTokenCookie = WebUtils.getCookie(request, "validateToken");

        if (!hasCookie(validateTokenCookie)) {
            applyValidateTokenCookie(response);
        } else if (hasCookie(accessTokenCookie)) {
            TokenDto dto = createTokenDto(accessTokenCookie, validateTokenCookie);
            setAuthentication(dto);
        } else {
            setAnonymousAuthentication();
        }
        filterChain.doFilter(request, response);
    }

    private boolean isValidateToken(TokenDto dto){
        return jwtTokenProvider.validateWebToken(dto);
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
//                .sameSite("La")
                .httpOnly(true)
                .build();
        response.addHeader("Set-Cookie", validateTokenCookie.toString());
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

    private void setAnonymousAuthentication() {
        SecurityContextHolder.getContext().setAuthentication(anonymousAuthentication);
    }

}
