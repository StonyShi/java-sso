package com.stony.sso.facade.enums;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/5/26 </p>
 * <p>Time: 10:11 </p>
 * <p>Version: 1.0 </p>
 */
public enum ResourceStatus {
    /** 1 有效 **/
    AVAILABLE(1),
    /** 0 无效 **/
    UNAVAILABLE(0);

    public int STATUS;
    ResourceStatus(int i) {
        this.STATUS = i;
    }

}
