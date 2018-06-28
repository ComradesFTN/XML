package ftn.xmlws.dto;

import ftn.xmlws.domain.User;

public class JwtAuthenticationResponse {

	private String accessToken;
	private User user;

    public JwtAuthenticationResponse(String accessToken, User user) {
        this.accessToken = accessToken;
        this.user=user;
    }

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
