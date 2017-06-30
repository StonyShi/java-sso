package com.stony.sso.service.mapper;

import com.stony.sso.facade.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /**
     * sys_user
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * sys_user
     *
     * @mbggenerated
     */
    int insert(User record);

    /**
     * sys_user
     *
     * @mbggenerated
     */
    int insertSelective(User record);

    /**
     * sys_user
     *
     * @mbggenerated
     */
    User selectByPrimaryKey(Long id);

    /**
     * sys_user
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * sys_user
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(User record);

    List<User> findAll();


    User findByUsername(String username);

    /**
     * 拥有角色的用户列表
     * @param roleIds
     * @return
     */
    List<User> findUserByRole(@Param("roleIds") String roleIds);
}