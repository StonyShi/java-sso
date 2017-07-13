package com.stony.sso.facade.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.stony.sso.commons.annotation.Optimizer;
import com.stony.sso.commons.serializer.DateTime2StringSerializer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Optimizer
public class Organization implements Serializable {
    /**
     * 
     * sys_organization.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 组织名称
     * sys_organization.name
     *
     * @mbggenerated
     */
    private String name;
    /**
     * logo icon图标
     */
    private String logo;

    /**
     * 父ID
     * sys_organization.parent_id
     *
     * @mbggenerated
     */
    private Long parentId;

    private String parentName;


    /**
     * 1 有效
     * sys_organization.available
     *
     * @mbggenerated
     */
    private Integer available;

    /**
     * 插入时间
     * sys_organization.insert_date
     *
     * @mbggenerated
     */
    private Date insertDate;

    /**
     * 更新时间
     * sys_organization.update_date
     *
     * @mbggenerated
     */
    private Date updateDate;

    /**
     * 子公司
     */
    private List<Organization> children;

    public List<Organization> getChildren() {
        return children;
    }

    public void setChildren(List<Organization> children) {
        this.children = children;
    }

    /**
     * sys_organization
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * sys_organization.id
     *
     * @return sys_organization.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * sys_organization.id
     *
     * @param id sys_organization.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 组织名称
     * sys_organization.name
     *
     * @return sys_organization.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * 组织名称
     * sys_organization.name
     *
     * @param name sys_organization.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * logo icon图标
     * @return
     */
    public String getLogo() {
        return logo;
    }

    /**
     * logo icon图标
     * @param logo
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * 父ID
     * sys_organization.parent_id
     *
     * @return sys_organization.parent_id
     *
     * @mbggenerated
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 父ID
     * sys_organization.parent_id
     *
     * @param parentId sys_organization.parent_id
     *
     * @mbggenerated
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }


    /**
     * 1 有效
     * sys_organization.available
     *
     * @return sys_organization.available
     *
     * @mbggenerated
     */
    public Integer getAvailable() {
        return available;
    }

    /**
     * 1 有效
     * sys_organization.available
     *
     * @param available sys_organization.available
     *
     * @mbggenerated
     */
    public void setAvailable(Integer available) {
        this.available = available;
    }

    /**
     * 插入时间
     * sys_organization.insert_date
     *
     * @return sys_organization.insert_date
     *
     * @mbggenerated
     */
    @JsonSerialize(using = DateTime2StringSerializer.class)
    public Date getInsertDate() {
        return insertDate;
    }

    /**
     * 插入时间
     * sys_organization.insert_date
     *
     * @param insertDate sys_organization.insert_date
     *
     * @mbggenerated
     */
    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    /**
     * 更新时间
     * sys_organization.update_date
     *
     * @return sys_organization.update_date
     *
     * @mbggenerated
     */
    @JsonSerialize(using = DateTime2StringSerializer.class)
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 更新时间
     * sys_organization.update_date
     *
     * @param updateDate sys_organization.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * sys_organization
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
        sb.append(", logo=").append(logo);
        sb.append(", parentId=").append(parentId);
        sb.append(", parentName=").append(parentName);
        sb.append(", available=").append(available);
        sb.append(", insertDate=").append(insertDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", children=").append(children);
        sb.append("]");
        return sb.toString();
    }
}