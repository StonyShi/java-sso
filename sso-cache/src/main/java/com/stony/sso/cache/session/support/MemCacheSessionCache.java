package com.stony.sso.cache.session.support;

import com.stony.sso.cache.session.SessionCache;

/**
 * @author Stony Created Date : 2016/4/23  17:50
 */
public class MemCacheSessionCache implements SessionCache {


    @Override
    public int setSession(String key, Object value, int seconds) {
        //TODO
        return 0;
    }

    @Override
    public Object getSession(String key) {
        //TODO
        return null;
    }

    @Override
    public int delSession(String key) {
        //TODO
        return 0;
    }

    @Override
    public boolean exists(String key) {
        return false;
    }
}
