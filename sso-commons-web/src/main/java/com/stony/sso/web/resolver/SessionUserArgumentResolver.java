package com.stony.sso.web.resolver;

import com.stony.sso.cache.ticket.SessionUser;
import com.stony.sso.cache.ticket.TicketCacheManager;
import com.stony.sso.web.constants.HeaderConstant;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.MethodParameter;
import org.springframework.util.Assert;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/5/10 </p>
 * <p>Time: 11:29 </p>
 * <p>Version: 1.0 </p>
 * 会话用户自动绑定
 * <pre class="code">
 *
 * </pre>
 */
public class SessionUserArgumentResolver implements HandlerMethodArgumentResolver,InitializingBean {


    TicketCacheManager ticketCacheManager;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return SessionUser.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String ticket = webRequest.getHeader(HeaderConstant.HEADER_TICKET);
        SessionUser ticketValue = getTicketCacheManager().getTicketCache().getTicketValue(ticket);
        return ticketValue;
    }

    public TicketCacheManager getTicketCacheManager() {
        return ticketCacheManager;
    }

    public void setTicketCacheManager(TicketCacheManager ticketCacheManager) {
        this.ticketCacheManager = ticketCacheManager;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.ticketCacheManager, "property ticketCacheManager is required.");
    }
}
