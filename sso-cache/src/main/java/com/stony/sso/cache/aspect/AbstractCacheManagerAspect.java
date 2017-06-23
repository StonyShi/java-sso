package com.stony.sso.cache.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Stony
 * Created Date : 2016/4/11 9:35
 */
public abstract class AbstractCacheManagerAspect implements ApplicationContextAware {
    private static final Logger logger = LoggerFactory.getLogger(AbstractCacheManagerAspect.class);

    private ApplicationContext ac;

    public abstract void cachePointcut();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ac = applicationContext;
    }
}
