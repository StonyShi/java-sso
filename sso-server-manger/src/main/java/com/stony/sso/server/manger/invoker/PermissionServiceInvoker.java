package com.stony.sso.server.manger.invoker;

import com.stony.sso.facade.context.PermissionContext;
import com.stony.sso.facade.entity.Resource;
import com.stony.sso.facade.entity.TokenInfo;
import com.stony.sso.facade.service.OAuthService;
import com.stony.sso.facade.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;


/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/5/13 </p>
 * <p>Time: 17:22 </p>
 * <p>Version: 1.0 </p>
 * HttpInvoker 调用
 */
public class PermissionServiceInvoker implements PermissionService {

    @javax.annotation.Resource
    private PermissionService permissionService;

    @javax.annotation.Resource
    OAuthService oAuthService;

    @Override
    public PermissionContext getPermissions(String appKey, String username) {
        if (!oAuthService.checkClientId(appKey)) {
            return new PermissionContext();
        }
        return permissionService.getPermissions(appKey, username);
    }

    @Override
    public List<Resource> getMenus(String appKey, String accessToken) {
        TokenInfo token = oAuthService.getToken(accessToken);
        if(token == null || token.getUsername() == null){
            return Collections.EMPTY_LIST;
        }
        return permissionService.getMenus(appKey, token.getUsername());
    }

    @Override
    public List<Resource> getResources(String appKey) {
        if (!oAuthService.checkClientId(appKey)) {
            return Collections.EMPTY_LIST;
        }
        return permissionService.getResources(appKey);
    }
}
