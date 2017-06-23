package com.stony.sso.cache.support.map;

import com.stony.sso.cache.Cachez;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Stony on 2016/3/11.
 */
public class CachezMap implements Cachez {

    public static final ConcurrentHashMap<String,Object> CACHES = new ConcurrentHashMap<String,Object>();

    @Override
    public Integer set(String key, Object value) {
        return CACHES.put(key,value) == null ? 1 : 0;
    }

    @Override
    public Integer set(String key, Object value, int expire) {
        return CACHES.put(key,value) == null ? 1 : 0;
    }

    @Override
    public Object get(String key) {
        return CACHES.get(key);
    }

    @Override
    public boolean supportExpire() {
        return false;
    }

    @Override
    public int del(String key) {
        return CACHES.remove(key) == null ? 0 : 1;
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
