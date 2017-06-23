package com.stony.sso.cache.ticket.support;

import com.stony.sso.cache.redis.JedisSentinelTemplate;
import com.stony.sso.cache.ticket.SessionUser;
import com.stony.sso.cache.ticket.TicketCache;
import com.stony.sso.commons.DESUtils;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/6/12 </p>
 * <p>Time: 10:52 </p>
 * <p>Version: 1.0 </p>
 */
public class AdminTicketCache implements TicketCache {

    JedisSentinelTemplate jedisSentinelTemplate;
    private int timeout = TicketCache.DEFAULT_TIMEOUT;


    /**
     *
     * @param user
     * @param ticket is username
     * @return
     */
    @Override
    public String setTicket(SessionUser user, String ticket) {
        return null;
    }

    @Override
    public String getTicket(String userId) {
        return null;
    }

    @Override
    public boolean existsTicket(String ticket) {
        return false;
    }

    @Override
    public SessionUser getTicketValue(String ticket) {
        return null;
    }



    @Override
    public void expireTicket(String userId) {

    }

    @Override
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
    public void setJedisSentinelTemplate(JedisSentinelTemplate jedisSentinelTemplate) {
        this.jedisSentinelTemplate = jedisSentinelTemplate;
    }

    protected String getUsernameTicket(String username){
        return DESUtils.generateTicket(username);
    }

}
