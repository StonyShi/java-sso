package com.stony.sso.facade.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.stony.sso.commons.annotation.Optimizer;
import com.stony.sso.commons.serializer.DateTime2StringSerializer;

import java.io.Serializable;
import java.util.Date;

@Optimizer
public class Role implements Serializable {

    public Role() {
    }

    public Role(Long id, Integer available, Date updateDate) {
        this.id = id;
        this.available = available;
        this.updateDate = updateDate;
    }

    /**
     * 
     * sys_role.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 角色
     * sys_role.role
     *
     * @mbggenerated
     */
    private String role;

    /**
     * 角色描述
     * sys_role.description
     *
     * @mbggenerated
     */
    private String description;


    /**
     * 1 有效
     * sys_role.available
     *
     * @mbggenerated
     */
    private Integer available;

    /**
     * 插入时间
     * sys_role.insert_date
     *
     * @mbggenerated
     */
    private Date insertDate;

    /**
     * 更新时间
     * sys_role.update_date
     *
     * @mbggenerated
     */
    private Date updateDate;

    /**
     * 角色归属
     * -1系统后台0公共1大后台资源2站点后台资源
     */
    private Integer roleType;

    /**
     * sys_role
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * sys_role.id
     *
     * @return sys_role.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * sys_role.id
     *
     * @param id sys_role.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * 角色
     * sys_role.role
     * @return sys_role.role
     * @mbggenerated
     */
    public String getRole() {
        return role;
    }

    /**
     * 
     * sys_role.role
     *
     * @param role sys_role.role
     *
     * @mbggenerated
     */
    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    /**
     * 角色描述
     * sys_role.description
     *
     * @return sys_role.description
     *
     * @mbggenerated
     */
    public String getDescription() {
        return description;
    }

    /**
     * 角色描述
     * sys_role.description
     *
     * @param description sys_role.description
     *
     * @mbggenerated
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }


    /**
     * 1 有效
     * sys_role.available
     *
     * @return sys_role.available
     *
     * @mbggenerated
     */
    public Integer getAvailable() {
        return available;
    }

    /**
     * 1 有效
     * sys_role.available
     *
     * @param available sys_role.available
     *
     * @mbggenerated
     */
    public void setAvailable(Integer available) {
        this.available = available;
    }

    /**
     * 插入时间
     * sys_role.insert_date
     *
     * @return sys_role.insert_date
     *
     * @mbggenerated
     */
    @JsonSerialize(using = DateTime2StringSerializer.class)
    public Date getInsertDate() {
        return insertDate;
    }

    /**
     * 插入时间
     * sys_role.insert_date
     *
     * @param insertDate sys_role.insert_date
     *
     * @mbggenerated
     */
    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    /**
     * 更新时间
     * sys_role.update_date
     *
     * @return sys_role.update_date
     *
     * @mbggenerated
     */
    @JsonSerialize(using = DateTime2StringSerializer.class)
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 更新时间
     * sys_role.update_date
     *
     * @param updateDate sys_role.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    /**
     * sys_role
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
        sb.append(", role=").append(role);
        sb.append(", description=").append(description);
        sb.append(", available=").append(available);
        sb.append(", insertDate=").append(insertDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", roleType=").append(roleType);
        sb.append("]");
        return sb.toString();
    }
}