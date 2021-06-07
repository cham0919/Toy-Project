package com.wcp.auth;

import com.wcp.common.Base64Utils;
import com.wcp.user.User;
import com.wcp.user.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpHead;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;


@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final String SECRET_KEY = Base64Utils.encode("wcp");

    private final String ROLE = "role";
    private final String IP = "ip";
    private final String UUID = "uuid";
    private final String ID = "id";

    // 토큰 유효시간 30분
    private long tokenValidTime = 30 * 60 * 1000L;


    // 객체 초기화, secretKey를 Base64로 인코딩한다.
//    @PostConstruct
//    protected void init() {
//        SECRET_KEY = Base64Utils.encode(SECRET_KEY);
//    }



    // JWT 토큰 생성
    public String createToken(TokenDto tokenDto) {
//        Claims claims = Jwts.claims().setSubject(String.valueOf(user.getKey())); // JWT payload 에 저장되는 정보단위
        Claims claims = Jwts.claims(); // JWT payload 에 저장되는 정보단위
        claims.put(ROLE, tokenDto.getRole()); // 정보는 key / value 쌍으로 저장된다.
        claims.put(ID, tokenDto.getId()); // 정보는 key / value 쌍으로 저장된다.
        claims.put(IP, tokenDto.getIp()); // 정보는 key / value 쌍으로 저장된다.
        claims.put(UUID, tokenDto.getUuid()); // 정보는 key / value 쌍으로 저장된다.
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
//                .setIssuedAt(now) // 토큰 발행 시간 정보
//                .setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
//                .signWith(SignatureAlgorithm.HS256, secretKey)  // 사용할 암호화 알고리즘과
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)  // 사용할 암호화 알고리즘과
                // signature 에 들어갈 secret값 세팅
                .compact();
    }

    // 토큰에서 회원 정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 Header에서 token 값을 가져옵니다. "X-AUTH-TOKEN" : "TOKEN값'
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(HttpHeaders.AUTHORIZATION);
    }

    // 웹 토큰의 유효성 확인
    public boolean validateWebToken(TokenDto dto) {
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(dto.getToken()).getBody();
            String tokenIP = String.valueOf(claims.get(IP));
            String reqIP = dto.getIp();
            if(tokenIP.equalsIgnoreCase(reqIP)){
                dto.setRole(String.valueOf(claims.get(ROLE)));
                dto.setId(String.valueOf(claims.get(ID)));
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    // 모바일 토큰의 유효성 확인
    public boolean validateMobileToken(TokenDto dto) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(dto.getToken());
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
