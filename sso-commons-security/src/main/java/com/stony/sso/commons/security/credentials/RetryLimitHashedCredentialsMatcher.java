package com.stony.sso.commons.security.credentials;

import com.stony.sso.commons.security.SecurityConstants;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>Created with car-commons-security.</p>
 * <p>User: Stony</p>
 * <p>Date: 2016/4/25</p>
 * <p>Time: 18:30</p>
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    public static String PASSWORD_RETRY_CACHE = SecurityConstants.CACHE_PASSWORD_RETRY;

    private Cache<String, AtomicInteger> passwordRetryCache;

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache(PASSWORD_RETRY_CACHE);
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();
        //retry count + 1
        AtomicInteger retryCount = passwordRetryCache.get(username);
        if(retryCount == null) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username, retryCount);
        }
        if(retryCount.incrementAndGet() > 5) {
            //if retry count > 5 throw
            throw new ExcessiveAttemptsException();
        }

        boolean matches = super.doCredentialsMatch(token, info);
        if(matches) {
            //clear retry count
            passwordRetryCache.remove(username);
        }
        return matches;
    }

    public static void setPasswordRetryCache(String cacheName){
        RetryLimitHashedCredentialsMatcher.PASSWORD_RETRY_CACHE = cacheName;
    }

}
