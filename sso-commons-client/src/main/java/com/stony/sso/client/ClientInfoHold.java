package com.stony.sso.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/4/29 </p>
 * <p>Time: 18:16 </p>
 * <p>Version: 1.0 </p>
 */
public class ClientInfoHold {

    private static final Logger logger = LoggerFactory.getLogger(ClientInfoHold.class);

    public static String APP_KEY;
    public static String APP_SECRET;
    public static String REMOTE_SERVICE_URL;

    public static String ACCESS_TOKEN_URL;
    public static String PERMISSION_USERNAME_URL;
    public static String PERMISSION_URL;
    public static String REDIRECT_URL;

    public static String LOGIN_URL;
    public static String LOGOUT_URL;


    /**
     * 客户端 KEY
     */

    private String appKey;
    private String appSecret;
    private String accessTokenUrl;
    private String permissionUserNameUrl;
    private String permissionUrl;
    private String redirectUrl;
    private String loginUrl;
    private String logoutUrl;

    /**
     * 资源服务中心地址
     */
    private String remoteServiceUrl;

    public String getRemoteServiceUrl() {
        return remoteServiceUrl;
    }

    public void setRemoteServiceUrl(String remoteServiceUrl) {
        this.remoteServiceUrl = remoteServiceUrl;
        ClientInfoHold.REMOTE_SERVICE_URL = remoteServiceUrl;
        logger.debug("remoteServiceUrl = ", remoteServiceUrl);
    }

    public String getAppKey() {
        return appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }
    public void setAppKey(String appKey) {
        this.appKey = appKey;
        ClientInfoHold.APP_KEY = appKey;
        logger.debug("appKey = {}", appKey);
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
        ClientInfoHold.APP_SECRET = appSecret;
        logger.debug("appSecret = {}", appSecret);
    }

    public void setAccessTokenUrl(String accessTokenUrl) {
        this.accessTokenUrl = accessTokenUrl;
        ClientInfoHold.ACCESS_TOKEN_URL = accessTokenUrl;
    }

    public void setPermissionUserNameUrl(String permissionUserNameUrl) {
        this.permissionUserNameUrl = permissionUserNameUrl;
        ClientInfoHold.PERMISSION_USERNAME_URL = permissionUserNameUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
        ClientInfoHold.PERMISSION_URL = permissionUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
        ClientInfoHold.REDIRECT_URL = redirectUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
        ClientInfoHold.LOGIN_URL = loginUrl;
    }

    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
        ClientInfoHold.LOGOUT_URL = logoutUrl;
    }
    public static void setLoginRedirectUrl(String url) {
        ClientInfoHold.REDIRECT_URL = url;
    }

    public String getAccessTokenUrl() {
        return accessTokenUrl;
    }

    public String getPermissionUsernameUrl() {
        return permissionUserNameUrl;
    }

    public String getPermissionUrl() {
        return permissionUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public String getLogoutUrl() {
        return logoutUrl;
    }
}
