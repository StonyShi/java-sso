package com.stony.sso.service.mapper;

import com.stony.sso.facade.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoleMapper {
    /**
     * sys_role
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * sys_role
     *
     * @mbggenerated
     */
    int insert(Role record);

    /**
     * sys_role
     *
     * @mbggenerated
     */
    int insertSelective(Role record);

    /**
     * sys_role
     *
     * @mbggenerated
     */
    Role selectByPrimaryKey(Long id);

    /**
     * sys_role
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Role record);

    /**
     * sys_role
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Role record);

    List<Role> findAll();

    List<Role> findAllRole(@Param("roleType") Integer roleType);

    List<Role> findRoles(@Param("roleIdsStr") String roleIdsStr);

    List<Role> findRolesByAppUser(Map<String, String> record);
}