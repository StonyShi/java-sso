package com.stony.sso.service.service;


import com.alibaba.fastjson.JSON;
import com.stony.sso.cache.redis.JedisSentinelTemplate;
import com.stony.sso.cache.redis.JedisTemplate;
import com.stony.sso.facade.entity.OAuthToken;
import com.stony.sso.facade.keys.SecurityKeys;
import com.stony.sso.facade.service.AppService;
import com.stony.sso.facade.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/6/1 </p>
 * <p>Time: 20:29 </p>
 * <p>Version: 1.0 </p>
 */
@Service("oAuthService")
public class OAuthServiceImpl implements OAuthService {
    private static final Logger logger = LoggerFactory.getLogger(OAuthServiceImpl.class);


    @javax.annotation.Resource
    AppService appService;

    @javax.annotation.Resource
    JedisTemplate jedisTemplate;


    public OAuthServiceImpl() {
        logger.debug("Enter");
    }

    /**
     * 默认5分钟
     * @param authCode
     * @param username
     */
    @Override
    public void addAuthCode(String authCode, String username) {
        logger.debug("code = {}, username = {}", authCode, username);
        jedisTemplate.setStr(getCodeKey(authCode), username, getCodeExpireIn());
    }

    /**
     * 默认3天
     * @param accessToken
     * @param token
     */
    @Override
    public void addAccessToken(String accessToken, OAuthToken token) {
        logger.debug("accessToken = {}, username = {}", accessToken, token.getUsername());
        jedisTemplate.setStr(getTokenKey(accessToken), convert(token), Integer.valueOf(getExpireIn() + ""));
    }

    @Override
    public void addRefreshToken(String refreshToken, OAuthToken token) {
        logger.debug("refreshToken = {}, username = {}", refreshToken, token.getUsername());
        jedisTemplate.setStr(getTokenKey(refreshToken), convert(token), Integer.valueOf(getRefreshTokenExpireIn() + ""));
    }

    @Override
    public String getUsernameByAuthCode(String authCode) {
        return jedisTemplate.getStr(getCodeKey(authCode));
    }

    @Override
    public OAuthToken getToken(String token) {
        String info = jedisTemplate.getStr(getTokenKey(token));
        return convert(info);
    }

    String convert(OAuthToken token) {
        if (token != null) {
            return JSON.toJSONString(token);
        }
        throw new NullPointerException("token must not null");
    }
    OAuthToken convert(String info) {
        if (info == null || info.length() == 0) {
            return null;
        }
        return JSON.parseObject(info, OAuthToken.class);
    }
    @Override
    public boolean checkAuthCode(String authCode) {
        return getUsernameByAuthCode(authCode) != null;
    }

    @Override
    public boolean checkAccessToken(String accessToken) {
        return getToken(accessToken) != null;
    }

    @Override
    public boolean checkRefreshToken(String refreshToken) {
        return getToken(refreshToken) != null;
    }

    @Override
    public boolean checkClientId(String appKey) {
        return appService.findAppByAppKey(appKey) != null;
    }


    @Override
    public boolean checkClientSecret(String appSecret) {
        return appService.findAppByAppSecret(appSecret) != null;
    }

    /**
     * 默认1天
     * @return
     */
    @Override
    public long getExpireIn() {
        return 60 * 60 * 24 * 1;
    }

    @Override
    public long getRefreshTokenExpireIn() {
        return getExpireIn() * 60;
    }

    /**
     * 5 分钟
     * @return
     */
    public int getCodeExpireIn(){
        return 60 * 5;
    }


    public static String getCodeKey(String key) {
        return SecurityKeys.KEY_OAUTH_CODE + key;
    }

    public static String getTokenKey(String token) {
        return SecurityKeys.KEY_OAUTH_TOKEN + token;
    }
}
