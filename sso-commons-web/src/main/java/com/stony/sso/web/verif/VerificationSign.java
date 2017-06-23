package com.stony.sso.web.verif;

import com.stony.sso.commons.SignUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/5/10 </p>
 * <p>Time: 11:29 </p>
 * <p>Version: 1.0 </p>
 * <pre class="code">
 *
 * </pre>
 */
public class VerificationSign {

    private static final Logger logger = LoggerFactory.getLogger(VerificationSign.class);

    private HttpServletRequest request;

    public VerificationSign(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * 不包含appKey
     * 校验签名字符
     * @param originalSign 大写
     * @return 签名一致返回true
     */
    @Deprecated
    public boolean verify(String originalSign){
        return originalSign.equalsIgnoreCase(getSign());
    }
    /**
     * 校验签名字符
     * @param originalSign 大写
     * @param appKey 参与签名的appKey
     * @return 签名一致返回true
     */
    public boolean verify(String originalSign,String appKey){
        return originalSign.equalsIgnoreCase(getSign(appKey));
    }

    /**
     * 不包含appKey
     * Request请求参数签名
     * @return
     */
    @Deprecated
    public String getSign(){
        return getSign(null);
    }
    /**
     * Request请求参数签名
     * @param appKey 如果appKey 为空不参与签名
     * @return
     */
    public String getSign(String appKey){
        Map<String, String[]> parameterMap = this.request.getParameterMap();
        logger.debug("verify getSign parameterMap : {}", parameterMap);
        Map<String, String> useParameters = new HashMap<String,String>();
        for(Map.Entry entry : parameterMap.entrySet()){
            useParameters.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
        }
        logger.debug("verify getSign useParameters : {}", useParameters);
        try {
            if(appKey != null) {
                return SignUtils.generateAppSign(useParameters, appKey);
            }else {
                return SignUtils.generateSign(useParameters);
            }
        } catch (Exception e) {
            logger.warn("verify sign error : {}",e.getMessage());
            return "BadSign_"+System.currentTimeMillis();
        }
    }
}
