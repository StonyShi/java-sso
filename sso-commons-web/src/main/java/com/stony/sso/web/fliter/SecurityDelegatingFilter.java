package com.stony.sso.web.fliter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;
import java.util.List;

/**
 * <pre class="code">
 * web.xml config:
 * &lt;filter&gt;
 *   &lt;filter-name&gt;securityFilter&lt;/filter-name&gt;
 *   &lt;filter-class&gt;org.springframework.web.filter.DelegatingFilterProxy&lt;/filter-class&gt;
 *   &lt;async-supported&gt;true&lt;/async-supported&gt;
 *   &lt;init-param&gt;
 *     &lt;param-name&gt;targetFilterLifecycle&lt;/param-name&gt;
 *     &lt;param-value&gt;true&lt;/param-value&gt;
 *   &lt;/init-param&gt;
 *   &lt;init-param&gt;
 *     &lt;param-name&gt;targetBeanName&lt;/param-name&gt;
 *     &lt;param-value&gt;securityFilter&lt;/param-value&gt;
 *   &lt;/init-param&gt;
 * &lt;/filter&gt;
 * &lt;filter-mapping&gt;
 *   &lt;filter-name&gt;securityFilter&lt;/filter-name&gt;
 *   &lt;url-pattern&gt;/*&lt;/url-pattern&gt;
 *   &lt;dispatcher&gt;REQUEST&lt;/dispatcher&gt;
 * &lt;/filter-mapping&gt;
 * spring-security.xml config:
 * &lt;bean id="ticketFilter" class="TicketDelegatingFilter"&gt;
 *   &lt;property name="excludes"&gt;
 *   &lt;list&gt;
 *     &lt;value&gt;/driver/login&lt;/value&gt;
 *     &lt;value&gt;/driver/logout&lt;/value&gt;
 *   &lt;/list&gt;
 *   &lt;/property&gt;
 *   &lt;property name="jedisSentinelTemplate" ref="jedisSentinelTemplate"/&gt;
 * &lt;/bean&gt;
 * &lt;bean id="verificationSignFilter" class="VerificationSignFilter"&gt;
 *   &lt;property name="excludes"&gt;
 *   &lt;list&gt;
 *     &lt;value&gt;/driver/login&lt;/value&gt;
 *     &lt;value&gt;/driver/logout&lt;/value&gt;
 *   &lt;/list&gt;
 *   &lt;/property&gt;
 *   &lt;property name="jedisSentinelTemplate" ref="jedisSentinelTemplate"/&gt;
 * &lt;/bean&gt;

 * &lt;bean id="securityFilter" class="SecurityDelegatingFilter"&gt;
 *   &lt;property name="filters"&gt;
 *   &lt;list&gt;
 *     &lt;ref bean="ticketFilter" /&gt;
 *     &lt;ref bean="verificationSignFilter" /&gt;
 *   &lt;/list&gt;
 *   &lt;/property&gt;
 *   &lt;property name="excludes"&gt;
 *     &lt;list&gt;
 *       &lt;value&gt;/driver/login&lt;/value&gt;
 *       &lt;value&gt;/driver/logout&lt;/value&gt;
 *     &lt;/list&gt;
 *   &lt;/property&gt;
 *   &lt;property name="jedisSentinelTemplate" ref="jedisSentinelTemplate"/&gt;
 * &lt;/bean&gt;
 * </pre>
 * @author Stony Created Date : 2016/4/22  18:21
 */
public class SecurityDelegatingFilter extends BaseDelegatingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityDelegatingFilter.class);

    private final static String FILTER_APPLIED = SecurityDelegatingFilter.class.getName().concat(".APPLIED");

    private List<Filter> filters;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("Enter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("Enter");
        doOnceFilter(servletRequest, servletResponse, filterChain);
    }

    @Override
    protected void doFilterInternal(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)throws IOException, ServletException {
        if(filters == null || filters.size() == 0){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            FilterChainProxy filterChainProxy = new FilterChainProxy(this.filters,filterChain);
            filterChainProxy.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public String getFilterApplied() {
        return FILTER_APPLIED;
    }

    @Override
    public void destroy() {
        logger.debug("Enter");
        if(filters != null){
            //
        }
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    /**
     * FilterChainProxy
     */
    private static class FilterChainProxy implements FilterChain{
        private static final Logger logger = LoggerFactory.getLogger(FilterChainProxy.class);

        private final FilterChain originalChain;
        private List<Filter> filters;
        private final int size;
        private int currentPosition = 0;

        public FilterChainProxy(List<Filter> filters, FilterChain originalChain) {
            this.filters = filters;
            this.originalChain = originalChain;
            this.size = filters.size();
        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException, ServletException {
            if (currentPosition == size) {
                logger.debug("FilterChainProxy[{}] reached end of additional filter chain; proceeding with original chain", getRequestURL(servletRequest));
                originalChain.doFilter(servletRequest, servletResponse);
            }else{
                currentPosition++;
                Filter nextFilter = this.filters.get(currentPosition - 1);
                logger.debug("FilterChainProxy[{}] at position [{}] of size[{}] in additional filter chain; firing Filter: {}",
                        getRequestURL(servletRequest),
                        currentPosition,
                        size,
                        nextFilter.getClass().getSimpleName());
                nextFilter.doFilter(servletRequest, servletResponse, this);
            }
        }
        private static final String HTTP = "http";
        private static final String HTTPS = "https";
        public StringBuffer getRequestURL(ServletRequest request) {
            String scheme = request.getScheme();
            int serverPort = request.getServerPort();
            StringBuffer url = new StringBuffer(scheme).append("://").append(request.getServerName());

            if (serverPort > 0 && ((HTTP.equalsIgnoreCase(scheme) && serverPort != 80) ||
                    (HTTPS.equalsIgnoreCase(scheme) && serverPort != 443))) {
                url.append(':').append(serverPort);
            }
            return url;
        }
    }
}
