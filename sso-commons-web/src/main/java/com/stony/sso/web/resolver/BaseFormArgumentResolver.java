package com.stony.sso.web.resolver;

import com.stony.sso.cache.ticket.SessionUser;
import com.stony.sso.cache.ticket.TicketCacheManager;
import com.stony.sso.commons.JacksonUtil;
import com.stony.sso.commons.StringUtils;
import com.stony.sso.web.constants.HeaderConstant;
import com.stony.sso.web.form.BaseForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.MethodParameter;
import org.springframework.util.Assert;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/5/10 </p>
 * <p>Time: 11:47 </p>
 * <p>Version: 1.0 </p>
 * <pre class="code">
 *  自动绑定登陆数据到表单，配置到spring-mvc.xml,使用示例：
 *  &lt;mvc:annotation-driven &gt;
        &lt;mvc:argument-resolvers&gt;
            &lt;bean class="BaseFormArgumentResolver"&gt;
                &lt;property name="ticketCacheManager" ref="ticketCacheManager"/&gt;
            &lt;/bean&gt;
        &lt;/mvc:argument-resolvers&gt;
    &lt;/mvc:annotation-driven&gt;
 * </pre>
 */
public class BaseFormArgumentResolver extends AbstractValidateArgumentResolver implements InitializingBean {


    private static final Logger logger = LoggerFactory.getLogger(BaseFormArgumentResolver.class);

    TicketCacheManager ticketCacheManager;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return BaseForm.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object doResolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        logger.info("getParameterType = {}",parameter.getParameterType());
        SessionUser ticketValue = getSessionUser(webRequest);
        if(logger.isTraceEnabled()){
            StringBuilder sb = new StringBuilder("Resolve [");
            sb.append(parameter.getMethod().getName())
                    .append("{ index = ").append(parameter.getParameterIndex())
                    .append(" , name = ").append(parameter.getParameterName())
                    .append(" , type = ").append(parameter.getParameterType())
                    .append(" , annotations = ").append(Arrays.asList(parameter.getParameterAnnotations()))
                    .append(" }")
                    .append("]");
            logger.trace(sb.toString());
        }
        Object arg = BeanUtils.instantiate(parameter.getParameterType());
        ServletRequestDataBinder dataBinder = new ServletRequestDataBinder(arg);
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        dataBinder.bind(servletRequest);
        if(ticketValue != null){
            if(arg instanceof  BaseForm){
                ((BaseForm) arg).setUserId(ticketValue.getUserId());
                ((BaseForm) arg).setName(ticketValue.getUserName());
                ((BaseForm) arg).setTicket(ticketValue.getTicket());
                ((BaseForm) arg).setAccount(ticketValue.getAccount());
                ((BaseForm) arg).setAppKey(ticketValue.getAppKey());
                ((BaseForm) arg).setAttributionSite(ticketValue.getGasStationId());
            }
        }
        logger.info("arg = {}", JacksonUtil.write2JsonStr(arg));
        return arg;
    }

    protected SessionUser getSessionUser(NativeWebRequest webRequest){
        String ticket = webRequest.getHeader(HeaderConstant.HEADER_TICKET);
        logger.debug("ticket = {}", ticket);
        SessionUser ticketValue = null;
        if(StringUtils.isNotEmpty(ticket)){
            ticketValue = getTicketCacheManager().getTicketCache().getTicketValue(ticket);
        }
        if(null == ticketValue){
            ticket = webRequest.getHeader(HeaderConstant.HEADER_TOKEN);
            ticketValue = getTicketCacheManager().getTicketCache().getTicketValue(ticket);
        }
        if(null == ticketValue){
            logger.warn("ticketValue is null, ticket = {}", ticket);
        }else{
            logger.debug("ticketValue = {}", ticketValue);
        }
        return ticketValue;
    }

    public TicketCacheManager getTicketCacheManager() {
        return ticketCacheManager;
    }

    public void setTicketCacheManager(TicketCacheManager ticketCacheManager) {
        this.ticketCacheManager = ticketCacheManager;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(this.ticketCacheManager, "property ticketCacheManager is required.");
    }
}
