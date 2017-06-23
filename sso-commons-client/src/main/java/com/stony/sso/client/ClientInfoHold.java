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
    /**
     * 客户端 KEY
     */

    private String appKey;
    private String appSecret;
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
}
