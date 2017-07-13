package com.stony.sso.service.service;

import com.stony.sso.cache.annotation.Cachezable;
import com.stony.sso.facade.entity.*;
import com.stony.sso.facade.keys.SecurityKeys;
import com.stony.sso.facade.service.*;
import com.stony.sso.service.mapper.OrganizationMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/4/26 </p>
 * <p>Time: 15:21 </p>
 * <p>Version: 1.0 </p>
 */
@Service("organizationService")
public class OrganizationServiceImpl implements OrganizationService {

    @javax.annotation.Resource
    private OrganizationMapper organizationMapper;

    public static final String REMOVE_WATCH_KEYS = "OrganizationById";

    @Override
    @Cachezable(key = "ALL", prefix = SecurityKeys.KEY_FIND_ORGANIZATION, type = Cachezable.CachezType.REMOVE, watch = REMOVE_WATCH_KEYS)
    public Organization createOrganization(Organization organization) {
        organizationMapper.insert(organization);
        return organization;
    }

    @Override
    @Cachezable(key = "#organization.id,ALL", prefix = SecurityKeys.KEY_FIND_ORGANIZATION, type = Cachezable.CachezType.REMOVE, watch = REMOVE_WATCH_KEYS)
    public Organization updateOrganization(Organization organization) {
        return organizationMapper.updateByPrimaryKeySelective(organization) > 0 ? organization : null;
    }

    @Override
    @Cachezable(key = "#organizationId,ALL", prefix = SecurityKeys.KEY_FIND_ORGANIZATION, type = Cachezable.CachezType.REMOVE, watch = REMOVE_WATCH_KEYS)
    public int deleteOrganization(Long organizationId) {
        return organizationMapper.deleteByPrimaryKey(organizationId);
    }

    @Override
    @Cachezable(key = "#organizationId", prefix = SecurityKeys.KEY_FIND_ORGANIZATION, type = Cachezable.CachezType.SET, watch = REMOVE_WATCH_KEYS)
    public Organization findOne(Long organizationId) {
        return organizationMapper.selectByPrimaryKey(organizationId);
    }

    @Override
    @Cachezable(key = "ALL", prefix = SecurityKeys.KEY_FIND_ORGANIZATION, type = Cachezable.CachezType.SET)
    public List<Organization> findAll() {
        return organizationMapper.findAll();
    }

    @Override
    public List<Organization> findAllWithExclude(Organization excludeOrganization) {
        return organizationMapper.findAllWithExclude(excludeOrganization);
    }

    @Override
    public void move(Organization source, Organization target) {
        Organization moveSource = new Organization();
        moveSource.setParentId(target.getParentId());
        moveSource.setId(source.getId());
        //update source to target info
        organizationMapper.updateByPrimaryKeySelective(moveSource);

//        Map map = new HashMap();
//        map.put("targetSelfAsParentIds",target.makeSelfAsParentIds());
//        map.put("sourceSelfAsParentIds",source.makeSelfAsParentIds());
//        map.put("parentIds",source.makeSelfAsParentIds());
//        organizationMapper.moveSelfAsParentIds(map);
    }
}
