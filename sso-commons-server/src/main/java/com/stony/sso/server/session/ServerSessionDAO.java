package com.stony.sso.server.session;

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
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/6/1 </p>
 * <p>Time: 14:35 </p>
 * <p>Version: 1.0 </p>
 */
public class ServerSessionDAO extends CachingSessionDAO implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(ServerSessionDAO.class);

    private PermissionService permissionService;
    private String appKey;

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }


    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }


    @Override
    protected void doUpdate(Session session) {
        logger.info("update appKey[{}] of session [{}] ", appKey, session);
        permissionService.updateSession(appKey, (SimpleSession) session);
    }
    @Override
    protected void doDelete(Session session) {
        logger.info("delete appKey[{}] of session [{}] ", appKey, session);
        permissionService.deleteSession(appKey, (SimpleSession) session);
    }
    @Override
    protected Session doReadSession(Serializable sessionId) {
        Session session = permissionService.getSession(appKey, String.valueOf(sessionId));
        logger.info("read Session appKey[{}] of session [{}] ", appKey, session);
        return session;
    }
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = permissionService.createSession((SimpleSession) session);
        assignSessionId(session, sessionId);
        logger.info("create session appKey[{}] of session [{}] ", appKey, session);
        return sessionId;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(permissionService, "Property 'permissionService' is required");
        Assert.notNull(appKey, "Property 'appKey' is required");
    }
}
