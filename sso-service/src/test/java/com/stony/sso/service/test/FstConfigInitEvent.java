package com.stony.sso.service.test;

//import com.alibaba.dubbo.common.serialize.support.SerializableClassRegistry;
//
//import org.nustaq.serialization.FSTConfiguration;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/7/4 </p>
 * <p>Time: 13:04 </p>
 * <p>Version: 1.0 </p>
 */
public class FstConfigInitEvent implements ApplicationListener<ContextRefreshedEvent> {

//    public static FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();
//    volatile boolean isInit = false;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        beforeClass();
    }
//
    public void beforeClass(){
//        if(isInit){
//            return;
//        }
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//        System.out.println(SerializableClassRegistry.getRegisteredClasses());
//        for (Class clazz : SerializableClassRegistry.getRegisteredClasses()) {
//            conf.registerClass(clazz);
//        }
//        this.isInit = true;
//        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }
}
