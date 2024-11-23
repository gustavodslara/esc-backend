package com.github.gustavodslara.esc_service.domain.entity;

public class AuthResponse {
    private User user;
    private String token;

    public AuthResponse(User user, String token) {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
