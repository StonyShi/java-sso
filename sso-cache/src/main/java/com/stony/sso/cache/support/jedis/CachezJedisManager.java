package com.stony.sso.cache.support.jedis;

import com.stony.sso.cache.Cachez;
import com.stony.sso.cache.CachezManager;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/4/27 </p>
 * <p>Time: 14:02 </p>
 * <p>Version: 1.0 </p>
 */
public class CachezJedisManager implements CachezManager {



    private Cachez cachez;

    @Override
    public Cachez getCachez() {
        return cachez;
    }

    @Override
    public void setCachez(Cachez cachez) {
        this.cachez = cachez;
    }
}
