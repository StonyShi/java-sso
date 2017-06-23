package com.stony.sso.facade.enums;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/6/16 </p>
 * <p>Time: 18:26 </p>
 * <p>Version: 1.0 </p>
 */
public enum UserStatus {

    /** 1 锁定 **/
    LOCKED(1),
    /** 0 解锁 **/
    UNLOCKED(0);

    public int STATUS;
    UserStatus(int i) {
        this.STATUS = i;
    }
}
