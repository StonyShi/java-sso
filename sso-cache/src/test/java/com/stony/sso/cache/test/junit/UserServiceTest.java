package com.stony.sso.cache.test.junit;

import com.stony.sso.cache.test.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author: Stony  Date: 2016/4/7 Time: 15:16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:cache-test.xml"})
public class UserServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);


    @Resource
    UserService userService;

    @Before
    public void setup(){
        try {
            Log4jConfigurer.initLogging("classpath:log4j.xml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void removeALL() throws Exception {
        logger.info("---------------------------------------------------------");
        logger.info("result : " + userService.getUser(100000));
        logger.info("result : " + userService.getUserAll());
        logger.info("result : " + userService.getUser(100000));
        logger.info("result : " + userService.getUserAll());
        logger.info("result : " + userService.removeUser(100000));
        logger.info("result : " + userService.getUser(100000));
        logger.info("result : " + userService.getUserAll());
        logger.info("---------------------------------------------------------");
    }

    @Repeat(5)
    @Test
    public void simple() throws Exception {
        logger.info("---------------------------------------------------------");
        logger.info("result : " + userService.getUser(100000));
        logger.info("---------------------------------------------------------");
    }

    @Test
    public void remove() throws Exception {
        logger.info("---------------------------------------------------------");
        logger.info("result : " + userService.getUser(100000));
        logger.info("result : " + userService.removeUser(100000));
        logger.info("result : " + userService.getUser(100000));
        logger.info("---------------------------------------------------------");
    }

    @Test
    public void update()throws Exception {
        logger.info("---------------------------------------------------------");
        logger.info("result : " + userService.updateUser(100000));
        logger.info("---------------------------------------------------------");
    }

    @Test
    public void tex(){
        ConcurrentHashMap<String,Object> concurrentHashMap = new ConcurrentHashMap<String,Object>();
        logger.info("---------------------------------------------------------");
        logger.info("result put : " + concurrentHashMap.put("xx","xx"));
        logger.info("result put : " + concurrentHashMap.put("xx","xx"));
        logger.info("result remove : " + concurrentHashMap.remove("xx"));
        logger.info("result remove : " + concurrentHashMap.remove("xx"));
        logger.info("---------------------------------------------------------");
    }
}
