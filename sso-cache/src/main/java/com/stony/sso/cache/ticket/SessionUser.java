package com.stony.sso.cache.ticket;

import java.io.Serializable;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/5/10 </p>
 * <p>Time: 10:59 </p>
 * <p>Version: 1.0 </p>
 */
public class SessionUser implements Serializable{

    private Long userId;
    private String userName;
    /** 此处为手机号码明文 **/
    private String account;
    /** yyyy-MM-dd HH:mm:ss **/
    private String insertDate;
    private String ticket;
    private String appKey;
    private Long gasStationId;//归属站点


    /**
     *
     * @param userId
     * @param userName
     * @param insertDate
     * @param ticket
     * @param account 手机号明文
     */

    public SessionUser(Long userId, String userName, String insertDate, String ticket, String account,Long gasStationId) {
        this.userId = userId;
        this.userName = userName;
        this.insertDate = insertDate;
        this.ticket = ticket;
        this.account = account;
        this.gasStationId =gasStationId;
    }

    /**
     *
     * @param userId 用户ID
     * @param userName 用户名名称
     * @param account 手机号明文
     * @param insertDate 插入时间
     * @param ticket ticket|token|session_id
     * @param appKey 登陆随机分配
     */
    public SessionUser(Long userId, String userName, String account, String insertDate, String ticket,Long gasStationId, String appKey) {
        this(userId, userName, insertDate, ticket, account,gasStationId);
        this.appKey = appKey;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(String insertDate) {
        this.insertDate = insertDate;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Long getGasStationId() {
        return gasStationId;
    }

    public void setGasStationId(Long gasStationId) {
        this.gasStationId = gasStationId;
    }

    @Override
    public String toString() {
        return "SessionUser{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", account='" + account + '\'' +
                ", insertDate='" + insertDate + '\'' +
                ", ticket='" + ticket + '\'' +
                ", appKey='" + appKey + '\'' +
                ", gasStationId='"+ gasStationId+ '\'' +
                '}';
    }
}
