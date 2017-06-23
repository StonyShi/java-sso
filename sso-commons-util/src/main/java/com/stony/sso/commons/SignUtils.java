package com.stony.sso.commons;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;



/**
 * @author Stony Created Date : 2016/4/21  11:49
 */
public abstract class SignUtils {
    private static final Logger logger = LoggerFactory.getLogger(SignUtils.class);


    /**
     * 签名特别注意以下重要规则：<br>
     * ◆ 参数名ASCII码从小到大排序（字典序）；<br>
     * ◆ 如果参数的值为空不参与签名；<br>
     * ◆ 参数名区分大小写；<br>
     * ◆ 所有签名字符转换为大写<br>
     * @param map 请求参数
     * @return 返回签名大写
     * @throws Exception
     */
    public static String generateSign(Map<String, String> map) throws Exception {
        StringBuffer buffer = getSignBuffer(map);
        String stringSignTemp = buffer.toString();
        if(logger.isTraceEnabled()){
            logger.trace("***SignStr => " + stringSignTemp);
        }
        return MD5Util.md5UpperCase(stringSignTemp);
    }

    /**
     * 签名特别注意以下重要规则：<br>
     * ◆ 参数名ASCII码从小到大排序（字典序）；<br>
     * ◆ 如果参数的值为空不参与签名；<br>
     * ◆ 参数名区分大小写；<br>
     * ◆ 验证调用返回或微信主动通知签名时，传送的sign参数不参与签名，将生成的签名与该sign值作校验。<br>
     * ◆ 微信接口可能增加字段，验证签名时必须支持增加的扩展字段<br>
     * ◆ 所有签名字符转换为大写<br>
     * @param map 请求参数
     * @param appKey
     * @return 返回微信支付签名大写
     * @throws Exception
     */
    public static String generateWXSign(Map<String, String> map,String appKey) throws Exception {
        StringBuffer buffer = getSignBuffer(map);
        if(buffer.length() >= 1){
            buffer.append("&");
        }
        buffer.append("key=").append(appKey);
        String stringSignTemp = buffer.toString();
        if(logger.isTraceEnabled()){
            logger.trace("***SignStr => " + stringSignTemp);
        }
        return MD5Util.md5UpperCase(stringSignTemp);
    }

    /**
     * 签名特别注意以下重要规则：<br>
     * ◆ 参数名ASCII码从小到大排序（字典序）；<br>
     * ◆ 如果参数的值为空不参与签名；<br>
     * ◆ 参数名区分大小写；<br>
     * ◆ 所有签名字符转换为大写<br>
     * ◆ 签名字符串最后拼接appKey<br>
     * @param map 请求参数
     * @param appKey
     * @return
     * @throws Exception
     */
    public static String generateAppSign(Map<String, String> map,String appKey) throws Exception {
        StringBuffer buffer = getSignBuffer(map);
        if(buffer.length() >= 1){
            buffer.append("&");
        }
        buffer.append("appKey=").append(appKey);
        String stringSignTemp = buffer.toString();
        if(logger.isTraceEnabled()){
            logger.trace("***SignStr => " + stringSignTemp);
        }
        return MD5Util.md5UpperCase(stringSignTemp);
    }

    /**
     * 按KEY 从小到大排序(正序),拼接字符串，以&分隔
     * @param map 请求参数
     * @return
     */
    public static StringBuffer getSignBuffer(Map<String, String> map){
        StringBuffer buffer = new StringBuffer();
        Set<String> set = map.keySet();
        String[] strs = set.toArray(new String[set.size()]);
        Arrays.sort(strs);
        boolean first = true;
        for(int i = 0, len = strs.length; i < len; i++){
            String key = strs[i];
            String value = map.get(key);
            if(!("sign".equals(key)) || StringUtils.isNotEmpty(value)){
                if(first){
                    buffer.append(key).append("=").append(value);
                    first = false;
                }else{
                    buffer.append("&").append(key).append("=").append(value);
                }
            }
        }
        return buffer;
    }

}
