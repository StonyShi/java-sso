package com.stony.sso.web.fliter;

import com.stony.sso.commons.CookieUtils;
import com.stony.sso.commons.StringUtils;
import com.stony.sso.web.constants.HeaderConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by fly on 2016/8/31.
 */
public class WebTicketDelegatingFilter extends TicketDelegatingFilter{

    String loginUrl = "/";

    @Override
    protected String getTicket(HttpServletRequest req) {
        String ticket = super.getTicket(req);
        return StringUtils.isEmpty(ticket) ? CookieUtils.getCookieValue(req, HeaderConstant.COOKIE_TICKET) : ticket;
    }

    @Override
    protected void onAccessDenied(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.sendRedirect(response.encodeRedirectURL(loginUrl));
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }
}
