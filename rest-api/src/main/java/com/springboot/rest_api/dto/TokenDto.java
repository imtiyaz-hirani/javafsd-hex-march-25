package com.springboot.rest_api.dto;

import org.springframework.stereotype.Component;

@Component
public class TokenDto {

	private String token;
	private String username;
	private String expiry;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

}
