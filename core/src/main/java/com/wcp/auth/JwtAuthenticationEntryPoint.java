package com.wcp.auth;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.http.ResponseCookie;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;import static com.wcp.auth.Token.ACCESSTOKEN;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {


	@Override
	public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException)
			throws IOException, ServletException {
		res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		res.setContentType("text/html");

		// TODO: 클라이언트 쪽에서 권한인증 로직 보강 시 accessToken 쿠키 초기화 로직 제거 필요
		ResponseCookie accessTokenCookie = ResponseCookie.from(ACCESSTOKEN.getToken(), null)
                .sameSite("Strict")
                .maxAge(0)
                .path("/wcp")
                .httpOnly(true)
                .build();
		res.addHeader("Set-Cookie", accessTokenCookie.toString());
	}

}
