package com.stony.sso.facade.service;


import com.stony.sso.facade.entity.PermissionEntity;
import com.stony.sso.facade.entity.UserAppRole;
import com.stony.sso.facade.entity.Resource;
import com.stony.sso.facade.entity.Role;

import java.util.List;

/**
 * <p>Created with car-facade-security.</p>
 * <p>User: Stony</p>
 * <p>Date: 2016/4/25</p>
 * <p>Time: 18:30</p>
 * <p>Version: 1.0</p>
 * @see UserAppRole
 */
public interface AuthorizationService {

    /**
     * 去重添加
     * @param user
     * @param app
     * @param roleIds
     * @return
     */
    int createAuthorization(Long user, Long app, String roleIds);


    /**
     * 批量删除授权
     * @param authorizations
     */
    int deleteAuthorizations(List<UserAppRole> authorizations);

    /**
     * user_id,app_id 存在 合并，否则只添加
     * @param authorization
     * @return
     */
    public UserAppRole createAuthorization(UserAppRole authorization);

    /**
     * user_id,app_id 存在 合并，否则只添加
     * @param authorization
     * @return
     */
    public UserAppRole updateAuthorization(UserAppRole authorization);

    /**
     * 更新role_ids
     * @param authorization
     * @return
     */
    public UserAppRole updateAuthorizationRoles(UserAppRole authorization);
    public void deleteAuthorization(Long authorizationId);

    public UserAppRole findOne(Long authorizationId);
    public List<UserAppRole> findAll();
    public List<UserAppRole> findAllApp(Integer userType);

    /**
     * 获取APP和用户的角色列表
     * @param appKey
     * @param username
     * @return
     */
    public List<Role> findRoles(String appKey, String username);

    /**
     * 获取用户的角色列表
     * @param username
     * @return
     */
    public List<Role> findAllRoles(String username);


    /**
     * 根据AppKey和用户名查找资源
     * @param appKey
     * @param username
     * @return
     */
    public List<Resource> findResources(String appKey, String username);

    /**
     * 判断用户是否拥有权限
     * @param appKey
     * @param username
     * @param roleIds
     * @return
     */
    public Boolean isUserAtRole(String appKey, String username, String roleIds);



    List<Resource> findMenusByAppUser(String appKey, String username);

    PermissionEntity getPermissionEntity(String appKey, String username);

    List<Resource> convertMenus(List<Resource> resourceList);

}
