package com.stony.sso.web.resolver;

import com.stony.sso.cache.ticket.SessionUser;
import com.stony.sso.commons.security.AdminSessionUserManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.context.request.NativeWebRequest;


/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/6/12 </p>
 * <p>Time: 10:39 </p>
 * <p>Version: 1.0 </p>
 * <pre class="code">
 *  自动绑定登陆数据到表单，配置到spring-mvc.xml,使用示例：
 *   &lt;mvc:annotation-driven &gt;
        &lt;mvc:argument-resolvers&gt;
            &lt;bean class="AdminBaseFormArgumentResolver"&gt;
                &lt;property name="ticketCacheManager" ref="ticketCacheManager"/&gt;
            &lt;/bean&gt;
        &lt;/mvc:argument-resolvers&gt;
     &lt;/mvc:annotation-driven&gt;
 * </pre>
 */
public class AdminBaseFormArgumentResolver extends BaseFormArgumentResolver {

    private static final Logger logger = LoggerFactory.getLogger(AdminBaseFormArgumentResolver.class);

    AdminSessionUserManager adminSessionUserManager;

    @Override
    protected SessionUser getSessionUser(NativeWebRequest webRequest) {
        if(SecurityUtils.getSubject().isAuthenticated()){
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            logger.debug("username = {}", username);
            return adminSessionUserManager.getSessionUser(username);
        }
        throw new UnauthenticatedException("当前用户未登录");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.adminSessionUserManager, "property adminSessionUserManager is required.");
    }

    public void setAdminSessionUserManager(AdminSessionUserManager adminSessionUserManager) {
        this.adminSessionUserManager = adminSessionUserManager;
    }
}
