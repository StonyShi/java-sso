package com.stony.sso.cache.test.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

/**
 * <p>Java-SSO
 * <p>com.stony.sso.cache.test.junit
 *
 * @author stony
 * @version 上午11:15
 * @since 2017/6/23
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-redis-test.xml"})
public class JedisClusterTest {

    private static final Logger logger = LoggerFactory.getLogger(JedisClusterTest.class);
    @Autowired
    JedisCluster jedisCluster;

    @Test
    public void test_26(){
        String key = "sssssssvvv";
        jedisCluster.sadd(key, "111dd");
        jedisCluster.sadd(key, "111bbb");

        logger.info("set = {}", jedisCluster.smembers(key));
        logger.info("del set ,{}", jedisCluster.del(key));
        logger.info("Enter@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");


    }
}
