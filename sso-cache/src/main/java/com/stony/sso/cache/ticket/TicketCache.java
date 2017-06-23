package com.stony.sso.cache.ticket;

import java.io.Serializable;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/5/6 </p>
 * <p>Time: 13:57 </p>
 * <p>Version: 1.0 </p>
 */
public interface TicketCache extends Serializable{

    /** 单位秒 **/
    public static final int DEFAULT_TIMEOUT = (86400 * 90); //90天过期

    /**
     * <p>添加用户Ticket 方法：</p>
     * <p>1.ticket 以 ticket = user (SessionUser)的形式存储在缓存中</p>
     * <p>2.获取用户当前的Ticket，并将 ticket = user  删除</p>
     * <p>3.userId 以 userId = ticket 的形式存入缓存中</p>
     * @param user
     * @param ticket
     * @return
     */
    public String setTicket(SessionUser user, String ticket);

    /**
     * 根据用户ID返回当前Ticket
     * @param userId
     * @return
     */
    public String getTicket(String userId);

    /**
     * 用户Ticket是否存在
     * @param ticket
     * @return
     */
    public boolean existsTicket(String ticket);

    /**
     * 返回会话用户
     * @param ticket
     * @return SessionUser
     */
    public SessionUser getTicketValue(String ticket);

    public void setTimeout(int timeout);


    public void expireTicket(String userId);

}
