package com.stony.sso.facade.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.stony.sso.commons.annotation.Optimizer;
import com.stony.sso.commons.serializer.DateTime2StringSerializer;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户app角色映射表
 * 授权
 */
@Optimizer
public class UserAppRole implements Serializable {
    /**
     *
     * sys_user_app_role.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 用户ID
     * sys_user_app_role.user_id
     *
     * @mbggenerated
     */
    private Long userId;
    private User user;

    /**
     * 应用ID
     * sys_user_app_role.app_id
     *
     * @mbggenerated
     */
    private Long appId;
    private App app;

    /**
     * 角色ID
     * sys_user_app_role.role_id
     *
     * @mbggenerated
     */
    private Long roleId;
    private Role role;

    /**
     * 插入时间
     * sys_user_app_role.insert_date
     *
     * @mbggenerated
     */
    private Date insertDate;

    /**
     * 更新时间
     * sys_user_app_role.update_date
     *
     * @mbggenerated
     */
    private Date updateDate;

    /**
     * sys_user_app_role
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    /**
     *
     * sys_user_app_role.id
     *
     * @return sys_user_app_role.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * sys_user_app_role.id
     *
     * @param id sys_user_app_role.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 用户ID
     * sys_user_app_role.user_id
     *
     * @return sys_user_app_role.user_id
     *
     * @mbggenerated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 用户ID
     * sys_user_app_role.user_id
     *
     * @param userId sys_user_app_role.user_id
     *
     * @mbggenerated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 应用ID
     * sys_user_app_role.app_id
     *
     * @return sys_user_app_role.app_id
     *
     * @mbggenerated
     */
    public Long getAppId() {
        return appId;
    }

    /**
     * 应用ID
     * sys_user_app_role.app_id
     *
     * @param appId sys_user_app_role.app_id
     *
     * @mbggenerated
     */
    public void setAppId(Long appId) {
        this.appId = appId;
    }

    /**
     * 角色ID
     * sys_user_app_role.role_id
     *
     * @return sys_user_app_role.role_id
     *
     * @mbggenerated
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 角色ID
     * sys_user_app_role.role_id
     *
     * @param roleId sys_user_app_role.role_id
     *
     * @mbggenerated
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 插入时间
     * sys_user_app_role.insert_date
     *
     * @return sys_user_app_role.insert_date
     *
     * @mbggenerated
     */
    @JsonSerialize(using = DateTime2StringSerializer.class)
    public Date getInsertDate() {
        return insertDate;
    }

    /**
     * 插入时间
     * sys_user_app_role.insert_date
     *
     * @param insertDate sys_user_app_role.insert_date
     *
     * @mbggenerated
     */
    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    /**
     * 更新时间
     * sys_user_app_role.update_date
     *
     * @return sys_user_app_role.update_date
     *
     * @mbggenerated
     */
    @JsonSerialize(using = DateTime2StringSerializer.class)
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 更新时间
     * sys_user_app_role.update_date
     *
     * @param updateDate sys_user_app_role.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * sys_user_app_role
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", user=").append(user);
        sb.append(", appId=").append(appId);
        sb.append(", app=").append(app);
        sb.append(", roleId=").append(roleId);
        sb.append(", role=").append(role);
        sb.append(", insertDate=").append(insertDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append("]");
        return sb.toString();
    }
}