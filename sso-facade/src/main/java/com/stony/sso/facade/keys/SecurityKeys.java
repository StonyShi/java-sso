package com.stony.sso.facade.keys;

/**
 * 权限管理KEYS 常量
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/4/27 </p>
 * <p>Time: 13:48 </p>
 * <p>Version: 1.0 </p>
 */
public abstract class SecurityKeys {
    public static final String MAGIC = "HORSE_";

    public static final String KEY_FIND_APP = MAGIC + "SysFindApp";

    public static final String KEY_FIND_ROLE = MAGIC + "SysFindRole";

    public static final String KEY_FIND_All_ROLE = MAGIC + "SysFindAllRole";

    public static final String KEY_FIND_USER = MAGIC + "SysFindUser";

    public static final String KEY_FIND_RESOURCE = MAGIC + "SysFindResource";

    public static final String KEY_FIND_ORGANIZATION = MAGIC + "SysFindOrganization";

    public static final String KEY_FIND_AUTHORIZATION = MAGIC + "SysFindAuthorization";

    public static final String KEY_FIND_ICON = MAGIC + "SysFindIcon";

    public static final String KEY_FIND_VARIABLE = MAGIC + "SysFindVariable";


    public static final String KEY_OAUTH_CODE = MAGIC + "oauthCode_";
    public static final String KEY_OAUTH_TOKEN = MAGIC + "oauthToken_";
}
