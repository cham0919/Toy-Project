package com.wcp.auth;

import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class JwtTokenProviderTest {

    @InjectMocks
    private JwtTokenProvider jwtTokenProvider;

    TokenDto dto = TokenDto.builder()
            .key("testId")
            .role("ROLE_MEMBER")
            .uuid(String.valueOf(UUID.randomUUID()))
            .build();

    @Test
    public void createToken_Success_NotNull(){
        String token = jwtTokenProvider.createToken(dto);

        assertNotNull(token);
    }

    @Test
    public void getUserPk_Success_Equals() {
        String token = jwtTokenProvider.createToken(dto);

        String userPk = jwtTokenProvider.getUserPk(token);

        assertEquals(dto.getKey(), userPk);
    }

    @Test
    public void resolveToken_Succsee_IOEquals() {
        String token = "token";
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(HttpHeaders.AUTHORIZATION, token);

        String returnToken = jwtTokenProvider.resolveToken(request);

        assertEquals(token, returnToken);
    }

    @Test
    public void validateWebToken() {
        String token = jwtTokenProvider.createToken(dto);
        TokenDto tempDto =  TokenDto.builder()
                .accessToken(token)
                .build();

        boolean result = jwtTokenProvider.validateWebToken(tempDto);

        assertTrue(result);
        assertNotNull(tempDto.getKey());
        assertNotNull(tempDto.getRole());
    }

//    // 모바일 토큰의 유효성 확인
//    public boolean validateMobileToken(TokenDto dto) {
//        try {
//            Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(dto.getToken());
//            return !claims.getBody().getExpiration().before(new Date());
//        } catch (Exception

    }
