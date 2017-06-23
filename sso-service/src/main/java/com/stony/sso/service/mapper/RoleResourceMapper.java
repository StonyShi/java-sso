package com.stony.sso.service.mapper;

import com.stony.sso.facade.entity.RoleResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleResourceMapper {
    /**
     * sys_role_resource
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * sys_role_resource
     *
     * @mbggenerated
     */
    int insert(RoleResource record);

    /**
     * sys_role_resource
     *
     * @mbggenerated
     */
    int insertSelective(RoleResource record);

    /**
     * sys_role_resource
     *
     * @mbggenerated
     */
    RoleResource selectByPrimaryKey(Long id);

    /**
     * sys_role_resource
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(RoleResource record);

    /**
     * sys_role_resource
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(RoleResource record);

    List<RoleResource> findAll();

    int deleteRoleResourcesById(@Param("roleId") Long roleId);

    int saveRoleResources(List<RoleResource> roleResources);
}