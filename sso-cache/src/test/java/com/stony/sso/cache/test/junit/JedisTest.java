package com.stony.sso.cache.test.junit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * <p>Java-SSO
 * <p>com.stony.sso.cache.test.junit
 *
 * @author stony
 * @version 下午2:29
 * @since 2017/6/23
 */
public class JedisTest {
    private static final Logger logger = LoggerFactory.getLogger(JedisTest.class);
    public static void main(String[] args){

        String key = "sssssssvvv";

        Jedis jedis = new Jedis("10.0.11.173",6383);
        jedis.auth("P65UCJVVG7");

        jedis.sadd(key, "111dd");
        jedis.sadd(key, "111bbb");

        logger.info("set = {}", jedis.smembers(key));
        logger.info("del set ,{}", jedis.del(key));
        logger.info("Enter@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        jedis.close();
    }
}
