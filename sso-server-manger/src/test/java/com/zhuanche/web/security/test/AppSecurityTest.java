package com.zhuanche.web.security.test;

import com.car.sqoil.facade.security.service.UserService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/6/14 </p>
 * <p>Time: 13:28 </p>
 * <p>Version: 1.0 </p>
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-context.xml")
public class AppSecurityTest {

    @Resource
    UserService userService;

    @Test
    @Ignore
    public void test(){
        System.out.println(userService.findUserByRole("25"));
    }
}
