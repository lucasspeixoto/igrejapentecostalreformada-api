package com.platform.igrejapentecostalreformadaapi.data.response;

import java.util.Date;
import java.util.Objects;

public class JWTAuthResponse {

    private String username;

    private Boolean authenticated;

    private Date created;

    private Date expiration;

    private String accessToken;

    private String refreshToken;
    
    private String tokenType = "Bearer";

    public JWTAuthResponse() {
    }

    public JWTAuthResponse(String username, Boolean authenticated, Date created, Date expiration, String accessToken, String refreshToken) {
        this.username = username;
        this.authenticated = authenticated;
        this.created = created;
        this.expiration = expiration;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JWTAuthResponse that)) return false;
        return Objects.equals(getAccessToken(), that.getAccessToken()) && Objects.equals(getTokenType(), that.getTokenType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccessToken(), getTokenType());
    }
}
