package com.stony.sso.service.service;

import com.google.common.collect.Sets;
import com.stony.sso.cache.annotation.Cachezable;
import com.stony.sso.commons.CollectionUtil;
import com.stony.sso.commons.DateUtils;
import com.stony.sso.facade.entity.*;
import com.stony.sso.facade.enums.ResourceStatus;
import com.stony.sso.facade.enums.ResourceType;
import com.stony.sso.facade.keys.SecurityKeys;
import com.stony.sso.facade.service.*;
import com.stony.sso.facade.util.PermissionUtil;
import com.stony.sso.service.mapper.ResourceMapper;
import com.stony.sso.service.mapper.RoleResourceMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/4/26 </p>
 * <p>Time: 15:48 </p>
 * <p>Version: 1.0 </p>
 */
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {

    @javax.annotation.Resource
    private ResourceMapper resourceMapper;

    @javax.annotation.Resource
    RoleResourceMapper roleResourceMapper;

    public static final String REMOVE_WATCH_KEYS = "ResourcesByIds,PermsByResIds,MenusByPerms,ResourcesByAppUsername,ResourcesByRoleIds";

    @Override
    @Cachezable(key = "Menus,ALL,MAPALL", prefix = SecurityKeys.KEY_FIND_RESOURCE, type = Cachezable.CachezType.REMOVE, watch = REMOVE_WATCH_KEYS)
    public Resource createResource(Resource resource) {
        resourceMapper.insert(resource);
        return resource;
    }

    @Override
    @Cachezable(key = "#resource.id,Menus,ALL,MAPALL", prefix = SecurityKeys.KEY_FIND_RESOURCE, type = Cachezable.CachezType.REMOVE, watch = REMOVE_WATCH_KEYS)
    public Resource updateResource(Resource resource) {
        return resourceMapper.updateByPrimaryKeySelective(resource) > 0 ? resource : null;
    }

    @Override
    @Cachezable(key = "#resourceId,Menus,ALL,MAPALL", prefix = SecurityKeys.KEY_FIND_RESOURCE, type = Cachezable.CachezType.REMOVE, watch = REMOVE_WATCH_KEYS)
    public int deleteResource(Long resourceId) {
        return resourceMapper.deleteByPrimaryKey(resourceId);
    }

    @Override
    @Cachezable(key = "'Resources_' + T(com.zhuanche.car.commons.util.CollectionUtil).replaceComma(#resourceIds), Menus,ALL,MAPALL",
            prefix = SecurityKeys.KEY_FIND_RESOURCE, type = Cachezable.CachezType.REMOVE, watch = REMOVE_WATCH_KEYS)
    public int deleteResources(String resourceIds) {
        Iterable<String> ids = CollectionUtil.split(resourceIds);
        HashSet<String> _ids = Sets.newHashSet(ids);
        int i = 0;
        for (String resourceId : _ids) {
            i += deleteResource(Long.valueOf(resourceId));
        }
        return i;
    }

    @Override
    @Cachezable(key = "'Resources_' + T(com.zhuanche.car.commons.util.CollectionUtil).replaceComma(#resourceIds), Menus,ALL,MAPALL",
            prefix = SecurityKeys.KEY_FIND_RESOURCE, type = Cachezable.CachezType.REMOVE, watch = REMOVE_WATCH_KEYS)
    public int disableResources(String resourceIds) {
        Iterable<String> ids = CollectionUtil.split(resourceIds);
        HashSet<String> _ids = Sets.newHashSet(ids);
        int i = 0;
        for (String resourceId : _ids) {
            if(null != updateResource(new Resource(Long.valueOf(resourceId), ResourceStatus.UNAVAILABLE.STATUS, DateUtils.now()))){
                i++;
            }
        }
        return i;
    }

    @Override
    @Cachezable(key = "#resourceId", prefix = SecurityKeys.KEY_FIND_RESOURCE, type = Cachezable.CachezType.SET)
    public Resource findOne(Long resourceId) {
        return resourceMapper.selectByPrimaryKey(resourceId);
    }

    @Override
    @Cachezable(key = "ALL", prefix = SecurityKeys.KEY_FIND_RESOURCE, type = Cachezable.CachezType.SET)
    public List<Resource> findAll() {
        return resourceMapper.findAll();
    }

    @Override
    //@Cachezable(key = "ALL", prefix = SecurityKeys.KEY_FIND_RESOURCE, type = Cachezable.CachezType.SET)
    public List<Resource> findAllUser(Integer userType) {
        return resourceMapper.findAllUser(userType);
    }

    @Cachezable(key = "Menus", prefix = SecurityKeys.KEY_FIND_RESOURCE, type = Cachezable.CachezType.SET)
    public List<Resource> findMenuAll(){
        return resourceMapper.findTypeAll(ResourceType.MENU.TYPE);
    }

    /**
     *
     * @param ids 逗号分隔
     * @return
     */
    @Cachezable(key = "'Resources_' + T(com.zhuanche.car.commons.util.CollectionUtil).replaceComma(#ids)",
            prefix = SecurityKeys.KEY_FIND_RESOURCE, type = Cachezable.CachezType.SET, watch = "ResourcesByIds")
    public List<Resource> findResources(String ids){
        return resourceMapper.findResources(ids);
    }

    @Override
    @Cachezable(key = "'Perms_' + T(com.zhuanche.car.commons.util.CollectionUtil).arrayLongConvertStr(#resourceIds,'_')",
            prefix = SecurityKeys.KEY_FIND_RESOURCE, type = Cachezable.CachezType.SET, watch = "PermsByResIds")
    public Set<String> findPermissions(Set<Long> resourceIds) {
        Set<String> permissions = new HashSet<String>();
        List<Resource> list = findResources(CollectionUtil.arrayLongConvertStr(resourceIds));
        for(Resource resource : list){
            if(resource != null && !StringUtils.isEmpty(resource.getPermission())) {
                permissions.add(resource.getPermission());
            }
        }
        return permissions;
    }

    /**
     *
     * @param permissions
     * @return
     * @see #findMenus(List, Set)
     */
    @Override
    @Cachezable(key = "'Menus_' + T(com.zhuanche.car.commons.util.CollectionUtil).arrayStringConvertStr(#permissions,'_')",
            prefix = SecurityKeys.KEY_FIND_RESOURCE, type = Cachezable.CachezType.SET, watch = "MenusByPerms")
    public List<Resource> findMenus(Set<String> permissions) {
        List<Resource> allResources = findMenuAll();
        return findMenus(allResources,permissions);
    }

    @Cachezable(key = "'Menus_' + T(com.zhuanche.car.commons.util.CollectionUtil).arrayStringConvertStr(#permissions,'_')",
            prefix = SecurityKeys.KEY_FIND_RESOURCE, type = Cachezable.CachezType.SET, watch = "MenusByPerms")
    public List<Resource> findMenus(List<Resource> allResources, Set<String> permissions){
        List<Resource> menus = new ArrayList<Resource>();
        for(Resource resource : allResources) {
//            if(resource.isRootNode()) {
//                continue;
//            }
            if(!resource.isAvailabled()){
                continue;
            }
            if(resource.isButton()) {
                continue;
            }
            if(!hasPermission(permissions, resource)) {
                continue;
            }
            menus.add(resource);
        }
        return menus;
    }

    @Override
    @Cachezable(key = "'Resources_' + #appKey + '_' + #username",
            prefix = SecurityKeys.KEY_FIND_RESOURCE, type = Cachezable.CachezType.SET, watch = "ResourcesByAppUsername")
    public List<Resource> findResourcesByAppUser(String appKey, String username) {
        Map<String,String> map = new HashMap<>();
        map.put("appKey", appKey);
        map.put("username", username);
        return resourceMapper.findResourcesByAppUser(map);
    }

    @Override
    @Cachezable(key = "'RoleResources_' + T(com.zhuanche.car.commons.util.CollectionUtil).replaceComma(#roleIds)",
            prefix = SecurityKeys.KEY_FIND_RESOURCE, type = Cachezable.CachezType.SET, watch = "ResourcesByRoleIds")
    public List<Resource> findResourcesByRoleIds(String roleIds) {
        return resourceMapper.findResourcesByRoleIds(roleIds);
    }

    @Override
    public List<Resource> findMenus(List<Resource> permissionsResources) {
        List<Resource> menus = new ArrayList<>();
        for(Resource resource : permissionsResources) {
            if(!resource.isAvailabled()){
                continue;
            }
            if(resource.isButton()) {
                continue;
            }
            menus.add(resource);
        }
        return menus;
    }

    @Override
    @Cachezable(key = "Menus,ALL,MAPALL", prefix = SecurityKeys.KEY_FIND_RESOURCE, type = Cachezable.CachezType.REMOVE, watch = REMOVE_WATCH_KEYS)
    public int updateRoleResources(Long roleId, String resourceIds) {
        Iterable<String> ids = CollectionUtil.split(resourceIds);
        HashSet<String> _ids = Sets.newHashSet(ids);
        List<RoleResource> roleResources = new ArrayList<>();
        for(String resourceId : _ids){
            RoleResource roleResource = new RoleResource(roleId, Long.valueOf(resourceId), ResourceStatus.AVAILABLE.STATUS, DateUtils.now());
            roleResources.add(roleResource);
        }
        int x = roleResourceMapper.deleteRoleResourcesById(roleId);
        int y = roleResourceMapper.saveRoleResources(roleResources);
        return y;
    }

    @Override
    @Cachezable(key = "MAPALL", prefix = SecurityKeys.KEY_FIND_RESOURCE, type = Cachezable.CachezType.SET)
    public Map<Long, Resource> findResources() {
        Map<Long, Resource> map = new HashMap<>();
        List<Resource> list = findAll();
        for(Resource resource : list){
            map.put(resource.getId(), resource);
        }
        return map;
    }

    private boolean hasPermission(Set<String> permissions, Resource resource) {
        return PermissionUtil.hasPermission(permissions,resource);
    }
}
