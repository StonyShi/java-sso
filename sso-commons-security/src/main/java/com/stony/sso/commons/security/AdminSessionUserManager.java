package com.stony.sso.commons.security;


import com.stony.sso.cache.ticket.SessionUser;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/6/12 </p>
 * <p>Time: 11:00 </p>
 * <p>Version: 1.0 </p>
 */
public interface AdminSessionUserManager {
    SessionUser getSessionUser(String username);
}
