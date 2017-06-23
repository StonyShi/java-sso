package com.stony.sso.server.filter;

import com.stony.sso.commons.StringUtils;
import com.stony.sso.facade.service.VerificationService;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/6/16 </p>
 * <p>Time: 13:45 </p>
 * <p>Version: 1.0 </p>
 * 验证码过滤器
 */
public class VerificationFilter extends AccessControlFilter {
    private static final Logger logger = LoggerFactory.getLogger(VerificationFilter.class);

    /**
     * 是否开启验证码支持
     */
    private boolean VerificationEnabled = true;
    private String failureKeyAttribute = FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME;
    private String verifyParam = "verifyCode"; //前台提交的验证码参数名
    private String usernameParam = "username"; //用户名称
    private VerificationService verificationService;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        //1、设置验证码是否开启属性，页面可以根据该属性来决定是否显示验证码
        request.setAttribute("VerificationEnabled", VerificationEnabled);
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        //2、判断验证码是否禁用 或不是表单提交（允许访问）
        if (!VerificationEnabled || !POST_METHOD.equalsIgnoreCase(httpServletRequest.getMethod())) {
            return true;
        }
        String username = getUsername(request);
        logger.info("username = {}", getUsername(request));
        String verifyCode = getVerifyCode(request);
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(verifyCode)){
            return false;
        }
        return verificationService.verify(username, verifyCode);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        logger.info("username = {}", getUsername(request));
        request.setAttribute(failureKeyAttribute, "VerifyError");
        return true;
    }

    public void setVerificationEnabled(boolean verificationEnabled) {
        VerificationEnabled = verificationEnabled;
    }

    public void setUsernameParam(String usernameParam) {
        this.usernameParam = usernameParam;
    }

    public void setVerifyParam(String verifyParam) {
        this.verifyParam = verifyParam;
    }

    public String getVerifyParam() {
        return verifyParam;
    }

    public String getUsernameParam() {
        return usernameParam;
    }

    public void setVerificationService(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    protected String getUsername(ServletRequest request) {
        return WebUtils.getCleanParam(request, getUsernameParam());
    }
    protected String getVerifyCode(ServletRequest request){
        return WebUtils.getCleanParam(request, getVerifyParam());
    }
}
