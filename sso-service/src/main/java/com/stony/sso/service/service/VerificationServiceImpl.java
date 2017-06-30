package com.stony.sso.service.service;


import com.stony.sso.cache.constants.BaseCacheConstant;
import com.stony.sso.cache.redis.JedisSentinelTemplate;
import com.stony.sso.cache.redis.JedisTemplate;
import com.stony.sso.commons.*;
import com.stony.sso.facade.entity.*;
import com.stony.sso.facade.service.*;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/6/16 </p>
 * <p>Time: 14:03 </p>
 * <p>Version: 1.0 </p>
 */
@Service("verificationService")
public class VerificationServiceImpl implements VerificationService {

    @Resource
    UserService userService;

//    @Resource
//    MobileMsgService mobileMsgService;

    @Resource(name = "jedisTemplate")
    JedisTemplate jedisTemplate;

    @Override
    public boolean verify(String username, String verifyCode) {
        User user = userService.findByUsername(username);
        if(user == null) {
            return false;
        }
        String cacheCode = jedisTemplate.getStr(BaseCacheConstant.getPhoneVerificationCodeKey(user.getPhone()));
        if(cacheCode ==  null){
            return false;
        }
        return cacheCode.equalsIgnoreCase(verifyCode);
    }

    @Override
    public boolean sendSms(String username){
        User user = userService.findByUsername(username);
        if (user == null) {
            return false;
        }
        String cacheCode = jedisTemplate.getStr(BaseCacheConstant.getPhoneVerificationCodeKey(user.getPhone()));
        if (StringUtils.isNotEmpty(cacheCode)) {
            return true;
        }
        int rand = RandomsUtil.generateCode();
        MobileMsg mobileMsg = new MobileMsg(user.getPhone(), "你的验证码为[" + rand + "],30分钟有效。");
        MobileEntity msgResult = null;//mobileMsgService.sendMobileMsg(mobileMsg);
        if (msgResult.isOk()) {
            jedisTemplate.setStr(BaseCacheConstant.getPhoneVerificationCodeKey(user.getPhone()), String.valueOf(rand), 60 * 30); //30分钟
            return true;
        }
        return false;
    }
}
