package com.stony.sso.cache.ticket;

import org.springframework.util.Assert;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/5/6 </p>
 * <p>Time: 14:03 </p>
 * <p>Version: 1.0 </p>
 * <pre class="code">
 *   &lt;bean id="ticketCache" class="JedisTicketCache"&gt;
        &lt;constructor-arg index="0" ref="sessionJedisTemplate" /&gt;
     &lt;/bean&gt;
     &lt;bean id="ticketCacheManager" class="DefaultTicketCacheManager"&gt;
        &lt;property name="ticketCache" ref="ticketCache"/&gt;
     &lt;/bean&gt;
 * </pre>
 */
public class DefaultTicketCacheManager implements TicketCacheManager{

    private int timeout = TicketCache.DEFAULT_TIMEOUT;
    TicketCache ticketCache;

    @Override
    public TicketCache getTicketCache() {
        return ticketCache;
    }

    @Override
    public void setTicketCache(TicketCache ticketCache) {
        this.ticketCache = ticketCache;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
        getTicketCache().setTimeout(timeout);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.ticketCache, "property ticketCache is required");
    }
}
