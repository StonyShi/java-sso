package com.stony.sso.server.filter;

import com.stony.sso.commons.StringUtils;
import com.stony.sso.commons.security.SecurityConstants;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/8/1 </p>
 * <p>Time: 17:34 </p>
 * <p>Version: 1.0 </p>
 */
public class ServerLogoutFilter extends LogoutFilter {

    private static final Logger logger = LoggerFactory.getLogger(ServerLogoutFilter.class);

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        logger.debug("Enter");
        //添加客户端退出，带回掉地址PARAMETER_BACKURL
        String backUrl = WebUtils.getCleanParam(request, SecurityConstants.PARAMETER_BACKURL);
        if (StringUtils.isNotEmpty(backUrl)) {
            Subject subject = getSubject(request, response);
            Map<String,String> map = new HashMap<>();
            try {
                subject.logout();
                map.put(SecurityConstants.PARAMETER_BACKURL_OK, SecurityConstants.PARAMETER_BACKURL_OK_VALUE);
            } catch (SessionException ise) {
                logger.debug("Encountered session exception during logout.  This can generally safely be ignored.", ise);
                map.put(SecurityConstants.PARAMETER_BACKURL_OK, "0");
                map.put(SecurityConstants.PARAMETER_BACKURL_MSG, ise.getMessage());
            }
            WebUtils.issueRedirect(request, response, backUrl, map);
            return false;
        }
        return super.preHandle(request, response);
    }
}
