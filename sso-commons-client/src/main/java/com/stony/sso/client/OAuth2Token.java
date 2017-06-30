package com.stony.sso.client;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/6/1 </p>
 * <p>Time: 21:40 </p>
 * <p>Version: 1.0 </p>
 */
public class OAuth2Token implements AuthenticationToken {

    public OAuth2Token(String authCode) {
        this.authCode = authCode;
    }

    private String authCode;

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }


    @Override
    public Object getPrincipal() {
        return this.authCode;
    }

    @Override
    public Object getCredentials() {
        return authCode;
    }
}
