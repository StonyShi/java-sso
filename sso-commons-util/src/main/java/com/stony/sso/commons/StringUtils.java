package com.stony.sso.commons;

/**
 * <p>Java-SSO
 * <p>com.stony.sso
 *
 * @author stony
 * @version 上午10:18
 * @since 2017/6/22
 */
public abstract class StringUtils {

    public static boolean isNotEmpty(String v) {
        return !isEmpty(v);
    }

    public static boolean isEmpty(String v) {
        return v == null || v.length() == 0;
    }
}
