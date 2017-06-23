package com.stony.sso.service.service;

import com.stony.sso.facade.context.PermissionContext;
import com.stony.sso.facade.entity.*;
import com.stony.sso.facade.service.AuthorizationService;
import com.stony.sso.facade.service.*;
import com.stony.sso.facade.util.PermissionUtil;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/4/26 </p>
 * <p>Time: 11:27 </p>
 * <p>Version: 1.0 </p>
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    private static final Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);


    @javax.annotation.Resource
    private ResourceService resourceService;

    @javax.annotation.Resource
    private AuthorizationService authorizationService;

    @javax.annotation.Resource
    private SessionDAO sessionDAO;

    @Override
    public SimpleSession getSession(String appKey, String sessionId) {
        logger.info("Enter [appKey : {},sessionId : {}]", appKey, sessionId);
        return (SimpleSession) getSessionDAO().readSession(sessionId);
    }

    @Override
    public String createSession(SimpleSession session) {
        logger.info("Enter [session : {}]", session);
        return getSessionDAO().create(session).toString();
    }

    @Override
    public void updateSession(String appKey, SimpleSession session) {
        logger.info("Enter [appKey : {},session : {}]", appKey, session);
        getSessionDAO().update(session);
    }

    @Override
    public void deleteSession(String appKey, SimpleSession session) {
        logger.info("Enter [appKey : {},session : {}]", appKey, session);
        getSessionDAO().delete(session);
    }

    @Override
    public PermissionContext getPermissions(String appKey, String username) {
        logger.info("Enter [appKey : {}, username : {}]", appKey, username);
        PermissionContext permissionContext = new PermissionContext();
        List<Resource> resourceList = authorizationService.findResources(appKey, username);
        Set<String> permissions = PermissionUtil.getPermissionsByResources(resourceList);
        permissionContext.setResources(resourceList);
        permissionContext.setMenus(resourceService.findMenus(resourceList, permissions));
        permissionContext.setPermissions(permissions);
        return permissionContext;
    }

    public SessionDAO getSessionDAO() {
        return sessionDAO;
    }
}
