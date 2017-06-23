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
@Optimizer
public class User implements Serializable {
    public User() {
    }

    public User(Long id, Integer locked, Date updateDate) {
        this.id = id;
        this.locked = locked;
        this.updateDate = updateDate;
    }

    /**
     * 
     * sys_user.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 
     * sys_user.organization_id
     *
     * @mbggenerated
     */
    private Long organizationId;

    /**
     * 用户名称
     * sys_user.username
     *
     * @mbggenerated
     */
    private String username;

    /**
     * 密码
     * sys_user.password
     *
     * @mbggenerated
     */
    private transient String password;
    private String sea;

    /**
     * 盐
     * sys_user.salt
     *
     * @mbggenerated
     */
    private String salt;

    /**
     * 1 锁定
     * sys_user.locked
     *
     * @mbggenerated
     */
    private Integer locked;

    /**
     * 手机号
     * sys_user.phone
     *
     * @mbggenerated
     */
    private String phone;

    /**
     * 插入时间
     * sys_user.insert_date
     *
     * @mbggenerated
     */
    private Date insertDate;

    /**
     * 更新时间
     * sys_user.update_date
     *
     * @mbggenerated
     */
    private Date updateDate;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 头像
     */
    private String avatar;

    /**
     * 归属站点ID
     */
    private Long gasStationId;

    /**
     * 归属站点名称
     */
    private String gasStationName;

    /**
     * 归属后台
     * -1 系统后台0 通用 1 大后台 2 站点后台
     */
    private Integer userType;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * sys_user
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;


    public String getSea() {
        return sea;
    }

    public void setSea(String sea) {
        this.sea = sea;
    }

    /**
     * 用户名 加盐
     * @return
     */
    public String getCredentialsSalt() {
        return username + salt;
    }

    /**
     *
     * sys_user.id
     *
     * @return sys_user.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * sys_user.id
     *
     * @param id sys_user.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * sys_user.organization_id
     *
     * @return sys_user.organization_id
     *
     * @mbggenerated
     */
    public Long getOrganizationId() {
        return organizationId;
    }

    /**
     *
     * sys_user.organization_id
     *
     * @param organizationId sys_user.organization_id
     *
     * @mbggenerated
     */
    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    /**
     * 用户名称
     * sys_user.username
     *
     * @return sys_user.username
     *
     * @mbggenerated
     */
    public String getUsername() {
        return username;
    }

    /**
     * 用户名称
     * sys_user.username
     *
     * @param username sys_user.username
     *
     * @mbggenerated
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 密码
     * sys_user.password
     *
     * @return sys_user.password
     *
     * @mbggenerated
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     * sys_user.password
     *
     * @param password sys_user.password
     *
     * @mbggenerated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 盐
     * sys_user.salt
     *
     * @return sys_user.salt
     *
     * @mbggenerated
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 盐
     * sys_user.salt
     *
     * @param salt sys_user.salt
     *
     * @mbggenerated
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    /**
     * 1 锁定
     * sys_user.locked
     *
     * @return sys_user.locked
     *
     * @mbggenerated
     */
    public Integer getLocked() {
        return locked;
    }

    /**
     * 1 锁定
     * sys_user.locked
     *
     * @param locked sys_user.locked
     *
     * @mbggenerated
     */
    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    /**
     * 手机号
     * sys_user.phone
     *
     * @return sys_user.phone
     *
     * @mbggenerated
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 手机号
     * sys_user.phone
     *
     * @param phone sys_user.phone
     *
     * @mbggenerated
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 插入时间
     * sys_user.insert_date
     *
     * @return sys_user.insert_date
     *
     * @mbggenerated
     */
    @JsonSerialize(using = DateTime2StringSerializer.class)
    public Date getInsertDate() {
        return insertDate;
    }

    /**
     * 插入时间
     * sys_user.insert_date
     *
     * @param insertDate sys_user.insert_date
     *
     * @mbggenerated
     */
    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    /**
     * 更新时间
     * sys_user.update_date
     *
     * @return sys_user.update_date
     *
     * @mbggenerated
     */
    @JsonSerialize(using = DateTime2StringSerializer.class)
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 更新时间
     * sys_user.update_date
     *
     * @param updateDate sys_user.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 是否锁定
     * @return
     */
    public boolean isLock() {
        return this.locked == 1;
    }

    public Long getGasStationId() {
        return gasStationId;
    }

    public void setGasStationId(Long gasStationId) {
        this.gasStationId = gasStationId;
    }

    public String getGasStationName() {
        return gasStationName;
    }

    public void setGasStationName(String gasStationName) {
        this.gasStationName = gasStationName;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     * sys_user
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
        sb.append(", organizationId=").append(organizationId);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", sea=").append(sea);
        sb.append(", salt=").append(salt);
        sb.append(", locked=").append(locked);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
        sb.append(", avatar=").append(avatar);
        sb.append(", insertDate=").append(insertDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", gasStationId=").append(gasStationId);
        sb.append(", gasStationName").append(gasStationName);
        sb.append(", userType").append(userType);
        sb.append("]");
        return sb.toString();
    }


}