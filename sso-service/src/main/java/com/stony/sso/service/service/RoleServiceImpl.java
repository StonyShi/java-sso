package com.stony.sso.service.service;

import com.google.common.collect.Sets;
import com.stony.sso.cache.annotation.Cachezable;
import com.stony.sso.commons.CollectionUtil;
import com.stony.sso.commons.DateUtils;
import com.stony.sso.facade.entity.*;
import com.stony.sso.facade.enums.ResourceStatusEnum;
import com.stony.sso.facade.keys.SecurityKeys;
import com.stony.sso.facade.service.*;
import com.stony.sso.service.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/4/26 </p>
 * <p>Time: 15:51 </p>
 * <p>Version: 1.0 </p>
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @javax.annotation.Resource
    private RoleMapper roleMapper;
    @javax.annotation.Resource
    private ResourceService resourceService;

    public static final String REMOVE_WATCH_KEYS = "RolesByIds,RoleNamesByIds,RolesByAppUsername";

    @Override
    @Cachezable(key = "ALL,MAPALL", prefix = SecurityKeys.KEY_FIND_ROLE, type = Cachezable.CachezType.REMOVE, watch = REMOVE_WATCH_KEYS)
    public Role createRole(Role role) {
        roleMapper.insert(role);
        return role;
    }
    @Override
    @Cachezable(key = "#role.id,ALL,MAPALL", prefix = SecurityKeys.KEY_FIND_ROLE, type = Cachezable.CachezType.REMOVE, watch = REMOVE_WATCH_KEYS)
    public Role updateRole(Role role) {
        return roleMapper.updateByPrimaryKeySelective(role) > 0 ? role : null;
    }
    @Override
    @Cachezable(key = "#roleId,ALL,MAPALL", prefix = SecurityKeys.KEY_FIND_ROLE, type = Cachezable.CachezType.REMOVE, watch = REMOVE_WATCH_KEYS)
    public int deleteRole(Long roleId) {
        return roleMapper.deleteByPrimaryKey(roleId);
    }

    @Override
    @Cachezable(key = "'Roles_' + T(com.zhuanche.car.commons.util.CollectionUtil).replaceComma(#roleIdsStr), ALL,MAPALL",
            prefix = SecurityKeys.KEY_FIND_ROLE, type = Cachezable.CachezType.REMOVE, watch = REMOVE_WATCH_KEYS)
    public int deleteRoles(String roleIdsStr) {
        Iterable<String> ids = CollectionUtil.split(roleIdsStr);
        HashSet<String> _ids = Sets.newHashSet(ids);
        int i = 0;
        for (String roleId : _ids) {
            i += deleteRole(Long.valueOf(roleId));
        }
        return i;
    }

    @Override
    @Cachezable(key = "'Roles_' + T(com.zhuanche.car.commons.util.CollectionUtil).replaceComma(#roleIdsStr), ALL,MAPALL",
            prefix = SecurityKeys.KEY_FIND_ROLE, type = Cachezable.CachezType.REMOVE, watch = REMOVE_WATCH_KEYS)
    public int disableRoles(String roleIdsStr) {
        Iterable<String> ids = CollectionUtil.split(roleIdsStr);
        HashSet<String> _ids = Sets.newHashSet(ids);
        int i = 0;
        for (String roleId : _ids) {
            if(null != updateRole(new Role(Long.valueOf(roleId), ResourceStatusEnum.UNAVAILABLE.STATUS, DateUtils.now()))){
                i++;
            }
        }
        return i;
    }

    @Override
    @Cachezable(key = "#roleId", prefix = SecurityKeys.KEY_FIND_ROLE, type = Cachezable.CachezType.SET)
    public Role findOne(Long roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    @Cachezable(key = "ALL", prefix = SecurityKeys.KEY_FIND_ROLE, type = Cachezable.CachezType.SET)
    public List<Role> findAll() {
        return roleMapper.findAll();
    }


    @Cachezable(key = "'Roles_' + T(com.zhuanche.car.commons.util.CollectionUtil).arrayLongConvertStr(#roleIds,'_')",
            prefix = SecurityKeys.KEY_FIND_ROLE, type = Cachezable.CachezType.SET, watch = "RolesByIds")
    public List<Role> findRoleList(Long... roleIds){
//        List<Role> roles = findAll();
//        List<Role> roleList = new ArrayList<Role>();
//        List<Long> roleIdsList = Arrays.asList(roleIds);
//        for(Role role : roles){
//            if(roleIdsList.contains(role.getId())) {
//                roleList.add(role);
//            }
//        }
//        if(roleList.size() > 0){
//            return roleList;
//        }
        String roleIdsStr = CollectionUtil.arrayLongConvertStr(roleIds);
        return findRoleList(roleIdsStr);
    }

    /**
     *
     * @param roleIdsStr 逗号分隔
     * @return
     */
    @Override
    @Cachezable(key = "'Roles_' + T(com.zhuanche.car.commons.util.CollectionUtil).replaceComma(#roleIdsStr)",
            prefix = SecurityKeys.KEY_FIND_ROLE, type = Cachezable.CachezType.SET, watch = "RolesByIds")
    public List<Role> findRoleList(String roleIdsStr) {
        return roleMapper.findRoles(roleIdsStr);
    }

    @Override
    @Cachezable(key = "'RoleNames_' + T(com.zhuanche.car.commons.util.CollectionUtil).arrayLongConvertStr(#roleIds,'_')",
            prefix = SecurityKeys.KEY_FIND_ROLE, type = Cachezable.CachezType.SET, watch = "RoleNamesByIds")
    public Set<String> findRoleNames(Long... roleIds) {
        Set<String> roles = new HashSet<String>();
        List<Role> roleList = findRoleList(roleIds);
        for(Role role : roleList){
            if(role != null) {
                roles.add(role.getRole());
            }
        }
        return roles;
    }

    @Override
    @Cachezable(key = "'Roles_' + #appKey + '_' + #username",
            prefix = SecurityKeys.KEY_FIND_ROLE, type = Cachezable.CachezType.SET, watch = "RolesByAppUsername")
    public List<Role> findRolesByAppUser(String appKey, String username) {
        Map<String,String> map = new HashMap<>();
        map.put("appKey", appKey);
        map.put("username", username);
        return roleMapper.findRolesByAppUser(map);
    }

    @Override
    @Cachezable(key = "'Roles_' + #username",
            prefix = SecurityKeys.KEY_FIND_All_ROLE, type = Cachezable.CachezType.SET, watch = "RolesByUsername")
    public List<Role> findAllRolesByUser(String username) {
        Map<String,String> map = new HashMap<>();
        map.put("username", username);
        return roleMapper.findRolesByAppUser(map);
    }

    @Override
    @Cachezable(key = "MAPALL", prefix = SecurityKeys.KEY_FIND_ROLE, type = Cachezable.CachezType.SET)
    public Map<Long, Role> findRoles() {
        Map<Long, Role> map = new HashMap<>();
        List<Role> list = findAll();
        for(Role role : list){
            map.put(role.getId(), role);
        }
        return map;
    }
}
