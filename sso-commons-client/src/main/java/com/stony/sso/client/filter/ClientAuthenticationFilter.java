package com.stony.sso.client.filter;


import com.stony.sso.client.ClientInfoHold;
import com.stony.sso.commons.StringUtils;
import com.stony.sso.commons.security.SecurityConstants;
import com.stony.sso.commons.security.request.ClientSavedRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Created with car-commons-security.</p>
 * <p>User: Stony</p>
 * <p>Date: 2016/4/25</p>
 * <p>Time: 18:30</p>
 */
public class ClientAuthenticationFilter extends AuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(ClientAuthenticationFilter.class);

    String redirectUrl;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        logger.info("Enter");
        Subject subject = getSubject(request, response);
        return subject.isAuthenticated();
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        logger.info("Enter");
//        String backUrl = request.getParameter(SecurityConstants.PARAMETER_BACKURL);
        String backUrl = getDefaultBackUrl(WebUtils.toHttp(request));
//        if(StringUtils.isEmpty(backUrl)){
//            backUrl = fallbackUrl;
//        }
        saveRequest(request, backUrl, backUrl);
        redirectToLogin(request, response, backUrl);
        return false;
    }

    protected void redirectToLogin(ServletRequest request, ServletResponse response,String backUrl) throws IOException {
        logger.info("Enter");
        String loginUrl = this.getLoginUrl();
        Map<String,String> map = new HashMap<>();
        Session session = SecurityUtils.getSubject().getSession();
        //http://localhost:8081/authorize?client_id=c1ebe466-1cdc-4bd3-ab69-77c3561b9dee&amp;response_type=code&amp;redirect_uri=http://localhost:8082/oauth2-login

//        map.put(SecurityConstants.PARAMETER_BACKURL, backUrl);

        map.put(SecurityConstants.CLIENT_ID, ClientInfoHold.APP_KEY);
        map.put(SecurityConstants.RESPONSE_TYPE, SecurityConstants.RESPONSE_TYPE_VALUE);
        map.put(SecurityConstants.REDIRECT_URI, backUrl);
        logger.info("redirect map = {}", map);
        WebUtils.issueRedirect(request, response, loginUrl, map);
    }

    protected void saveRequest(ServletRequest request, String backUrl, String fallbackUrl) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        session.setAttribute(SecurityConstants.ATTR_AUTHC_FALLBACK_URL, fallbackUrl);
        SavedRequest savedRequest = new ClientSavedRequest(httpRequest, backUrl);
        session.setAttribute(WebUtils.SAVED_REQUEST_KEY, savedRequest);
        logger.info("session = [{},{}], backUrl = {}", session.getId(), session.getHost(), backUrl);
//        logger.info("session = [{},{}], fallbackUrl = {}", session.getId(), session.getHost(), fallbackUrl);
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
        backUrl.append(getRedirectUrl());
        ClientInfoHold.setLoginRedirectUrl(backUrl.toString());
        return backUrl.toString();
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }
}
