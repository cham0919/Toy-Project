package com.wcp.token.refresh;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

	private final  RefreshTokenRepository refreshTokenRepository;

	public void create(RefreshToken refreshToken) {
		refreshTokenRepository.save(refreshToken);
	}

	public void create(String refreshToken, String accessToken, Date expirationDate) {
		RefreshToken refreshTokenObj = new RefreshToken().setRefreshToken(refreshToken);
		refreshTokenObj.setAccessToken(accessToken);
		refreshTokenObj.setExpirationDate(expirationDate);
		create(refreshTokenObj);
	}
//
//	public void update(RefreshToken refreshToken) {
//		RefreshToken fetchRefreshToken = refreshTokenRepository.findById(refreshToken.getId()).get();
//		fetchRefreshToken = refreshToken;
//	}

	public void delete(RefreshToken refreshToken) {
		refreshTokenRepository.delete(refreshToken);
	}

	public RefreshToken fetch(RefreshToken refreshToken) {
		return refreshTokenRepository.findById(refreshToken.getId()).get();
	}

	public RefreshToken fetchByRefreshToken(String refreshToken) {
		return refreshTokenRepository.findByRefreshToken(refreshToken);
	}

	public RefreshToken fetchByAccessToken(String accessToken) {
		return refreshTokenRepository.findByAccessToken(accessToken);
	}

	public RefreshToken fetchByRefreshTokenAndAccessToken(String refreshToken, String accessToken) {
		return refreshTokenRepository.findByRefreshTokenAndAccessToken(refreshToken, accessToken);
	}

	public List<RefreshToken> fetchAllRefreshTokenList() {
		return refreshTokenRepository.findAll();
	}

	/*@Transactional
	public int deleteAllExpiredTokens() {
		Query query = refreshTokenRepository.
				.createQuery("delete from RefreshToken as rt where rt.expirationDate < :now")
				.setParameter("now", new Date());
		return query.executeUpdate();
	}*/
}
