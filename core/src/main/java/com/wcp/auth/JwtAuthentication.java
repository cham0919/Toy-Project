package com.wcp.auth;

import com.wcp.security.Role;
import com.wcp.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class JwtAuthentication implements Authentication {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthentication.class);

	private final String token;
	private String userId;
	private String role;
	private boolean isAuthenticated;

	public JwtAuthentication(TokenDto dto) {
		this.token = dto.getToken();
		this.userId = dto.getId();
		this.role = dto.getRole();
		this.isAuthenticated = true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		logger.debug("Getting authorities");
		if (isAuthenticated) {
				return Collections.singletonList(new SimpleGrantedAuthority(role));
		}
		return Collections.singletonList(new SimpleGrantedAuthority(Role.ANONYMOUS.getValue()));
	}

	@Override
	public Object getCredentials() {
		return token;
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
		if (userId != null && isAuthenticated)
			return userId;
		return null;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

}