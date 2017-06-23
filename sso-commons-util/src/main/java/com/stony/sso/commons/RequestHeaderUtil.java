package com.stony.sso.commons;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/5/12 </p>
 * <p>Time: 13:51 </p>
 * <p>Version: 1.0 </p>
 */
public abstract class RequestHeaderUtil {

    /**
     * 将Request Header 转换为Map
     * @param request
     * @return
     */
    public static Map<String,String> getHeaderMap(HttpServletRequest request){
        Map<String, String> result = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        for (Enumeration enumeration = headerNames; enumeration.hasMoreElements(); ) {
            String name = enumeration.nextElement().toString();
            String value = request.getHeader(name);
            result.put(name, value);
        }
        return result;
    }
}
