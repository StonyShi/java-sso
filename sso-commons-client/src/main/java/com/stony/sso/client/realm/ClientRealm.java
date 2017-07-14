package com.stony.sso.client.realm;



import com.stony.sso.facade.context.PermissionContext;
import com.stony.sso.facade.service.PermissionService;
import com.stony.sso.facade.util.PermissionUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Created with car-commons-security.</p>
 * <p>User: Stony</p>
 * <p>Date: 2016/4/25</p>
 * <p>Time: 18:30</p>
 */
@Deprecated
public class ClientRealm extends AuthorizingRealm implements InitializingBean{

    private static final Logger logger = LoggerFactory.getLogger(ClientRealm.class);

    private PermissionService permissionService;
    private String appKey;
    private String remoteServiceUrl;

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public void setRemoteServiceUrl(String remoteServiceUrl) {
        this.remoteServiceUrl = remoteServiceUrl;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        logger.info("Enter [appKey : {}, username : {}]", appKey, username);
        if(!SecurityUtils.getSubject().isAuthenticated()){
            throw new UnauthenticatedException("AppKey : " + appKey + " , username : " + username + " 身份验证失败.");
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Map<String ,Object> urlVariables = new HashMap<>();
        urlVariables.put("username", username);
        urlVariables.put("appKey", appKey);
        logger.info("授权信息验证 appKey [{}] of username [{}]", appKey, username);
        PermissionContext context = permissionService.getPermissions(appKey, username);
        authorizationInfo.setRoles(PermissionUtil.getRoleNameByRoles(context.getRoles()));
        authorizationInfo.setStringPermissions(context.getPermissions());
        logger.info("PermissionContext =  ", context);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //永远不会被调用
        throw new UnsupportedOperationException("身份验证调用错误!");
    }

    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(permissionService, "Property 'permission' is required");
        Assert.notNull(appKey, "Property 'appKey' is required");
        Assert.notNull(remoteServiceUrl, "Property 'remoteServiceUrl' is required");
    }
}
