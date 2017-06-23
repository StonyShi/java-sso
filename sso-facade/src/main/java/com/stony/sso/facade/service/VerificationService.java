package com.stony.sso.facade.service;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/6/16 </p>
 * <p>Time: 13:56 </p>
 * <p>Version: 1.0 </p>
 */
public interface VerificationService {
    /**
     * 验证码校验
     * @param username
     * @param verifyCode
     * @return
     */
    boolean verify(String username, String verifyCode);

    /**
     * 下发短信
     * @param username
     * @return
     */
    boolean sendSms(String username);
}
