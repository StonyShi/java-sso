package com.stony.sso.cache.mongo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.Field;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/5/20 </p>
 * <p>Time: 17:57 </p>
 * <p>Version: 1.0 </p>
 */
public abstract class MongoUtils {
    private static final Logger logger = LoggerFactory.getLogger(MongoUtils.class);


    public static Update getUpdate(Object info){
        Update upd = new Update();
        BeanWrapper beanWrapper = new BeanWrapperImpl(info);
        Field[] fields = info.getClass().getDeclaredFields();
        String name = null;
        for (Field field : fields) {
            name = field.getName();
            if("serialVersionUID".equalsIgnoreCase(name)){
                continue;
            }
            try {
                if (null != beanWrapper.getPropertyValue(name)) {
                    upd.set(name, beanWrapper.getPropertyValue(name));
                }
            } catch (BeansException e) {
                logger.warn("field {} is error : {}.", name, e.getMessage());
            }
        }
        return upd;
    }
}
