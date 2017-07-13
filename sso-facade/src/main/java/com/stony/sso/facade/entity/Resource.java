package com.stony.sso.facade.entity;

import com.stony.sso.facade.enums.ResourceType;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.stony.sso.commons.annotation.Optimizer;
import com.stony.sso.commons.serializer.DateTime2StringSerializer;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Optimizer
public class Resource implements Serializable {

    public Resource() {
    }

    public Resource(Long id, Integer available, Date updateDate) {
        this.id = id;
        this.available = available;
        this.updateDate = updateDate;
    }

    /**
     * 
     * sys_resource.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 名称
     * sys_resource.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * MENU,BUTTON
     * sys_resource.type
     *
     * @mbggenerated
     */
    private String type;

    /**
     * 资源路径
     * sys_resource.url
     *
     * @mbggenerated
     */
    private String url;

    /**
     * 父ID
     * sys_resource.parent_id
     *
     * @mbggenerated
     */
    private Long parentId;

    /**
     * 父节点名称
     */
    private String parentName;


    /**
     * 权限
     * sys_resource.permission
     *
     * @mbggenerated
     */
    private String permission;

    /**
     * 1 有效
     * sys_resource.available
     *
     * @mbggenerated
     */
    private Integer available;

    /**
     * 样式
     * sys_resource.icon
     *
     * @mbggenerated
     */
    private String icon;

    /**
     * 插入时间
     * sys_resource.insert_date
     *
     * @mbggenerated
     */
    private Date insertDate;

    /**
     * 更新时间
     * sys_resource.update_date
     *
     * @mbggenerated
     */
    private Date updateDate;
    /**
     * 角色是否拥有资源
     */
    private boolean hasRole = false;

    /**
     * 归属后台
     * -1系统后台0公共1大后台资源2站点后台资源
     */
    private Integer resourceType;

    List<Resource> children;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List<Resource> getChildren() {
        return children;
    }

    public void setChildren(List<Resource> children) {
        this.children = children;
    }

    public boolean isHasRole() {
        return hasRole;
    }

    public void setHasRole(boolean hasRole) {
        this.hasRole = hasRole;
    }

    /**
     * sys_resource
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * sys_resource.id
     *
     * @return sys_resource.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * sys_resource.id
     *
     * @param id sys_resource.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 名称
     * sys_resource.name
     *
     * @return sys_resource.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * 名称
     * sys_resource.name
     *
     * @param name sys_resource.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * MENU,BUTTON
     * sys_resource.type
     *
     * @return sys_resource.type
     *
     * @mbggenerated
     */
    public String getType() {
        return type;
    }

    /**
     * MENU,BUTTON
     * sys_resource.type
     *
     * @param type sys_resource.type
     *
     * @mbggenerated
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 资源路径
     * sys_resource.url
     *
     * @return sys_resource.url
     *
     * @mbggenerated
     */
    public String getUrl() {
        return url;
    }

    /**
     * 资源路径
     * sys_resource.url
     *
     * @param url sys_resource.url
     *
     * @mbggenerated
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 父ID
     * sys_resource.parent_id
     *
     * @return sys_resource.parent_id
     *
     * @mbggenerated
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 父ID
     * sys_resource.parent_id
     *
     * @param parentId sys_resource.parent_id
     *
     * @mbggenerated
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }


    /**
     * 权限
     * sys_resource.permission
     *
     * @return sys_resource.permission
     *
     * @mbggenerated
     */
    public String getPermission() {
        return permission;
    }

    /**
     * 权限
     * sys_resource.permission
     *
     * @param permission sys_resource.permission
     *
     * @mbggenerated
     */
    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    /**
     * 1 有效
     * sys_resource.available
     *
     * @return sys_resource.available
     *
     * @mbggenerated
     */
    public Integer getAvailable() {
        return available;
    }

    /**
     * 1 有效
     * sys_resource.available
     *
     * @param available sys_resource.available
     *
     * @mbggenerated
     */
    public void setAvailable(Integer available) {
        this.available = available;
    }

    /**
     * 样式
     * sys_resource.icon
     *
     * @return sys_resource.icon
     *
     * @mbggenerated
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 样式
     * sys_resource.icon
     *
     * @param icon sys_resource.icon
     *
     * @mbggenerated
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * 插入时间
     * sys_resource.insert_date
     *
     * @return sys_resource.insert_date
     *
     * @mbggenerated
     */
    @JsonSerialize(using = DateTime2StringSerializer.class)
    public Date getInsertDate() {
        return insertDate;
    }

    /**
     * 插入时间
     * sys_resource.insert_date
     *
     * @param insertDate sys_resource.insert_date
     *
     * @mbggenerated
     */
    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    /**
     * 更新时间
     * sys_resource.update_date
     *
     * @return sys_resource.update_date
     *
     * @mbggenerated
     */
    @JsonSerialize(using = DateTime2StringSerializer.class)
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 更新时间
     * sys_resource.update_date
     *
     * @param updateDate sys_resource.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }


    public boolean isAvailabled() {
        return available == 1;
    }

    public boolean isButton() {
        return ResourceType.valueOf(type) == ResourceType.BUTTON;
    }

    public boolean isMenu() {
        return ResourceType.valueOf(type) == ResourceType.MENU;
    }

    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * sys_resource
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
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", url=").append(url);
        sb.append(", parentId=").append(parentId);
        sb.append(", parentName=").append(parentName);
        sb.append(", permission=").append(permission);
        sb.append(", available=").append(available);
        sb.append(", hasRole=").append(hasRole);
        sb.append(", icon=").append(icon);
        sb.append(", insertDate=").append(insertDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", children=").append(children);
        sb.append(", resourceType=").append(resourceType);
        sb.append("]");
        return sb.toString();
    }

}