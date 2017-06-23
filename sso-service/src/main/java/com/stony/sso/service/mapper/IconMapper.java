package com.stony.sso.service.mapper;

import com.stony.sso.facade.entity.Icon;
import com.github.pagehelper.Page;

import java.util.List;

public interface IconMapper {
    /**
     * sys_icon
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * sys_icon
     *
     * @mbggenerated
     */
    int insert(Icon record);

    /**
     * sys_icon
     *
     * @mbggenerated
     */
    int insertSelective(Icon record);

    /**
     * sys_icon
     *
     * @mbggenerated
     */
    Icon selectByPrimaryKey(Integer id);

    /**
     * sys_icon
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Icon record);

    /**
     * sys_icon
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Icon record);

    Page<Icon> findAll();
}