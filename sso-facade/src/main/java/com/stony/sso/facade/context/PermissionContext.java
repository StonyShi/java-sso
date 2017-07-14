package com.stony.sso.facade.context;


import com.stony.sso.facade.entity.Resource;
import com.stony.sso.facade.entity.Role;
import com.stony.sso.facade.entity.User;

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

    private long userId;
    private String username;
    private List<Resource> resources;
    private List<Resource> menus;
    private List<Role> roles;
    private String responseTime;
    private String code;
    private String accessToken;
    private String refreshToken;
    private long expiresIn;
    private String clientId;
    private User user;
    private Set<String> permissions;

    public PermissionContext(String username, List<Resource> resources, List<Role> roles, String responseTime) {
        this.username = username;
        this.resources = resources;
        this.roles = roles;
        this.responseTime = responseTime;
    }
    public PermissionContext(String username){
        this.username = username;
    }
    public PermissionContext() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setMenus(List<Resource> menus) {
        this.menus = menus;
    }

    public List<Resource> getMenus() {
        return menus;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    @Override
    public String toString() {
        return "PermissionContext{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", resources=" + resources +
                ", menus=" + menus +
                ", permissions=" + permissions +
                ", roles=" + roles +
                ", responseTime='" + responseTime + '\'' +
                ", code='" + code + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", expiresIn=" + expiresIn +
                ", clientId='" + clientId + '\'' +
                ", user=" + user +
                '}';
    }


}
