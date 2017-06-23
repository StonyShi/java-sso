package com.stony.sso.service.mapper;

import com.stony.sso.facade.entity.Organization;

import java.util.List;

public interface OrganizationMapper {
    /**
     * sys_organization
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * sys_organization
     *
     * @mbggenerated
     */
    int insert(Organization record);

    /**
     * sys_organization
     *
     * @mbggenerated
     */
    int insertSelective(Organization record);

    /**
     * sys_organization
     *
     * @mbggenerated
     */
    Organization selectByPrimaryKey(Long id);

    /**
     * sys_organization
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Organization record);

    /**
     * sys_organization
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Organization record);

    List<Organization> findAll();

    List<Organization> findAllWithExclude(Organization excludeOraganization);
}