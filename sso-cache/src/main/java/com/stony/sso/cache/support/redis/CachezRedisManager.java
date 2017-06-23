package com.stony.sso.cache.support.redis;

import com.stony.sso.cache.Cachez;
import com.stony.sso.cache.CachezManager;

/**
 * Created by Stony on 2016/3/11.
 */
public class CachezRedisManager implements CachezManager {
    private Cachez cachez;
    private Cachez[] cachezs;
    @Override
    public Cachez getCachez() {
        if(cachez == null){
            cachez = new CachezRedis();
        }
        return cachez;
    }

    @Override
    public void setCachez(Cachez cachez) {
        this.cachez = cachez;
    }
}
