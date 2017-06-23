package com.stony.sso.server.filter;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/4/26 </p>
 * <p>Time: 16:57 </p>
 * <p>Version: 1.0 </p>
 */
public class ServerFormAuthenticationFilter extends FormAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(ServerFormAuthenticationFilter.class);

//    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
//        Session session = getSubject(request, response).getSession();
//        String fallbackUrl = (String) session.getAttribute(SecurityConstants.ATTR_AUTHC_FALLBACK_URL);
//        logger.info("session = {}, fallbackUrl = {}", session.getId(), fallbackUrl);
//        if(StringUtils.isEmpty(fallbackUrl)) {
//            fallbackUrl = WebUtils.getCleanParam(request, SecurityConstants.PARAMETER_BACKURL);
//            logger.info("parameter fallbackUrl = {}", fallbackUrl);
//            if(StringUtils.isEmpty(fallbackUrl)) {
//                fallbackUrl = getSuccessUrl();
//                logger.info("success fallbackUrl = {}", fallbackUrl);
//                WebUtils.redirectToSavedRequest(request, response, fallbackUrl);
//            }else{
//                WebUtils.issueRedirect(request, response, fallbackUrl);
//            }
//        }
//        WebUtils.redirectToSavedRequest(request, response, (fallbackUrl + "?sid="+session.getId()));
//    }


//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//        logger.info("username = {}", getUsername(request));
//        if(request.getAttribute(getFailureKeyAttribute()) != null) {
//            return true;
//        }
//        return super.onAccessDenied(request, response);
//    }
}
