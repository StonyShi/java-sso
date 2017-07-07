package com.stony.sso.facade.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.stony.sso.commons.annotation.Optimizer;
import com.stony.sso.commons.serializer.DateTime2StringSerializer;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/5/12 </p>
 * <p>Time: 20:32 </p>
 * <p>Version: 1.0 </p>
 */
public class App implements Serializable {
    public App() {
    }

    public App(Long id, Integer available, Date updateDate) {
        this.id = id;
        this.available = available;
        this.updateDate = updateDate;
    }

    /**
     * 
     * sys_app.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * app名称
     * sys_app.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * appKey
     * sys_app.app_key
     *
     * @mbggenerated
     */
    private String appKey;

    /**
     * app 安全码
     * sys_app.app_secret
     *
     * @mbggenerated
     */
    private String appSecret;

    /**
     * 1 有效
     * sys_app.available
     *
     * @mbggenerated
     */
    private Integer available;

    /**
     * 地址域名
     * sys_app.address
     */
    private String address;

    /**
     * 图标
     * sys_app.icon
     */
    private String icon;
    /**
     * 描述
     * sys_app.desc
     */
    private String desc;

    /**
     * 插入时间
     * sys_app.insert_date
     *
     * @mbggenerated
     */
    private Date insertDate;

    /**
     * 更新时间
     * sys_app.update_date
     *
     * @mbggenerated
     */
    private Date updateDate;

    /**
     * sys_app
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * sys_app.id
     *
     * @return sys_app.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * sys_app.id
     *
     * @param id sys_app.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * app名称
     * sys_app.name
     *
     * @return sys_app.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * app名称
     * sys_app.name
     *
     * @param name sys_app.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * appKey
     * sys_app.app_key
     *
     * @return sys_app.app_key
     *
     * @mbggenerated
     */
    public String getAppKey() {
        return appKey;
    }

    /**
     * appKey
     * sys_app.app_key
     *
     * @param appKey sys_app.app_key
     *
     * @mbggenerated
     */
    public void setAppKey(String appKey) {
        this.appKey = appKey == null ? null : appKey.trim();
    }

    /**
     * app 安全码
     * sys_app.app_secret
     *
     * @return sys_app.app_secret
     *
     * @mbggenerated
     */
    public String getAppSecret() {
        return appSecret;
    }

    /**
     * app 安全码
     * sys_app.app_secret
     *
     * @param appSecret sys_app.app_secret
     *
     * @mbggenerated
     */
    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret == null ? null : appSecret.trim();
    }

    /**
     * 1 有效
     * sys_app.available
     *
     * @return sys_app.available
     *
     * @mbggenerated
     */
    public Integer getAvailable() {
        return available;
    }

    /**
     * 1 有效
     * sys_app.available
     *
     * @param available sys_app.available
     *
     * @mbggenerated
     */
    public void setAvailable(Integer available) {
        this.available = available;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 插入时间
     * sys_app.insert_date
     *
     * @return sys_app.insert_date
     *
     * @mbggenerated
     */
    @JsonSerialize(using = DateTime2StringSerializer.class)
    public Date getInsertDate() {
        return insertDate;
    }

    /**
     * 插入时间
     * sys_app.insert_date
     *
     * @param insertDate sys_app.insert_date
     *
     * @mbggenerated
     */
    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    /**
     * 更新时间
     * sys_app.update_date
     *
     * @return sys_app.update_date
     *
     * @mbggenerated
     */
    @JsonSerialize(using = DateTime2StringSerializer.class)
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 更新时间
     * sys_app.update_date
     *
     * @param updateDate sys_app.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * sys_app
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
        sb.append(", appKey=").append(appKey);
        sb.append(", appSecret=").append(appSecret);
        sb.append(", available=").append(available);
        sb.append(", address=").append(address);
        sb.append(", icon=").append(icon);
        sb.append(", insertDate=").append(insertDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append("]");
        return sb.toString();
    }
}