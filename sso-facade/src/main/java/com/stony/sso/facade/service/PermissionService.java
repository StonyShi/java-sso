package com.stony.sso.facade.service;

import com.stony.sso.facade.context.PermissionContext;
import com.stony.sso.facade.entity.Resource;
import org.apache.shiro.session.mgt.SimpleSession;

import java.util.List;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/7/14 </p>
 * <p>Time: 11:06 </p>
 * <p>Version: 1.0 </p>
 */
public interface PermissionService {

    /**
     * 获取用户许可信息，包括资源，菜单，权限，角色
     * @param appKey
     * @param username
     * @return
     */
    PermissionContext getPermissions(String appKey, String username);

    List<Resource> getMenus(String appKey, String username);

    List<Resource> getResources(String appKey);
}
