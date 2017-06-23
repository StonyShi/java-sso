package com.stony.sso.cache.support.jedis;

import com.stony.sso.cache.Cachez;
import com.stony.sso.cache.redis.JedisTemplate;

import java.util.Set;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/4/27 </p>
 * <p>Time: 13:55 </p>
 * <p>Version: 1.0 </p>
 */
public class CachezJedis implements Cachez {

    JedisTemplate jedisTemplate;

    @Override
    public Integer set(String key, Object value) {
        jedisTemplate.set(key, value);
        return 1;
    }

    @Override
    public Integer set(String key, Object value, int expire) {
        jedisTemplate.set(key, value, expire);
        return 1;
    }

    @Override
    public Object get(String key) {
        return jedisTemplate.get(key);
    }

    @Override
    public boolean supportExpire() {
        return true;
    }

    @Override
    public int del(String key) {
        return Integer.valueOf(jedisTemplate.del(key).toString());
    }

    @Override
    public int watch(String watchKey, String cacheKey) {
        Long re = jedisTemplate.sadd(watchKey, cacheKey);
        return 0;
    }

    @Override
    public Set<String> unwatch(String key) {
        Set<String> keys = jedisTemplate.smembers(key);
        jedisTemplate.del(keys);
        jedisTemplate.del(key);
        return keys;
    }

    public JedisTemplate getJedisTemplate() {
        return jedisTemplate;
    }

    public void setJedisTemplate(JedisTemplate jedisTemplate) {
        this.jedisTemplate = jedisTemplate;
    }
}
