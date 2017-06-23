package com.stony.sso.client.session;


import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/4/29 </p>
 * <p>Time: 19:53 </p>
 * <p>Version: 1.0 </p>
 */
public class ClientWebSessionManager extends DefaultWebSessionManager {


    private static final Logger logger = LoggerFactory.getLogger(ClientWebSessionManager.class);

    @Override
    protected Session doCreateSession(SessionContext context) {
        ServletRequest request = WebUtils.getRequest(context);
        ServletResponse response = WebUtils.getResponse(context);
        Serializable sessionId = getSessionId(request,response);
        if(sessionId != null){
            logger.debug("sessionId = {}", sessionId);
            Session session = getSessionDAO().readSession(sessionId);
            if(null != session){
                return session;
            }
        }
        return super.doCreateSession(context);
    }

    @Override
    protected Session createExposedSession(Session session, SessionContext context) {
        logger.debug("sessionId = {}", session.getId());
        return super.createExposedSession(session, context);
    }

    @Override
    protected Session createExposedSession(Session session, SessionKey key) {
        logger.debug("sessionId = {}", session.getId());
        return super.createExposedSession(session, key);
    }
}
