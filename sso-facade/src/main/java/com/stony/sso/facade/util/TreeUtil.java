package com.stony.sso.facade.util;


import com.stony.sso.facade.entity.Organization;
import com.stony.sso.facade.entity.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/6/14 </p>
 * <p>Time: 20:32 </p>
 * <p>Version: 1.0 </p>
 */
public abstract class TreeUtil {

    /**
     * 菜单树
     * @param all
     * @return
     */
    public static List<Resource> treeMenu(List<Resource> all){
        return treeMenu(all, 0L, null);
    }
    /**
     * 菜单树
     * @param all
     * @param pid 父ID默认0
     * @param pname 父名称 默认 null
     * @return
     */
    public static List<Resource> treeMenu(List<Resource> all, Long pid, String pname) {
        List<Resource> result = new ArrayList<>();
        for (Resource resource : all) {
            if (pid.compareTo(resource.getParentId()) == 0) {
                List<Resource> children = treeMenu(all, resource.getId(), resource.getName());
                resource.setChildren(children);
                resource.setParentName(pname);
                result.add(resource);
            }
        }
        return result.isEmpty() ? null : result;
    }

    /**
     * 组织树
     * @param all
     * @return
     */
    public static List<Organization> treeOrganization(List<Organization> all){
        return treeOrganization(all, 0L, null);
    }
    /**
     * 组织树
     * @param all
     * @param pid 父ID默认0
     * @param pname 父名称 默认 null
     * @return
     */
    public static List<Organization> treeOrganization(List<Organization> all, Long pid, String pname) {
        List<Organization> result = new ArrayList<>();
        for (Organization organization : all) {
            if (pid.compareTo(organization.getParentId()) == 0) {
                List<Organization> children = treeOrganization(all, organization.getId(), organization.getName());
                organization.setChildren(children);
                organization.setParentName(pname);
                result.add(organization);
            }
        }
        return result.isEmpty() ? null : result;
    }
}
