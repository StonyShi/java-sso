package com.stony.sso.cache.test;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Stony Created Date : 2016/4/23  15:53
 */

public class PropertiesTest {


    @Ignore
    @Test
    public void test() throws IOException {
        PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();
        factoryBean.setFileEncoding("utf-8");
        factoryBean.setLocation(new ClassPathResource("redis.properties"));
        factoryBean.afterPropertiesSet();
        Properties properties = factoryBean.getObject();
        System.out.println(properties.getProperty("redis3.timeout"));
        System.out.println(properties.getProperty("redis4.host"));
        System.out.println(properties.getProperty("redis10.host"));
    }
}
