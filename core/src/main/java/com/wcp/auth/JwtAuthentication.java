package com.wcp.auth;

import com.wcp.security.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class JwtAuthentication implements Authentication {

	private static final Logger logger = LoggerFactory.getLogger(JwtAuthentication.class);

	private final String accessToken;
	private final String key;
	private final String role;
	private boolean isAuthenticated;

	public JwtAuthentication(TokenDto dto) {
		this.accessToken = dto.getAccessToken();
		this.key = dto.getKey();
		this.role = dto.getRole();
		this.isAuthenticated = true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		logger.debug("Getting authorities");
		if (isAuthenticated) {
			return Collections.singletonList(new SimpleGrantedAuthority(role));
		} else {
			return Collections.singletonList(
					new SimpleGrantedAuthority(Role.ANONYMOUS.getValue())
			);
		}
	}

	@Override
	public Object getCredentials() {
		return accessToken;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}

	@Override
	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		this.isAuthenticated = isAuthenticated;
	}

	@Override
	public String getName() {
		if (key != null && isAuthenticated)
			return key;
		return null;
	}
}