package com.stony.sso.service.mapper;


import com.stony.sso.facade.entity.App;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppMapper {
    /**
     * sys_app
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * sys_app
     *
     * @mbggenerated
     */
    int insert(App record);

    /**
     * sys_app
     *
     * @mbggenerated
     */
    int insertSelective(App record);

    /**
     * sys_app
     *
     * @mbggenerated
     */
    App selectByPrimaryKey(Long id);

    /**
     * sys_app
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(App record);

    /**
     * sys_app
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(App record);

    List<App> findAll();

    List<App> findAllApp(@Param("userType") Integer userType);

    App findAppByAppKey(@Param("appKey") String appKey);

    App findAppByAppSecret(@Param("appSecret") String appSecret);


}