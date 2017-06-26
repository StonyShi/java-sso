package com.stony.sso.cache.ticket.support;

import com.stony.sso.cache.constants.BaseCacheConstant;
import com.stony.sso.cache.redis.JedisSentinelTemplate;
import com.stony.sso.cache.redis.JedisTemplate;
import com.stony.sso.cache.ticket.SessionUser;
import com.stony.sso.cache.ticket.TicketCache;
import com.stony.sso.commons.StringUtils;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/5/6 </p>
 * <p>Time: 14:01 </p>
 * <p>Version: 1.0 </p>
 */
public class JedisTicketCache implements TicketCache{

    JedisTemplate jedisTemplate;
    private int timeout = TicketCache.DEFAULT_TIMEOUT;

    @Override
    public void expireTicket(String userId) {
        String oldTicket = getTicket(userId);
        //获取用户当前的Ticket，并将 ticket = userId  删除
        if(StringUtils.isNotEmpty(oldTicket)) {
            jedisTemplate.del(BaseCacheConstant.getUserTicketKey(oldTicket));
        }
        //将 userId 以 userId = ticket 删除
        jedisTemplate.del(BaseCacheConstant.getUserTicketKey(userId));
    }
    @Override
    public String setTicket(SessionUser user, String ticket) {
        String userId = String.valueOf(user.getUserId());
        //ticket 以 ticket = user 的形式存储在缓存中
        jedisTemplate.set(BaseCacheConstant.getUserTicketKey(ticket), user, getTimeout());//默认90天过期
        //获取用户当前ticket
        String oldTicket = getTicket(userId);
        //当前的Ticket不为空，并将 ticket = user  删除
        if(StringUtils.isNotEmpty(oldTicket) && !oldTicket.equals(ticket)) {
            jedisTemplate.del(BaseCacheConstant.getUserTicketKey(oldTicket));
        }
        //userId 以 userId = ticket 的形式存入缓存中
        jedisTemplate.setStr(BaseCacheConstant.getUserTicketKey(userId), ticket, getTimeout());//默认90天过期
        return ticket;
    }

    @Override
    public String getTicket(String userId) {
        return jedisTemplate.getStr(BaseCacheConstant.getUserTicketKey(userId));
    }

    @Override
    public SessionUser getTicketValue(String ticket) {
        return (SessionUser) jedisTemplate.get(BaseCacheConstant.getUserTicketKey(ticket));
    }

    @Override
    public boolean existsTicket(String ticket) {
        return getTicketValue(ticket) != null;
    }

    public JedisTicketCache(JedisTemplate jedisTemplate) {
        this.jedisTemplate = jedisTemplate;
    }

    /**
     * 禁用无参实例化
     * @see #jedisTemplate
     */
    private JedisTicketCache() {}

    public int getTimeout() {
        return timeout;
    }

    @Override
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }


}
