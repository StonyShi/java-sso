package com.stony.sso.cache.session;

import java.io.Serializable;

/**
 * @author Stony Created Date : 2016/4/23  17:39
 */
public interface SessionCache extends Serializable{


    int setSession(String key, Object value, int seconds);

    Object getSession(String key);

    int delSession(String key);

    boolean exists(String key);
}
