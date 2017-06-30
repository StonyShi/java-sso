package com.stony.sso.commons.security.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/6/1 </p>
 * <p>Time: 21:41 </p>
 * <p>Version: 1.0 </p>
 */
public class OAuth2AuthenticationException extends AuthenticationException {

    public OAuth2AuthenticationException(Throwable cause) {
        super(cause);
    }


    /**
     * Constructs a new AuthenticationException.
     *
     * @param message the reason for the exception
     */
    public OAuth2AuthenticationException(String message) {
        super(message);
    }
}
