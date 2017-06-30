package com.stony.sso.server.manger.invoker;

import com.stony.sso.facade.context.PermissionContext;
import com.stony.sso.facade.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/5/13 </p>
 * <p>Time: 17:22 </p>
 * <p>Version: 1.0 </p>
 * HttpInvoker 调用
 */
@Controller
@RequestMapping(value = "/invoker/permission")
public class PermissionServiceInvoker implements PermissionService {

    @javax.annotation.Resource
    private PermissionService permissionService;

    @Override
    public PermissionContext getPermissions(String appKey, String username) {
        return permissionService.getPermissions(appKey, username);
    }

    @Override
    @RequestMapping(value = "menus")
    public PermissionContext getMenus(String appKey, String username) {
        return permissionService.getMenus(appKey, username);
    }

    @Override
    @RequestMapping(value = "resources")
    public PermissionContext getResources() {
        return permissionService.getResources();
    }
}
