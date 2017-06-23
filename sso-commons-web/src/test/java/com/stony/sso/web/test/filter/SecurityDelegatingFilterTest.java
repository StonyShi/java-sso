package com.stony.sso.web.test.filter;

import com.stony.sso.web.constants.HeaderConstant;
import com.stony.sso.web.verif.VerificationSign;
import com.zhuanche.car.cache.redis.JedisSentinelTemplate;
import com.zhuanche.car.cache.ticket.DefaultTicketCacheManager;
import com.zhuanche.car.cache.ticket.TicketCache;
import com.zhuanche.car.cache.ticket.TicketCacheManager;
import com.zhuanche.car.cache.ticket.support.JedisTicketCache;
import com.stony.sso.web.fliter.SecurityDelegatingFilter;
import org.apache.commons.lang.StringUtils;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.util.Log4jConfigurer;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static org.mockito.BDDMockito.*;


/**
 * @author Stony Created Date : 2016/4/23  18:04
 */
public class SecurityDelegatingFilterTest {

    private ServletRequest request;

    private ServletResponse response;
    private static final Logger logger = LoggerFactory.getLogger(SecurityDelegatingFilterTest.class);


    @Before
    public void setup() {
        try {
            Log4jConfigurer.initLogging("classpath:log4j.xml");
        } catch (FileNotFoundException e) {}
        this.request = new MockHttpServletRequest();
        this.response = new MockHttpServletResponse();
    }


    @Test
    public void testOK() throws Exception {
        Servlet servlet = mock(Servlet.class);

        SecurityDelegatingFilter securityDelegatingFilter  = new SecurityDelegatingFilter();
        JedisSentinelTemplate jedisSentinelTemplate = EasyMock.createMock(JedisSentinelTemplate.class);
        TicketCache ticketCache = new JedisTicketCache(jedisSentinelTemplate);
        TicketCacheManager ticketCacheManager = new DefaultTicketCacheManager();
        ticketCacheManager.setTicketCache(ticketCache);
        ticketCacheManager.afterPropertiesSet();

        securityDelegatingFilter.setTicketCacheManager(ticketCacheManager);

        List<Filter> filters = new ArrayList<Filter>();
//        TicketDelegatingFilter tickFilter = new TicketDelegatingFilter();
//        tickFilter.setExcludes(new ArrayList<String>());
//        tickFilter.setIncludes(new ArrayList<String>());
//        tickFilter.setJedisSentinelTemplate(jedisSentinelTemplate);
//        tickFilter.afterPropertiesSet();
//        filters.add(tickFilter);

        filters.add(new MockFilter());
        filters.add(new MockHeaderFilter()); //headerFilter
        filters.add(new MockFilter());
        filters.add(new MockRequestFilter());
        filters.add(new MockFilter());
        securityDelegatingFilter.setFilters(filters);
        securityDelegatingFilter.afterPropertiesSet();
//        TestFilterChain chain = new TestFilterChain(servlet);
//        chain.setFilters(securityDelegatingFilter);
        MockFilterChain chain = new MockFilterChain(servlet, securityDelegatingFilter);

        ((MockHttpServletResponse)response).setHeader(HeaderConstant.HEADER_TICKET,"123");
        ((MockHttpServletRequest)request).setParameter("username","Ue");
        ((MockHttpServletRequest)request).setParameter("password","123456");

        VerificationSign vs = new VerificationSign(((MockHttpServletRequest)request));
        ((MockHttpServletResponse)response).setHeader(HeaderConstant.HEADER_SIGN,vs.getSign());

        logger.info("----------------------------------------------------------------------------------");
        chain.doFilter(this.request, this.response);
        verify(servlet).service(this.request, this.response);
    }

    @Ignore
    @Test(expected = ServletException.class)
    public void testBadTick() throws Exception {
        Servlet servlet = mock(Servlet.class);


        SecurityDelegatingFilter securityDelegatingFilter  = new SecurityDelegatingFilter();
        JedisSentinelTemplate jedisSentinelTemplate = EasyMock.createMock(JedisSentinelTemplate.class);
        TicketCache ticketCache = new JedisTicketCache(jedisSentinelTemplate);
        TicketCacheManager ticketCacheManager = new DefaultTicketCacheManager();
        ticketCacheManager.setTicketCache(ticketCache);
        ticketCacheManager.afterPropertiesSet();

        securityDelegatingFilter.setTicketCacheManager(ticketCacheManager);

        List<Filter> filters = getFilters();

        securityDelegatingFilter.setFilters(filters);
        securityDelegatingFilter.afterPropertiesSet();
        MockFilterChain chain = new MockFilterChain(servlet, securityDelegatingFilter);

        ((MockHttpServletResponse)response).setHeader(HeaderConstant.HEADER_TICKET,"23");
        ((MockHttpServletRequest)request).setParameter("username","Ue");
        ((MockHttpServletRequest)request).setParameter("password","123456");

        logger.info("----------------------------------------------------------------------------------");
        chain.doFilter(this.request, this.response);

        verify(servlet).service(this.request, this.response);
    }
    @Ignore
    @Test(expected = ServletException.class)
    public void testBadSign() throws Exception {
        Servlet servlet = mock(Servlet.class);

        SecurityDelegatingFilter securityDelegatingFilter  = new SecurityDelegatingFilter();
        JedisSentinelTemplate jedisSentinelTemplate = EasyMock.createMock(JedisSentinelTemplate.class);
        TicketCache ticketCache = new JedisTicketCache(jedisSentinelTemplate);
        TicketCacheManager ticketCacheManager = new DefaultTicketCacheManager();
        ticketCacheManager.setTicketCache(ticketCache);
        ticketCacheManager.afterPropertiesSet();

        securityDelegatingFilter.setTicketCacheManager(ticketCacheManager);


        List<Filter> filters = getFilters();
        filters.add(new MockRequestFilter());

        securityDelegatingFilter.setFilters(filters);
        securityDelegatingFilter.afterPropertiesSet();
        MockFilterChain chain = new MockFilterChain(servlet, securityDelegatingFilter);

        ((MockHttpServletResponse)response).setHeader(HeaderConstant.HEADER_TICKET,"123");
        ((MockHttpServletRequest)request).setParameter("username","Ue");
        ((MockHttpServletRequest)request).setParameter("password","123456");

        logger.info("----------------------------------------------------------------------------------");
        chain.doFilter(this.request, this.response);
        verify(servlet).service(this.request, this.response);
    }
    private List<Filter> getFilters(){
        List<Filter> filters = new ArrayList<Filter>();
        filters.add(new MockFilter());
        filters.add(new MockHeaderFilter()); //headerFilter
        filters.add(new MockFilter());
       return filters;
    }
    private static class MockFilter implements Filter {
        private static final Logger logger = LoggerFactory.getLogger(MockFilter.class);

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            logger.info("Enter");
        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            logger.info("Enter");
//            logger.info("Filter URI[{}]", servletRequest.getServerName());
            filterChain.doFilter(servletRequest,servletResponse);
        }

        @Override
        public void destroy() {
            logger.info("Enter");
        }
    }
    private static class MockHeaderFilter implements Filter {
        private static final Logger logger = LoggerFactory.getLogger(MockFilter.class);

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            logger.info("Enter");
        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            logger.info("Enter");
//            logger.info("Filter URI[{}]", servletRequest.getServerName());
            HttpServletResponse response = (HttpServletResponse)servletResponse;
            String ticket = response.getHeader(HeaderConstant.HEADER_TICKET);
            if(ticket == null || (!"123".equals(ticket))){
                response.setContentType(HeaderConstant.CONTEN_TYPE_CHARSET);
                response.getOutputStream().println("{'msg' : 'tick is validate'}");
                return;
            }
            filterChain.doFilter(servletRequest,servletResponse);
        }

        @Override
        public void destroy() {
            logger.info("Enter");
        }
    }
    private static class MockRequestFilter implements Filter {
        private static final Logger logger = LoggerFactory.getLogger(MockFilter.class);

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            logger.info("Enter");
        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            logger.info("Enter");
//            logger.info("Filter URI[{}]", servletRequest.getServerName());
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse)servletResponse;
            VerificationSign vs = new VerificationSign(request);
            String sign = response.getHeader(HeaderConstant.HEADER_SIGN);
            if(StringUtils.isEmpty(sign) || (!vs.verify(sign))){
                response.setContentType(HeaderConstant.CONTEN_TYPE_CHARSET);
                response.getOutputStream().println("{'msg' : 'sign is validate'}");
                return;
            }
            filterChain.doFilter(servletRequest,servletResponse);
        }

        @Override
        public void destroy() {
            logger.info("Enter");
        }
    }

    private static class TestFilterChain implements FilterChain{
        private ServletRequest request;
        private ServletResponse response;
        private List<Filter> filters;
        private Iterator<Filter> iterator;

        public TestFilterChain(Servlet servlet) {
            this.filters = new ArrayList<>();
        }

        public void setFilters(Filter... filters) {
            this.filters = Arrays.asList(filters);
        }

        private static List<Filter> initFilterList(Servlet servlet, Filter... filters) {
            return Arrays.asList(filters);
        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException, ServletException {
            if(this.request != null) {
                throw new IllegalStateException("This FilterChain has already been called!");
            } else {
                if(this.iterator == null) {
                    this.iterator = this.filters.iterator();
                }

                if(this.iterator.hasNext()) {
                    Filter nextFilter = (Filter)this.iterator.next();
                    nextFilter.doFilter(request, response, this);
                }

                this.request = request;
                this.response = response;
            }
        }
    }


}
