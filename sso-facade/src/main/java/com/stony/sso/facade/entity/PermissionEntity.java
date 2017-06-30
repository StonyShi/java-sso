package com.stony.sso.facade.entity;


import com.stony.sso.commons.annotation.Optimizer;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/7/8 </p>
 * <p>Time: 16:41 </p>
 * <p>Version: 1.0 </p>
 */
@Optimizer
public class PermissionEntity implements Serializable{
    private long userId;
    private String username;
    private List<Resource> resources;
    private List<Role> roles;
    private String responseTime;
    private String code;
    private String accessToken;
    private String refreshToken;
    private long expiresIn;
    private String clientId;

    public PermissionEntity(String username, List<Resource> resources, List<Role> roles, String responseTime) {
        this.username = username;
        this.resources = resources;
        this.roles = roles;
        this.responseTime = responseTime;
    }

    public PermissionEntity() {
    }

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

    @Override
    public String toString() {
        return "PermissionEntity{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", resources=" + resources +
                ", roles=" + roles +
                ", responseTime='" + responseTime + '\'' +
                ", code='" + code + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", expiresIn=" + expiresIn +
                ", clientId='" + clientId + '\'' +
                '}';
    }
}
