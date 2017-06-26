package com.stony.sso.web.resolver;

import javax.servlet.http.HttpServletRequest;

import com.stony.sso.cache.ticket.SessionUser;
import com.stony.sso.commons.CookieUtils;
import com.stony.sso.web.constants.HeaderConstant;
import com.stony.sso.commons.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.NativeWebRequest;

public class WebBaseFormArgumentResolver extends BaseFormArgumentResolver{
	private static final Logger logger = LoggerFactory.getLogger(WebBaseFormArgumentResolver.class);
	
	@Override
	protected SessionUser getSessionUser(NativeWebRequest webRequest){
		String ticket = webRequest.getHeader(HeaderConstant.HEADER_TICKET);
		if(StringUtils.isEmpty(ticket)){
			ticket = CookieUtils.getCookieValue(webRequest.getNativeRequest(HttpServletRequest.class), HeaderConstant.COOKIE_TICKET);
		}
        logger.debug("ticket = {}", ticket);
        SessionUser ticketValue = null;
        if(StringUtils.isNotEmpty(ticket)){
            ticketValue = getTicketCacheManager().getTicketCache().getTicketValue(ticket);
        }
        return ticketValue;
	}
}
