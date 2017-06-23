package com.stony.sso.cache.ticket;

import org.springframework.beans.factory.InitializingBean;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/5/6 </p>
 * <p>Time: 14:00 </p>
 * <p>Version: 1.0 </p>
 */
public interface TicketCacheManager extends InitializingBean {

    public TicketCache getTicketCache();

    public void setTicketCache(TicketCache ticketCache);
}
