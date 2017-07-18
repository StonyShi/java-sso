package com.stony.sso.facade.enums;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/5/12 </p>
 * <p>Time: 20:18 </p>
 * <p>Version: 1.0 </p>
 */
public enum ResourceTypeEnum {

    MENU("MENU"), BUTTON("BUTTON");
    public String TYPE;

    ResourceTypeEnum(String type) {
        this.TYPE = type;
    }
}
