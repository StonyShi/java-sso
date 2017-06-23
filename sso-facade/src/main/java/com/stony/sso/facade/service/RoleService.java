package com.stony.sso.facade.service;

import com.stony.sso.facade.entity.Role;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>Created with car-facade-security.</p>
 * <p>User: Stony</p>
 * <p>Date: 2016/4/25</p>
 * <p>Time: 18:30</p>
 * <p>Version: 1.0</p>
 */
public interface RoleService {


    public Role createRole(Role role);
    public Role updateRole(Role role);
    public int deleteRole(Long roleId);

    /**
     * 批量删除角色
     * @param roleIds
     */
    int deleteRoles(String roleIds);

    /**
     * 批量禁用角色
     * @param roleIds
     */
    int disableRoles(String roleIds);

    public Role findOne(Long roleId);
    public List<Role> findAll();
    public List<Role> findAllRole(Integer roleType);

    /**
     * 根据角色编号得到角色标识符列表
     * @param roleIds
     * @return
     */
    public List<Role> findRoleList(Long... roleIds);
    public List<Role> findRoleList(String roleIdsStr);

    /**
     * 角色名称集合
     * @param roleIds
     * @return
     */
    Set<String> findRoleNames(Long... roleIds);

    List<Role> findRolesByAppUser(String appKey, String username);
    List<Role> findAllRolesByUser(String username);

    /**
     * id=role
     * @return
     */
    Map<Long, Role> findRoles();
}
