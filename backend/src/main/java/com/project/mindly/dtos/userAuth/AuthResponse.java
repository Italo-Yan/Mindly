package com.project.mindly.dtos.userAuth;

import com.project.mindly.enums.UserRoles;

public class AuthResponse {

    private String token;
    private UserRoles role;

    public AuthResponse(String token, UserRoles role) {
        this.token = token;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "token='" + token + '\'' +
                ", role=" + role +
                '}';
    }
}
