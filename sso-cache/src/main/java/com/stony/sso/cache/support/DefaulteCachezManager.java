package com.stony.sso.cache.support;

import com.stony.sso.cache.Cachez;
import com.stony.sso.cache.CachezManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/4/27 </p>
 * <p>Time: 14:03 </p>
 * <p>Version: 1.0 </p>
 */
public class DefaulteCachezManager implements CachezManager,InitializingBean{

    private Cachez cachez;


    @Override
    public Cachez getCachez() {
        return cachez;
    }

    @Override
    public void setCachez(Cachez cachez) {
        this.cachez = cachez;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.cachez, "property cachez is required");
    }
}
