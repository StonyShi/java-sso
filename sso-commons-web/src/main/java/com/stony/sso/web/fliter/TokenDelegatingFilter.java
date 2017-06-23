package com.stony.sso.web.fliter;

import com.stony.sso.cache.ticket.SessionUser;
import com.stony.sso.commons.DESUtils;
import com.stony.sso.commons.DateUtils;
import com.stony.sso.commons.RequestHeaderUtil;
import com.stony.sso.web.constants.HeaderConstant;
import com.stony.sso.web.constants.ResponseConstant;
import com.stony.sso.web.entity.ResponseEntity;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/5/17 </p>
 * <p>Time: 14:51 </p>
 * <p>Version: 1.0 </p>
 */
public class TokenDelegatingFilter extends BaseDelegatingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(TokenDelegatingFilter.class);
    private final static String FILTER_APPLIED = TokenDelegatingFilter.class.getName().concat(".APPLIED");
    private TokenUserManager tokenUserManager;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("Enter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("Enter");
        doOnceFilter(servletRequest,servletResponse,filterChain);
    }

    @Override
    protected void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        logger.info("Filter URI[{}]", uri);
        logger.info("RequestHeaderInfo[{}]", RequestHeaderUtil.getHeaderMap(req));
        if (uri.equals("/") || excludesUrl(uri)) {
            logger.debug("exclude[{}]", uri);
        } else {
            if (includesUrl(uri)) {
                String token = req.getHeader(HeaderConstant.HEADER_TOKEN);
                SessionUser ticketValue = null;
                if(StringUtils.isNotEmpty(token)){
                    logger.debug("filter token = {}", token);
                    //获取token SessionUser,如果sessionUser 不存在，解析出手机号，查询SessionUser,存入缓存
                    ticketValue = getTicketCacheManager().getTicketCache().getTicketValue(token);
                    if(ticketValue == null){
                        String phone = DESUtils.token2Phone(token);
                        if(StringUtils.isNotEmpty(phone)){
                            logger.debug("filter phone = {}", phone);
                            SessionUser sessionUser = tokenUserManager.getSessionUser(phone);
                            if(sessionUser != null) {
                                sessionUser.setTicket(token);
                                getTicketCacheManager().getTicketCache().setTicket(sessionUser, token);
                                ticketValue = sessionUser;
                                logger.debug("filter sessionUser = {}", sessionUser);
                            }else{
                                logger.warn("filter sessionUser is null, token = " + token);
                            }
                        }else{
                            ticketValue = null;
                            logger.warn("filter phone is null, token = " + token);
                        }
                    }
                }
                if(StringUtils.isEmpty(token) || (ticketValue == null)){
                    res.setCharacterEncoding(HeaderConstant.CONTEN_TYPE_CHARSET);
                    res.setContentType(HeaderConstant.CONTEN_TYPE_JSON);
                    ResponseEntity<Map> responseEntity = new ResponseEntity<>();
                    responseEntity.setData(new HashMap());
                    responseEntity.setResponseCode(ResponseConstant.CODE_TICKET_INVALID);
                    responseEntity.setResponseMessage(ResponseConstant.MSG_TICKET_INVALID);
                    responseEntity.setResponseDate(DateUtils.now());
                    res.getOutputStream().write(responseEntity.toJsonString().getBytes(HeaderConstant.CONTEN_TYPE_CHARSET));
                    return;
                }
            }
        }
        chain.doFilter(request,response);
    }

    @Override
    public String getFilterApplied() {
        return FILTER_APPLIED;
    }

    @Override
    public void destroy() {
        logger.debug("Enter");
    }

    public void setTokenUserManager(TokenUserManager tokenUserManager) {
        this.tokenUserManager = tokenUserManager;
    }

    public static interface TokenUserManager{
        SessionUser getSessionUser(String phone);
    }
}
