package org.itstep.msk.app.entity.model;

public class AuthResponse {

    public String getJwt() {
        return jwt;
    }

    private final String jwt;

    public AuthResponse(String jwt) {
        this.jwt = jwt;
    }

}
