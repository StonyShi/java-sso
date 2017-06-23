package com.stony.sso.service.mapper;

import com.stony.sso.facade.entity.UserAppRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserAppRoleMapper {
    /**
     * sys_user_app_role
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * sys_user_app_role
     *
     * @mbggenerated
     */
    int insert(UserAppRole record);

    /**
     * sys_user_app_role
     *
     * @mbggenerated
     */
    int insertSelective(UserAppRole record);

    /**
     * sys_user_app_role
     *
     * @mbggenerated
     */
    UserAppRole selectByPrimaryKey(Long id);

    /**
     * sys_user_app_role
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(UserAppRole record);

    /**
     * sys_user_app_role
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(UserAppRole record);

    List<UserAppRole> findAll();

    List<UserAppRole> findAllApp(@Param("userType") Integer userType);

    UserAppRole findByAppUser(UserAppRole record);

    int saveAuthorization(List<UserAppRole> record);

    int delete(UserAppRole authorization);
}