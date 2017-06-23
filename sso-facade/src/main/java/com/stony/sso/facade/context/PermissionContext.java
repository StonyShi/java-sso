package com.stony.sso.facade.context;


import com.stony.sso.facade.entity.Resource;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * 保持用户角色，权限，资源，菜单
 * <p>Created with car-commons-security.</p>
 * <p>User: Stony</p>
 * <p>Date: 2016/4/25</p>
 * <p>Time: 18:30</p>
 */
public class PermissionContext implements Serializable {


    public static final String GET_SESSION = "/get/session";
    public static final String CREATE_SESSION = "/create/session";
    public static final String UPDATE_SESSION = "/update/session";
    public static final String DELETE_SESSION = "/delete/session";

    private Set<String> roles;
    private Set<String> permissions;
    private List<Resource> menus;
    private List<Resource> resources;

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public List<Resource> getMenus() {
        return menus;
    }

    public void setMenus(List<Resource> menus) {
        this.menus = menus;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    @Override
    public String toString() {
        return "PermissionContext{" +
                ", resources=" + resources +
                ", roles=" + roles +
                ", permissions=" + permissions +
                ", menus=" + menus +
                '}';
    }
}
