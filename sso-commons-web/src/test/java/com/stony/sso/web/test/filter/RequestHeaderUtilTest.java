package com.stony.sso.web.test.filter;

import java.util.*;
import javax.servlet.ServletContext;

import com.stony.sso.commons.RequestHeaderUtil;
import org.junit.Before;
import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/5/17 </p>
 * <p>Time: 21:00 </p>
 * <p>Version: 1.0 </p>
 */
public class RequestHeaderUtilTest {

    private MockHttpServletRequestBuilder builder;

    private ServletContext servletContext;

    private static final Logger logger = LoggerFactory.getLogger(RequestHeaderUtilTest.class);


    @Before
    public void setUp() {
        this.builder = MockMvcRequestBuilders.get("/user");
        HttpHeaders headers = new HttpHeaders();
        headers.add("car-ticket","xsdfsdfsdf");
        headers.add("car-imei","123123");
        headers.add("car-request-time","146854646456456");
        headers.add("car-userId","23123123");
        headers.add("car-body","fff");
        builder.headers(headers);
        servletContext = new MockServletContext();
    }



    @Test
    public void test(){
        MockHttpServletRequest request = this.builder.buildRequest(this.servletContext);

        Map<String,String> result = new HashMap<>();
        Enumeration<String> headerNames  = request.getHeaderNames();
        for (Enumeration enumeration = headerNames; enumeration.hasMoreElements(); ) {
            String name = enumeration.nextElement().toString();
            String value = request.getHeader(name);
            result.put(name,value);
        }
        logger.warn("result == {}", RequestHeaderUtil.getHeaderMap(request));
        logger.warn("result === {}" , result);




    }
}
