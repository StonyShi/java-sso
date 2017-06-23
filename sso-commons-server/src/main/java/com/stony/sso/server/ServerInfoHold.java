package com.stony.sso.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/6/16 </p>
 * <p>Time: 10:45 </p>
 * <p>Version: 1.0 </p>
 */
public class ServerInfoHold {

    private static final Logger logger = LoggerFactory.getLogger(ServerInfoHold.class);

    public static String APP_KEY;
    public static String APP_SECRET;
    /**
     *  KEY
     */

    private String appKey;
    private String appSecret;


    public void setAppKey(String appKey) {
        this.appKey = appKey;
        ServerInfoHold.APP_KEY = appKey;
        logger.debug("appKey = {}", appKey);
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
        ServerInfoHold.APP_SECRET = appSecret;
        logger.debug("appSecret = {}", appSecret);
    }

    public String getAppKey() {
        return appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }
}
