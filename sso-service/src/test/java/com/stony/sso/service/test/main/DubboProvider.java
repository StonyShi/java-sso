package com.stony.sso.service.test.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/7/14 </p>
 * <p>Time: 13:38 </p>
 * <p>Version: 1.0 </p>
 */
public class DubboProvider {

    public static void main(String[] args) {
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
            context.start();
            System.out.println("################################### --STARTED-- ##################################################");
//            System.out.println("getRegisteredClasses : " + SerializableClassRegistry.getRegisteredClasses());
            System.out.println("Dubbo " + context + " started!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("== DubboProvider context start error:");
            e.printStackTrace();
        }

        synchronized (DubboProvider.class) {
            while (true) {
                try {
                    DubboProvider.class.wait();
                } catch (InterruptedException e) {
                    System.out.println("== synchronized error:");
                    e.printStackTrace();
                }
            }
        }
    }
}
