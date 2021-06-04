package com.wcp.token.refresh;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository  extends JpaRepository<RefreshToken, Long> {

    RefreshToken findByRefreshToken(String refreshToken);

    RefreshToken findByAccessToken(String accessToken);

    RefreshToken findByRefreshTokenAndAccessToken(String refreshToken, String accessToken);
}
