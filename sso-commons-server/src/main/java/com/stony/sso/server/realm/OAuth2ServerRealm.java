package com.stony.sso.server.realm;

import com.stony.sso.facade.entity.PermissionEntity;
import com.stony.sso.facade.entity.User;
import com.stony.sso.facade.service.AuthorizationService;
import com.stony.sso.facade.service.UserService;
import com.stony.sso.facade.util.PermissionUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/4/29 </p>
 * <p>Time: 9:33 </p>
 * <p>Version: 1.0 </p>
 * @see org.apache.shiro.authc.UsernamePasswordToken
 */
public class OAuth2ServerRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(OAuth2ServerRealm.class);

    @javax.annotation.Resource
    private UserService userService;

    @javax.annotation.Resource
    private AuthorizationService authorizationService;

    private String appKey;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        PermissionEntity entity = (PermissionEntity) principals.getPrimaryPrincipal();
//        String username = (String)principals.getPrimaryPrincipal();
        final String username = entity.getUsername();
        logger.info("appKey = {} ,username = {}",appKey, username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(PermissionUtil.getRoleNameByRoles(authorizationService.findRoles(appKey, username)));
        authorizationInfo.setStringPermissions(PermissionUtil.getPermissionsByResources(authorizationService.findResources(appKey, username)));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();
        logger.info("username = {}", username);
        User user = userService.findByUsername(username);
        if(user == null) {
            throw new UnknownAccountException();//没找到帐号
        }
        if(user.isLock()) {
            throw new LockedAccountException(); //帐号锁定
        }
        PermissionEntity entity = new PermissionEntity();
        entity.setUsername(username);
        entity.setUserId(user.getId());
        entity.setUser(user);
        entity.setClientId(appKey);
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                entity, //用户
                user.getSea(), //密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
        logger.info("清理授权信息缓存");
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
        logger.info("清理身份验证信息缓存");
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

}
