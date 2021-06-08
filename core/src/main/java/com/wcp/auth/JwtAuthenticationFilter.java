package com.wcp.auth;

import com.wcp.common.http.HttpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 헤더에서 JWT 를 받아옵니다.
        String token = jwtTokenProvider.resolveToken(request);
        if (token != null) {
            // 유효한 토큰인지 확인합니다.
            TokenDto dto = TokenDto.builder()
                    .token(token)
                    .ip(HttpUtils.getClientIp(request))
                    .build();
            if(jwtTokenProvider.validateWebToken(dto)){
                setAuthentication(dto);
                filterChain.doFilter(request, response);
            }else{
                filterChain.doFilter(request, response);
//                filter(request, response);
            }
        }else{
            filterChain.doFilter(request, response);
        }
    }


    private void filter(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        req.getRequestDispatcher("/logout").forward(request, response);
    }

    private void setAuthentication(TokenDto dto) {
        if (!dto.getToken().isEmpty()) {
            JwtAuthentication authentication = new JwtAuthentication(dto);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

}
