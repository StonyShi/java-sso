package com.stony.sso.web.form;

import java.io.Serializable;

/**
 * @author Stony
 * Created Date : 2016/4/19  17:07
 */
public class BaseForm implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 6705421231066960588L;
	
	private Long id;
    private Long userId;
    private String name;
    private String account; //
    private String ticket;
    private String appKey;
    private Long attributionSite;//归属地点ID
    private String openId;//普通用户的标识，对当前公众号唯一


    /** 页码 **/
    private int pageNum = 1;
    /** 页大小 **/
    private int pageSize = 10;

    /**
     * 分页参数
     * @return
     */
    private int echo = 1;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public int getEcho() {
        return echo;
    }

    public void setEcho(int echo) {
        this.echo = echo;
    }

    public Long getAttributionSite() {
        return attributionSite;
    }

    public void setAttributionSite(Long attributionSite) {
        this.attributionSite = attributionSite;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Override
    public String toString() {
        return "BaseForm{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", ticket='" + ticket + '\'' +
                ", appKey='" + appKey + '\'' +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", echo=" + echo +
                ", attributionSite="+ attributionSite +
                '}';
    }
}
