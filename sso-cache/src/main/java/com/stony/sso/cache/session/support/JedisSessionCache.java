package com.stony.sso.cache.session.support;

import com.stony.sso.cache.redis.JedisTemplate;
import com.stony.sso.cache.session.SessionCache;

/**
 * @author Stony Created Date : 2016/4/23  17:46
 */
public class JedisSessionCache implements SessionCache {

    private JedisTemplate jedisTemplate;

    public JedisSessionCache(JedisTemplate jedisTemplate) {
        this.jedisTemplate = jedisTemplate;
    }
    private JedisSessionCache(){}

    @Override
    public int setSession(String key, Object value,int seconds) {
        jedisTemplate.set(key,value,seconds);
        return 0;
    }

    @Override
    public Object getSession(String key) {
        return jedisTemplate.get(key);
    }

    @Override
    public int delSession(String key) {
        jedisTemplate.del(key);
        return 1;
    }

    @Override
    public boolean exists(String key) {
        return jedisTemplate.exists(key);
    }
}
