package com.stony.sso.service.mapper;

import com.stony.sso.facade.entity.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ResourceMapper {
    /**
     * sys_resource
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * sys_resource
     *
     * @mbggenerated
     */
    int insert(Resource record);

    /**
     * sys_resource
     *
     * @mbggenerated
     */
    int insertSelective(Resource record);

    /**
     * sys_resource
     *
     * @mbggenerated
     */
    Resource selectByPrimaryKey(Long id);

    /**
     * sys_resource
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Resource record);

    /**
     * sys_resource
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Resource record);

    List<Resource> findAll();

    List<Resource> findAllUser(@Param("userType") Integer userType);

    List<Resource> findTypeAll(@Param("type") String type);

    /**
     * 查询资源
     * @param ids 资源IDS
     * @return
     */
    List<Resource> findResources(@Param("ids") String ids);

    List<Resource> findResourcesByAppUser(Map<String, String> record);

    /**
     * 查询角色资源
     * @param roleIds 角色IDS
     * @return
     */
    List<Resource> findResourcesByRoleIds(@Param("roleIds") String roleIds);
}