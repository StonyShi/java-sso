package com.stony.sso.server.manger.invoker;

import com.stony.sso.facade.context.PermissionContext;
import com.stony.sso.facade.entity.Resource;
import com.stony.sso.facade.service.AuthorizationService;
import com.stony.sso.facade.service.PermissionService;
import com.stony.sso.facade.service.ResourceService;
import com.stony.sso.facade.util.PermissionUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Set;


/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/5/13 </p>
 * <p>Time: 17:22 </p>
 * <p>Version: 1.0 </p>
 * HttpInvoker 调用
 */
public class PermissionController implements PermissionService {

    private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);

    @javax.annotation.Resource
    private AuthorizationService authorizationService;

    @javax.annotation.Resource
    private ResourceService resourceService;

    @javax.annotation.Resource
    private SessionDAO sessionDAO;


    @Override
    public SimpleSession getSession(String appKey, String sessionId) {
        logger.info("Enter [appKey : {},sessionId : {}]", appKey, sessionId);
        return (SimpleSession) sessionDAO.readSession(sessionId);
    }

    @Override
    public String createSession(SimpleSession session) {
        logger.info("Enter [session : {}]", session);
        return String.valueOf(sessionDAO.create(session));
    }

    @Override
    public void updateSession(String appKey, SimpleSession session) {
        logger.info("Enter [appKey : {},session : {}]", appKey, session);
        sessionDAO.update(session);
    }

    @Override
    public void deleteSession(String appKey, SimpleSession session) {
        logger.info("Enter [appKey : {},session : {}]", appKey, session);
        sessionDAO.delete(session);
    }

    @Override
    public PermissionContext getPermissions(String appKey, String username) {
        logger.info("Enter [appKey : {}, username : {}]", appKey, username);
        if(!SecurityUtils.getSubject().isAuthenticated()){
            throw new UnauthenticatedException("AppKey : " + appKey + " , username : " + username + " 身份验证失败.");
        }
        PermissionContext permissionContext = new PermissionContext();
        List<Resource> resourceList = authorizationService.findResources(appKey, username);
        Set<String> permissions = PermissionUtil.getPermissionsByResources(resourceList);
        permissionContext.setResources(resourceList);
        permissionContext.setMenus(resourceService.findMenus(resourceList, permissions));
        permissionContext.setPermissions(permissions);
        return permissionContext;
    }

    @Override
    public PermissionContext getMenus(String appKey, String username) {
        PermissionContext permissionContext = new PermissionContext();
        permissionContext.setMenus(authorizationService.findMenusByAppUser(appKey, username));
        return permissionContext;
    }
}
