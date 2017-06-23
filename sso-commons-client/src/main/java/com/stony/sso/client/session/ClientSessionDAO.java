package com.stony.sso.client.session;


import com.stony.sso.facade.service.PermissionService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * <p>Created with car-commons-security.</p>
 * <p>User: Stony</p>
 * <p>Date: 2016/4/25</p>
 * <p>Time: 18:30</p>
 */
public class ClientSessionDAO extends CachingSessionDAO implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(ClientSessionDAO.class);

    private PermissionService permission;
    private String appKey;
    private String remoteServiceUrl;

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public void setRemoteServiceUrl(String remoteServiceUrl) {
        this.remoteServiceUrl = remoteServiceUrl;
    }

    public void setPermission(PermissionService permission) {
        this.permission = permission;
    }

    private String getUrl(String suffix){
        return remoteServiceUrl + suffix;
    }
    @Override
    protected void doUpdate(Session session) {
        logger.info("update appKey[{}] of session [{}] ", appKey, session);
        permission.updateSession(appKey, (SimpleSession) session);
    }
    @Override
    protected void doDelete(Session session) {
        logger.info("delete appKey[{}] of session [{}] ", appKey, session);
        permission.deleteSession(appKey, (SimpleSession) session);
    }
    @Override
    protected Session doReadSession(Serializable sessionId) {
        Session session = permission.getSession(appKey, String.valueOf(sessionId));
        logger.info("read Session appKey[{}] of session [{}] ", appKey, session);
        return session;
    }
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = permission.createSession((SimpleSession) session);
        assignSessionId(session, sessionId);
        logger.info("create session appKey[{}] of session [{}] ", appKey, session);
        return sessionId;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(permission, "Property 'permission' is required");
        Assert.notNull(appKey, "Property 'appKey' is required");
        Assert.notNull(remoteServiceUrl, "Property 'remoteServiceUrl' is required");
    }
}
