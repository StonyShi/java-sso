package com.stony.sso.facade.service;

import com.stony.sso.facade.entity.MobileEntity;
import com.stony.sso.facade.entity.MobileMsg;

/**
 * <p>Java-SSO
 * <p>com.stony.sso.facade.service
 *
 * @author stony
 * @version 下午12:39
 * @since 2017/6/22
 */
public interface MobileMsgService {
    MobileEntity sendMobileMsg(MobileMsg mobileMsg);
}
