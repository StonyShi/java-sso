package com.stony.sso.client.handler;


import com.stony.sso.facade.entity.PermissionEntity;
import com.stony.sso.facade.entity.Resource;
import com.stony.sso.facade.service.MenusManager;
import com.stony.sso.client.ClientInfoHold;
import com.stony.sso.facade.service.PermissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/5/7 </p>
 * <p>Time: 18:44 </p>
 * <p>Version: 1.0 </p>
 */
public class MenusManagerImpl implements MenusManager {
    private static final Logger logger = LoggerFactory.getLogger(MenusManagerImpl.class);

    /**
     * HttpInvoker
     */
    @javax.annotation.Resource
    PermissionService permissionService;

    @Override
    public List<Resource> getMenus(){
        Subject subject = SecurityUtils.getSubject();
        List<Resource> menus = null;
        String username = null;
        if (subject.isAuthenticated()) {
            PermissionEntity entity = (PermissionEntity) subject.getPrincipal();
            username = entity.getUsername();
            menus = permissionService.getMenus(ClientInfoHold.APP_KEY, entity.getAccessToken());
        } else {
            menus = Collections.EMPTY_LIST;
        }
        logger.debug("username = {}, menus = {}", username, menus);
        return menus;
    }
}
