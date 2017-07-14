package com.stony.sso.service.service;

import com.stony.sso.facade.context.PermissionContext;
import com.stony.sso.facade.entity.*;
import com.stony.sso.facade.service.AuthorizationService;
import com.stony.sso.facade.service.*;
import com.stony.sso.facade.util.PermissionUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
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
    public List<Resource> getMenus(String appKey, String username) {
        logger.info("Enter [appKey : {}, username : {}]", appKey, username);
        return authorizationService.findMenusByAppUser(appKey, username);
    }

    @Override
    public List<Resource> getResources(String appKey) {
        logger.info("Enter.");
        return resourceService.findAll();
    }

    @Override
    public List<Resource> getResources(String appKey, String username) {
        return authorizationService.findResources(appKey, username);
    }

    @Override
    public List<Role> getRoles(String appKey, String username) {
        return authorizationService.findRoles(appKey, username);
    }
}
