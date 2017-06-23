package com.stony.sso.service.service;


import com.stony.sso.cache.redis.JedisSentinelTemplate;
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
    JedisSentinelTemplate jedisSentinelTemplate;


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
        jedisSentinelTemplate.setStr(getCodeKey(authCode), username, getCodeExpireIn());
    }

    /**
     * 默认3天
     * @param accessToken
     * @param username
     */
    @Override
    public void addAccessToken(String accessToken, String username) {
        logger.debug("accessToken = {}, username = {}", accessToken, username);
        jedisSentinelTemplate.setStr(getTokenKey(accessToken), username, Integer.valueOf(getExpireIn()+""));
    }

    @Override
    public String getUsernameByAuthCode(String authCode) {
        return jedisSentinelTemplate.getStr(getCodeKey(authCode));
    }

    @Override
    public String getUsernameByAccessToken(String accessToken) {
        return jedisSentinelTemplate.getStr(getTokenKey(accessToken));
    }

    @Override
    public boolean checkAuthCode(String authCode) {
        return getUsernameByAuthCode(authCode) != null;
    }

    @Override
    public boolean checkAccessToken(String accessToken) {
        return getUsernameByAccessToken(accessToken) != null;
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
     * 默认3天
     * @return
     */
    @Override
    public long getExpireIn() {
        return 60 * 60 * 24 * 3;
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
