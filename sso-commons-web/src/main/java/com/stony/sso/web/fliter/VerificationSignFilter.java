package com.stony.sso.web.fliter;

import com.stony.sso.commons.DateUtils;
import com.stony.sso.web.constants.HeaderConstant;
import com.stony.sso.web.entity.ResponseEntity;
import com.stony.sso.web.constants.ResponseConstant;
import com.stony.sso.web.verif.VerificationSign;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Stony Created Date : 2016/4/21  12:19
 */
public class VerificationSignFilter extends BaseDelegatingFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(VerificationSignFilter.class);
    private final static String FILTER_APPLIED = VerificationSignFilter.class.getName().concat(".APPLIED");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("Enter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.debug("Enter");
        doOnceFilter(servletRequest,servletResponse,filterChain);
    }

    @Override
    protected void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        logger.info("Filter URI[{}]", uri);
        if(uri.equals("/") || excludesUrl(uri)){
            logger.debug("exclude[{}]",uri);
        }else{
            if(includesUrl(uri)){
                VerificationSign vs = new VerificationSign(req);
                String sign = req.getHeader(HeaderConstant.HEADER_SIGN);
                String appKey = req.getHeader(HeaderConstant.HEADER_APPKEY);
                //签名为空，或者签名不一致返回
                //return json {data : {}, responseCode : 100002, responseMessage : "请求签名无效", responseDate : "2016-04-21 12:24:30"}
                if(StringUtils.isEmpty(sign) || (!vs.verify(sign, appKey))){
                    res.setCharacterEncoding(HeaderConstant.CONTEN_TYPE_CHARSET);
                    res.setContentType(HeaderConstant.CONTEN_TYPE_JSON);
                    ResponseEntity<Map> responseEntity = new ResponseEntity<Map>();
                    responseEntity.setData(new HashMap());
                    responseEntity.setResponseCode(ResponseConstant.CODE_SIGN_INVALID);
                    responseEntity.setResponseMessage(ResponseConstant.MSG_SIGN_INVALID);
                    responseEntity.setResponseDate(DateUtils.now());
                    res.getOutputStream().write(responseEntity.toJsonString().getBytes(HeaderConstant.CONTEN_TYPE_CHARSET));
                    return;
                }
            }
        }
        chain.doFilter(request,response);
    }

    @Override
    public String getFilterApplied() {
        return FILTER_APPLIED;
    }
    @Override
    public void destroy() {
        logger.debug("Enter");
    }
}
