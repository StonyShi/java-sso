package com.stony.sso.service.service;

import com.google.common.collect.Sets;
import com.stony.sso.cache.annotation.Cachezable;
import com.stony.sso.commons.CollectionUtil;
import com.stony.sso.commons.MapPlaceholderHelper;
import com.stony.sso.facade.entity.App;
import com.stony.sso.facade.entity.*;
import com.stony.sso.facade.keys.SecurityKeys;
import com.stony.sso.facade.service.AppService;
import com.stony.sso.facade.service.AuthorizationService;
import com.stony.sso.facade.service.*;
import com.stony.sso.facade.util.PermissionUtil;
import com.stony.sso.service.mapper.UserAppRoleMapper;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/4/26 </p>
 * <p>Time: 14:14 </p>
 * <p>Version: 1.0 </p>
 */
@Service("authorizationService")
public class AuthorizationServiceImpl implements AuthorizationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationServiceImpl.class);

    @javax.annotation.Resource
    private UserAppRoleMapper userAppRoleMapper;

    @javax.annotation.Resource
    private UserService userService;
    @javax.annotation.Resource
    private RoleService roleService;
    @javax.annotation.Resource
    private ResourceService resourceService;
    @javax.annotation.Resource
    private AppService appService;
    @javax.annotation.Resource
    private GlobalVariableService globalVariableService;

    public static final String REMOVE_WATCH_KEYS = "RolesByAppUsername,ResourcesByAppUsername";

    private final MapPlaceholderHelper helper = new MapPlaceholderHelper("${", "}");

    @Override
    @Cachezable(key = "ALL", prefix = SecurityKeys.KEY_FIND_AUTHORIZATION, type = Cachezable.CachezType.REMOVE, watch = REMOVE_WATCH_KEYS)
    public int createAuthorization(Long user, Long app, String roleIds) {
        Iterable<String> ids = CollectionUtil.split(roleIds);
        HashSet<String> _ids = Sets.newHashSet(ids);
        List<UserAppRole> saveAuths = new ArrayList<>();
        List<UserAppRole> all = findAll();
        for(String roleId : _ids){
            UserAppRole auth = new UserAppRole();
            auth.setUserId(user);
            auth.setAppId(app);
            auth.setRoleId(Long.valueOf(roleId));
            if(!exist(auth, all)){
                saveAuths.add(auth);
            }
        }
        if(saveAuths.isEmpty()){
            return 0;
        }
        logger.info("save : {}", saveAuths);
        return userAppRoleMapper.saveAuthorization(saveAuths);
    }
    protected boolean exist(UserAppRole authorization , List<UserAppRole> all){
        for(UserAppRole ah : all){
            if(compareToLong(authorization.getUserId(),ah.getUserId())
                    && compareToLong(authorization.getAppId(), ah.getAppId())
                    && compareToLong(authorization.getRoleId(), ah.getRoleId())){
                return true;
            }
        }
        return false;
    }
    protected boolean compareToLong(Long a, Long b){
        return a.compareTo(b) == 0;
    }
    @Cachezable(key = "ALL", prefix = SecurityKeys.KEY_FIND_AUTHORIZATION, type = Cachezable.CachezType.REMOVE, watch = REMOVE_WATCH_KEYS)
    public UserAppRole createAuthorization(UserAppRole authorization) {
        return merge(authorization);
    }

    @Cachezable(key = "#authorization.id,ALL", prefix = SecurityKeys.KEY_FIND_AUTHORIZATION, type = Cachezable.CachezType.REMOVE, watch = REMOVE_WATCH_KEYS)
    public UserAppRole updateAuthorization(UserAppRole authorization) {
        return merge(authorization);
    }
    @Cachezable(key = "#authorization.id,ALL", prefix = SecurityKeys.KEY_FIND_AUTHORIZATION, type = Cachezable.CachezType.REMOVE, watch = REMOVE_WATCH_KEYS)
    public UserAppRole updateAuthorizationRoles(UserAppRole authorization){
        long row = userAppRoleMapper.updateByPrimaryKey(authorization);
        authorization.setId(row);
        return authorization;
    }
    @Cachezable(key = "#authorizationId,ALL", prefix = SecurityKeys.KEY_FIND_AUTHORIZATION, type = Cachezable.CachezType.REMOVE, watch = REMOVE_WATCH_KEYS)
    public void deleteAuthorization(Long authorizationId) {
        userAppRoleMapper.deleteByPrimaryKey(authorizationId);
    }

    public UserAppRole merge(UserAppRole authorization) {
        UserAppRole dbAuthorization = userAppRoleMapper.findByAppUser(authorization);
        if(dbAuthorization ==  null) {//如果数据库中不存在相应记录 直接新增
            long id = userAppRoleMapper.insert(authorization);
            return authorization;
        }
        if(dbAuthorization.equals(authorization)) { //如果是同一条记录直接更新即可
            return userAppRoleMapper.updateByPrimaryKeySelective(authorization) > 0 ? authorization : null;
        }
        return userAppRoleMapper.updateByPrimaryKeySelective(dbAuthorization) > 0 ? authorization : null;
    }



    @Override
    @Cachezable(key = "#authorizationId", prefix = SecurityKeys.KEY_FIND_AUTHORIZATION, type = Cachezable.CachezType.SET)
    public UserAppRole findOne(Long authorizationId) {
        return userAppRoleMapper.selectByPrimaryKey(authorizationId);
    }

    @Override
    @Cachezable(key = "ALL", prefix = SecurityKeys.KEY_FIND_AUTHORIZATION, type = Cachezable.CachezType.SET)
    public List<UserAppRole> findAll() {
        List<UserAppRole> authorizations = userAppRoleMapper.findAll();
        Map<Long, User> users = userService.findUsers();
        Map<Long, Role> roles = roleService.findRoles();
        Map<Long, App> apps = appService.findApps();
        List<UserAppRole> list = new ArrayList<>();
        for(UserAppRole authorization : authorizations){
            authorization.setApp(apps.get(authorization.getAppId()));
            authorization.setUser(users.get(authorization.getUserId()));
            authorization.setRole(roles.get(authorization.getRoleId()));
            list.add(authorization);
        }
        return list;
    }

    public List<UserAppRole> findAllApp(Integer userType) {
        List<UserAppRole> authorizations = userAppRoleMapper.findAllApp(userType);
        Map<Long, User> users = userService.findUsers();
        Map<Long, Role> roles = roleService.findRoles();
        Map<Long, App> apps = appService.findApps();
        List<UserAppRole> list = new ArrayList<>();
        for(UserAppRole authorization : authorizations){
            authorization.setApp(apps.get(authorization.getAppId()));
            authorization.setUser(users.get(authorization.getUserId()));
            authorization.setRole(roles.get(authorization.getRoleId()));
            list.add(authorization);
        }
        return list;
    }

    @Cachezable(key = "'Roles_' + #appKey + '_' + #username",
            prefix = SecurityKeys.KEY_FIND_ROLE, type = Cachezable.CachezType.SET, watch = "RolesByAppUsername")
    public List<Role> findRoles(String appKey, String username) {
        return roleService.findRolesByAppUser(appKey,username);
    }


    @Cachezable(key = "'Roles_' + #username",
            prefix = SecurityKeys.KEY_FIND_All_ROLE, type = Cachezable.CachezType.SET, watch = "RolesByUsername")
    public List<Role> findAllRoles(String username){
        return roleService.findAllRolesByUser(username);
    }


    @Override
    @Cachezable(key = "'Resources_' + #appKey + '_' + #username",
            prefix = SecurityKeys.KEY_FIND_RESOURCE, type = Cachezable.CachezType.SET, watch = "ResourcesByAppUsername")
    public List<Resource> findResources(String appKey, String username) {
        return resourceService.findResourcesByAppUser(appKey, username);
    }

    /**
     * 判断用户是否拥有权限
     * @param appKey
     * @param username
     * @param roleIds
     * @return
     */
    public Boolean isUserAtRole(String appKey, String username, String roleIds) {
        List<Resource> userResources = findResources(appKey, username);
        if (userResources == null) {
            return false;
        }
        List<Resource> roleResources = resourceService.findResourcesByRoleIds(roleIds);
        return roleResources != null && PermissionUtil.hasPermission(userResources, roleResources);
    }

    @Override
    public List<Resource> findMenusByAppUser(String appKey, String username) {
        List<Resource> resourceList = resourceService.findResourcesByAppUser(appKey, username);
        List<Resource> menus = new ArrayList<>();
        Map<String, String> maps = globalVariableService.getFieldVariables();
        for (Resource resource : resourceList) {
            if (!resource.isAvailabled()) {
                continue;
            }
            if (resource.isButton()) {
                continue;
            }
            String url_old = resource.getUrl() == null ? "":resource.getUrl();
            resource.setUrl(helper.replacePlaceholders(url_old, maps));
            logger.debug("【转换地址】url={};【转换后】url={}", url_old, resource.getUrl());
            menus.add(resource);
        }
        return menus;
    }

    @Override
    public PermissionEntity getPermissionEntity(String appKey, String username) {
        PermissionEntity entity = new PermissionEntity();
        entity.setResources(findResources(appKey, username));
        entity.setRoles(findRoles(appKey, username));
        return entity;
    }

    @Override
    @Cachezable(key = "ALL", prefix = SecurityKeys.KEY_FIND_AUTHORIZATION, type = Cachezable.CachezType.REMOVE, watch = REMOVE_WATCH_KEYS)
    public int deleteAuthorizations(List<UserAppRole> authorizations) {
        int i = 0;
        for(UserAppRole authorization : authorizations){
            i += userAppRoleMapper.delete(authorization);
        }
        return i;
    }
}
