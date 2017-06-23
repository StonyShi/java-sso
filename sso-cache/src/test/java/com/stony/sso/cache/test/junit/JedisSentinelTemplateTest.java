package com.stony.sso.cache.test.junit;

import com.stony.sso.cache.redis.JedisSentinelTemplate;
import com.stony.sso.commons.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Stony Created Date : 2016/4/22  15:32
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:spring-redis-test.xml","classpath:spring/spring-redis-sentinel.xml"})
public class JedisSentinelTemplateTest {

    private static final Logger logger = LoggerFactory.getLogger(JedisSentinelTemplateTest.class);

    @Resource
    JedisSentinelTemplate jedisSentinelTemplate;


    @Test
    public void testSet(){
        String key  = "sssssssvvv";
        jedisSentinelTemplate.sadd(key,"111dd");
        jedisSentinelTemplate.sadd(key,"111bbb");

        logger.info("set = {}", jedisSentinelTemplate.smembers(key));
        logger.info("del set ,{}", jedisSentinelTemplate.del(jedisSentinelTemplate.smembers("SysFindUser_UserNames")));
//        logger.info("del key ,{}", jedisSentinelTemplate.del("SysFindUser_UserNames"));
//        logger.info("del key ,{}", jedisSentinelTemplate.del("SysFindUser_admin"));
//        logger.info("del key ,{}", jedisSentinelTemplate.del("SysFindUser_LiXue"));
        logger.info("Enter@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }
    @Test
    public void testString(){
        String key = "yuke";
        Map map = new HashMap<>();
        map.put("x","12313");
        map.put("y","9234234");
        map.put("z",12313);
        jedisSentinelTemplate.set(key,map);
        Assert.notNull(jedisSentinelTemplate.get(key));
        jedisSentinelTemplate.del(key);


        Assert.isNull(jedisSentinelTemplate.get(key));

        for(int i = 0; i < 30; i++){
            key = "Bule"+ DateUtils.dateTime2string(new Date(System.currentTimeMillis() - ((1+i) * 900000)));
            logger.info("key = {}",key );
            jedisSentinelTemplate.set(key,map);
            Assert.notNull(jedisSentinelTemplate.get(key));
            jedisSentinelTemplate.del(key);
        }
        logger.info("Enter@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }
}
