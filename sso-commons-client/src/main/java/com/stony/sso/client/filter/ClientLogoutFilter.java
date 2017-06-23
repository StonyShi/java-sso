package com.stony.sso.client.filter;

import com.stony.sso.commons.StringUtils;
import com.stony.sso.commons.security.SecurityConstants;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/6/13 </p>
 * <p>Time: 18:14 </p>
 * <p>Version: 1.0 </p>
 */
public class ClientLogoutFilter extends LogoutFilter {

    private static final Logger logger = LoggerFactory.getLogger(ClientLogoutFilter.class);
    //client.logout.url
    private String logoutUrl;

//    RestTemplate restTemplate = new RestTemplate();

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        String backUrlOk = WebUtils.getCleanParam(request, SecurityConstants.PARAMETER_BACKURL_OK);
        logger.info("User[{}] logout server to {}, back_ok = {}.", subject.getPrincipal(), logoutUrl, backUrlOk);
        //客户端进入退出
        if(StringUtils.isEmpty(backUrlOk)){
            Map<String,String> map = new HashMap<>();
            map.put(SecurityConstants.PARAMETER_BACKURL, getDefaultBackUrl(WebUtils.toHttp(request)));
            logger.info("back map = {}", map);
            WebUtils.issueRedirect(request, response, logoutUrl, map);
            return false;
        }
        //服务器带PARAMETER_BACKURL_OK返回退出
        if(StringUtils.isNotEmpty(backUrlOk)){
            if(!SecurityConstants.PARAMETER_BACKURL_OK_VALUE.equals(backUrlOk)){
                logger.warn("服务器退出异常 ：{}", WebUtils.getCleanParam(request, SecurityConstants.PARAMETER_BACKURL_MSG));
            }
        }
        //执行 subject.logout 后重定向到首页
        return super.preHandle(request, response);
    }

    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }

    private String getDefaultBackUrl(HttpServletRequest request) {
        String scheme = request.getScheme();
        String domain = request.getServerName();
        int port = request.getServerPort();
        String contextPath = request.getContextPath();
        StringBuilder backUrl = new StringBuilder(scheme);
        backUrl.append("://");
        backUrl.append(domain);
        if("http".equalsIgnoreCase(scheme) && port != 80) {
            backUrl.append(":").append(String.valueOf(port));
        } else if("https".equalsIgnoreCase(scheme) && port != 443) {
            backUrl.append(":").append(String.valueOf(port));
        }
        backUrl.append(contextPath);
        if(backUrl.toString().endsWith("/")){
            backUrl.append("logout");
        }else{
            backUrl.append("/logout");
        }
        return backUrl.toString();
    }
}
