package com.stony.sso.cache.constants;

/**
 * @author Stony Created Date : 2016/4/20  17:36
 */
public abstract class BaseCacheConstant {

    public static final String KEY_USER_TICKET = "CarUserTicket";
    public static final String KEY_PHONE_PASSWD = "CarPhonePasswd";


    public static String getUserTicketKey(String ticket){
        return KEY_USER_TICKET + "_" + ticket;
    }

    public static  String getPhoneVerificationCodeKey(String phone){
        return "PhoneVerifyCode_" + phone;
    }

}
