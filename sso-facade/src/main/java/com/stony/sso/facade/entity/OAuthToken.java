package com.stony.sso.facade.entity;

import java.io.Serializable;

/**
 * <p>Java-SSO
 * <p>com.stony.sso.facade.entity
 *
 * @author stony
 * @version 上午11:16
 * @since 2017/6/30
 */
public class OAuthToken implements Serializable {
    String token;
    long expireIn;
    String clientId;
    String clientSecret;
    String username;

    public OAuthToken() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(long expireIn) {
        this.expireIn = expireIn;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public OAuthToken clone(){
        OAuthToken token = new OAuthToken();
        token.setUsername(this.getUsername());
        token.setExpireIn(this.getExpireIn());
        token.setToken(this.getToken());
        token.setClientId(this.getClientId());
        token.setClientSecret(this.getClientSecret());
        return token;
    }

    @Override
    public String toString() {
        return "OAuthToken{" +
                "token='" + token + '\'' +
                ", expireIn=" + expireIn +
                ", clientId='" + clientId + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
