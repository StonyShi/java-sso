package com.stony.sso.facade.entity;

import com.stony.sso.commons.annotation.Optimizer;
import com.stony.sso.commons.page.PageBean;

import java.io.Serializable;
import java.util.Date;

@Optimizer
public class Icon extends PageBean implements Serializable {
    /**
     * 
     * sys_icon.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 图标名称
     * sys_icon.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 图标地址
     * sys_icon.url
     *
     * @mbggenerated
     */
    private String url;

    /**
     * 1 有效
     * sys_icon.available
     *
     * @mbggenerated
     */
    private Integer available;

    /**
     * 插入时间
     * sys_icon.insert_date
     *
     * @mbggenerated
     */
    private Date insertDate;

    /**
     * 更新时间
     * sys_icon.update_date
     *
     * @mbggenerated
     */
    private Date updateDate;

    /**
     * sys_icon
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * sys_icon.id
     *
     * @return sys_icon.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * sys_icon.id
     *
     * @param id sys_icon.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 图标名称
     * sys_icon.name
     *
     * @return sys_icon.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }
    /**
     * 图标名称
     * sys_icon.name
     *
     * @param name sys_icon.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 图标地址
     * sys_icon.url
     *
     * @return sys_icon.url
     *
     * @mbggenerated
     */
    public String getUrl() {
        return url;
    }

    /**
     * 图标地址
     * sys_icon.url
     *
     * @param url sys_icon.url
     *
     * @mbggenerated
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 1 有效
     * sys_icon.available
     *
     * @return sys_icon.available
     *
     * @mbggenerated
     */
    public Integer getAvailable() {
        return available;
    }

    /**
     * 1 有效
     * sys_icon.available
     *
     * @param available sys_icon.available
     *
     * @mbggenerated
     */
    public void setAvailable(Integer available) {
        this.available = available;
    }

    /**
     * 插入时间
     * sys_icon.insert_date
     *
     * @return sys_icon.insert_date
     *
     * @mbggenerated
     */
    public Date getInsertDate() {
        return insertDate;
    }

    /**
     * 插入时间
     * sys_icon.insert_date
     *
     * @param insertDate sys_icon.insert_date
     *
     * @mbggenerated
     */
    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    /**
     * 更新时间
     * sys_icon.update_date
     *
     * @return sys_icon.update_date
     *
     * @mbggenerated
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 更新时间
     * sys_icon.update_date
     *
     * @param updateDate sys_icon.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * sys_icon
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
        sb.append(", url=").append(url);
        sb.append(", available=").append(available);
        sb.append(", insertDate=").append(insertDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append("]");
        return sb.toString();
    }
}