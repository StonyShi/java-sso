package com.stony.sso.facade.service;

import com.stony.sso.facade.entity.OAuthToken;

/**
 * <p>Created with car-facade-security.</p>
 * <p>User: Stony</p>
 * <p>Date: 2016/6/1</p>
 * <p>Time: 20:30</p>
 * <p>Version: 1.0</p>
 */
public interface OAuthService {
    /**
     * 添加 auth code
     * @param authCode
     * @param username
     */
    void addAuthCode(String authCode, String username);

    /**
     * 添加 access token
     * @param accessToken
     * @param token
     */
    void addAccessToken(String accessToken, OAuthToken token);

    /**
     * 添加 refreshToken
     * @param refreshToken
     * @param token
     */
    void addRefreshToken(String refreshToken, OAuthToken token);

    /**
     * 验证auth code是否有效
     * @param authCode
     * @return
     */
    boolean checkAuthCode(String authCode);

    /**
     * 验证access token是否有效
     * @param accessToken
     * @return
     */
    boolean checkAccessToken(String accessToken);

    /**
     *
     * @param refreshToken
     * @return
     */
    boolean checkRefreshToken(String refreshToken);

    /**
     * 根据auth code获取用户名
     * @param authCode
     * @return
     */
    String getUsernameByAuthCode(String authCode);

    /**
     * 根据用户token
     * @param token
     * @return
     */
    OAuthToken getToken(String token);

    /**
     * auth code , access token 过期时间
     * @return
     */
    long getExpireIn();

    long getRefreshTokenExpireIn();

    /**
     * 检查客户端id是否存在
     * @param appKey
     * @return
     */
    boolean checkClientId(String appKey);

    /**
     * 坚持客户端安全KEY是否存在
     * @param appSecret
     * @return
     */
    boolean checkClientSecret(String appSecret);
}
