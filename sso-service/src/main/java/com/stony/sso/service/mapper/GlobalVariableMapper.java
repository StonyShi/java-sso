package com.stony.sso.service.mapper;

import com.stony.sso.facade.entity.GlobalVariable;

import java.util.List;

public interface GlobalVariableMapper {
    /**
     * global_variable
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * global_variable
     *
     * @mbggenerated
     */
    int insert(GlobalVariable record);

    /**
     * global_variable
     *
     * @mbggenerated
     */
    int insertSelective(GlobalVariable record);

    /**
     * global_variable
     *
     * @mbggenerated
     */
    GlobalVariable selectByPrimaryKey(Long id);

    /**
     * global_variable
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(GlobalVariable record);

    /**
     * global_variable
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(GlobalVariable record);

    List<GlobalVariable> findAll();
}