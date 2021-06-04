package com.wcp.security;

import com.wcp.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Scope("prototype")
public class JwtAuthentication implements Authentication {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthentication.class);

	private static final long serialVersionUID = 1L;

	private final String token;
	private User user;
	private boolean isAuthenticated;

	public JwtAuthentication(String token) {
		this.token = token;
		this.user = null;
		this.isAuthenticated = false;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		logger.debug("Getting authorities");
		Role role = user.getRole();
		return Collections.singletonList(new SimpleGrantedAuthority(role.getValue()));
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
		if (user != null && isAuthenticated)
			return user.getId();
		return null;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

}