package com.stony.sso.web.fliter;

import com.stony.sso.web.constants.HeaderConstant;
import com.stony.sso.web.entity.ResponseEntity;
import com.zhuanche.car.commons.util.DateUtils;
import com.zhuanche.car.commons.util.RequestHeaderUtil;
import com.stony.sso.web.constants.ResponseConstant;
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
 * {@link org.springframework.web.filter.DelegatingFilterProxy} 代理过滤器
 * spring-bean.xml config
 * <pre class="code">
 *     &lt;bean id="ticketFilter" class="TicketDelegatingFilter"&gt;
 *          &lt;property name="excludes"&gt;
 *              &lt;list&gt;
 *                  &lt;value&gt;/login&lt;/value&gt;
 *                  &lt;value&gt;/logout&lt;/value&gt;
 *              &lt;/list&gt;
 *          &lt;/property&gt;
 *     &lt;/bean&gt;
 * </pre>
 * web.xml config
 * <pre class="code">
 *     &lt;filter&gt;
 *         &lt;filter-name&gt;ticketFilter&lt;/filter-name&gt;
 *         &lt;filter-class&gt;org.springframework.web.filter.DelegatingFilterProxy&lt;/filter-class&gt;
 *         &lt;async-supported&gt;true&lt;/async-supported&gt;
 *         &lt;init-param&gt;
 *             &lt;param-name&gt;targetFilterLifecycle&lt;/param-name&gt;
 *              &lt;param-value&gt;true&lt;/param-value&gt;
 *         &lt;/init-param&gt;
 *         &lt;init-param&gt;
 *             &lt;param-name&gt;targetBeanName&lt;/param-name&gt;
 *             &lt;param-value&gt;ticketFilter&lt;/param-value&gt;
 *         &lt;/init-param&gt;
 *     &lt;/filter&gt;
 *     &lt;filter-mapping&gt;
 *         &lt;filter-name&gt;ticketFilter&lt;/filter-name&gt;
 *         &lt;url-pattern&gt;/*&lt;/url-pattern&gt;
 *         &lt;dispatcher&gt;REQUEST&lt;/dispatcher&gt;
 *     &lt;/filter-mapping&gt;
 * </pre>
 * @author Stony Created Date : 2016/4/20  17:08
 * @see org.springframework.web.filter.DelegatingFilterProxy
 * @see VerificationSignFilter
 * @see SecurityDelegatingFilter
 */
public class TicketDelegatingFilter extends BaseDelegatingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(TicketDelegatingFilter.class);

    private final static String FILTER_APPLIED = TicketDelegatingFilter.class.getName().concat(".APPLIED");

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
        if(uri.equals("/") || excludesUrl(uri)){
            logger.debug("exclude[{}]",uri);
        }else{
            if(includesUrl(uri)){
                String ticket = getTicket(req);
                //ticket == null or get ticket cache is null return error
                //return json {data : {}, responseCode : 100001, responseMessage : "身份验证无效", responseDate : "2016-04-21 11:14:30"}
                logger.debug("filter ticket = {}", ticket);
                //ticketCache 获取 ticket
                if(StringUtils.isEmpty(ticket) || (null == getTicketCacheManager().getTicketCache().getTicketValue(ticket))){
                    onAccessDenied(req, res);
                    return;
                }
            }
        }
        chain.doFilter(request,response);
    }

    protected void onAccessDenied(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
        res.setCharacterEncoding(HeaderConstant.CONTEN_TYPE_CHARSET);
        res.setContentType(HeaderConstant.CONTEN_TYPE_JSON);
        ResponseEntity<Map> responseEntity = new ResponseEntity<Map>();
        responseEntity.setData(new HashMap());
        responseEntity.setResponseCode(ResponseConstant.CODE_TICKET_INVALID);
        responseEntity.setResponseMessage(ResponseConstant.MSG_TICKET_INVALID);
        responseEntity.setResponseDate(DateUtils.now());
        res.getOutputStream().write(responseEntity.toJsonString().getBytes(HeaderConstant.CONTEN_TYPE_CHARSET));
    }
    protected String getTicket(HttpServletRequest req){
        return req.getHeader(HeaderConstant.HEADER_TICKET);
    }

    @Override
    public String getFilterApplied() {
        return FILTER_APPLIED;
    }

    @Override
    public void destroy() {
        logger.debug("Enter");
    }


}
