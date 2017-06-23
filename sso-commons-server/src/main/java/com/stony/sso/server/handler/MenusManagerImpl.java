package com.stony.sso.server.handler;

import com.stony.sso.commons.security.SecurityConstants;
import com.stony.sso.facade.entity.Resource;
import com.stony.sso.facade.service.AuthorizationService;
import com.stony.sso.facade.service.MenusManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
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

    @javax.annotation.Resource
    private AuthorizationService authorizationService;

    @Override
    public List<Resource> getMenus(){
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        List<Resource> menus = authorizationService.findMenusByAppUser(SecurityConstants.SERVER_APP_KEY, username);
        logger.debug("username = {}, menus = {}",username, menus);
        return menus;
    }
    public List<Resource> getMenus(String active){
        List<Resource> menus = getMenus();
        List<Resource> useMenus = new LinkedList<>();
        Long pid = -1L;
        for(Resource menu : menus){
            if(menu.getUrl().equals(active)){
                menu.setType("active");
                pid = menu.getParentId();
            }
            useMenus.add(menu);
        }
        List<Resource> resultMenus = new LinkedList<>();
        for(Resource menu : useMenus){
            if(menu.getParentId().compareTo(pid) == 0){
                menu.setType("selected");
            }
            resultMenus.add(menu);
        }
        return resultMenus;
    }
}
