package com.stony.sso.cache.support.redis;

import com.stony.sso.cache.Cachez;

import java.util.Set;

/**
 * @author: Stony  Date: 2016/4/7 Time: 15:04
 */
public class CachezRedis implements Cachez {


    @Override
    public Integer set(String key, Object value) {
        return null;
    }

    @Override
    public Integer set(String key, Object value, int expire) {
        return null;
    }

    @Override
    public Object get(String key) {
        return null;
    }

    @Override
    public boolean supportExpire() {
        return true;
    }

    @Override
    public int del(String key) {
        return 0;
    }

    @Override
    public int watch(String watchKey, String cacheKey) {
        return 0;
    }

    @Override
    public Set<String> unwatch(String key) {
        return null;
    }
}
