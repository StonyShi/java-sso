package com.stony.sso.facade.service;


import com.stony.sso.facade.entity.Organization;

import java.util.List;

/**
 * <p>Created with car-facade-security.</p>
 * <p>User: Stony</p>
 * <p>Date: 2016/4/25</p>
 * <p>Time: 18:30</p>
 * <p>Version: 1.0</p>
 */
public interface OrganizationService {


    public Organization createOrganization(Organization organization);
    public Organization updateOrganization(Organization organization);
    public int deleteOrganization(Long organizationId);

    Organization findOne(Long organizationId);
    List<Organization> findAll();

    Object findAllWithExclude(Organization excludeOraganization);

    void move(Organization source, Organization target);
}
