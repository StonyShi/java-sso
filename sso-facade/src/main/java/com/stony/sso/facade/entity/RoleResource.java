package com.stony.sso.facade.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.stony.sso.commons.annotation.Optimizer;
import com.stony.sso.commons.serializer.DateTime2StringSerializer;

import java.io.Serializable;
import java.util.Date;

@Optimizer
public class RoleResource implements Serializable {
    /**
     * 
     * sys_role_resource.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 角色ID
     * sys_role_resource.role_id
     *
     * @mbggenerated
     */
    private Long roleId;

    /**
     * 资源ID
     * sys_role_resource.resource_id
     *
     * @mbggenerated
     */
    private Long resourceId;

    /**
     * 是否有效 1 有效
     * sys_role_resource.available
     *
     * @mbggenerated
     */
    private Integer available;

    /**
     * 插入时间
     * sys_role_resource.insert_date
     *
     * @mbggenerated
     */
    private Date insertDate;

    /**
     * 更新时间
     * sys_role_resource.update_date
     *
     * @mbggenerated
     */
    private Date updateDate;

    public RoleResource(Long roleId, Long resourceId, Integer available, Date insertDate) {
        this.roleId = roleId;
        this.resourceId = resourceId;
        this.available = available;
        this.insertDate = insertDate;
    }

    public RoleResource() {
    }

    /**
     * sys_role_resource
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * sys_role_resource.id
     *
     * @return sys_role_resource.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * sys_role_resource.id
     *
     * @param id sys_role_resource.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 角色ID
     * sys_role_resource.role_id
     *
     * @return sys_role_resource.role_id
     *
     * @mbggenerated
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 角色ID
     * sys_role_resource.role_id
     *
     * @param roleId sys_role_resource.role_id
     *
     * @mbggenerated
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 资源ID
     * sys_role_resource.resource_id
     *
     * @return sys_role_resource.resource_id
     *
     * @mbggenerated
     */
    public Long getResourceId() {
        return resourceId;
    }

    /**
     * 资源ID
     * sys_role_resource.resource_id
     *
     * @param resourceId sys_role_resource.resource_id
     *
     * @mbggenerated
     */
    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * 是否有效 1 有效
     * sys_role_resource.available
     *
     * @return sys_role_resource.available
     *
     * @mbggenerated
     */
    public Integer getAvailable() {
        return available;
    }

    /**
     * 是否有效 1 有效
     * sys_role_resource.available
     *
     * @param available sys_role_resource.available
     *
     * @mbggenerated
     */
    public void setAvailable(Integer available) {
        this.available = available;
    }

    /**
     * 插入时间
     * sys_role_resource.insert_date
     *
     * @return sys_role_resource.insert_date
     *
     * @mbggenerated
     */
    @JsonSerialize(using = DateTime2StringSerializer.class)
    public Date getInsertDate() {
        return insertDate;
    }

    /**
     * 插入时间
     * sys_role_resource.insert_date
     *
     * @param insertDate sys_role_resource.insert_date
     *
     * @mbggenerated
     */
    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    /**
     * 更新时间
     * sys_role_resource.update_date
     *
     * @return sys_role_resource.update_date
     *
     * @mbggenerated
     */
    @JsonSerialize(using = DateTime2StringSerializer.class)
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 更新时间
     * sys_role_resource.update_date
     *
     * @param updateDate sys_role_resource.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * sys_role_resource
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
        sb.append(", roleId=").append(roleId);
        sb.append(", resourceId=").append(resourceId);
        sb.append(", available=").append(available);
        sb.append(", insertDate=").append(insertDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append("]");
        return sb.toString();
    }
}