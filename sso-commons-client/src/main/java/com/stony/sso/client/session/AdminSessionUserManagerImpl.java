package com.stony.sso.client.session;

import com.stony.sso.cache.ticket.SessionUser;
import com.stony.sso.commons.DateUtils;
import com.stony.sso.commons.security.AdminSessionUserManager;
import com.stony.sso.facade.context.PermissionContext;
import com.stony.sso.facade.entity.User;
import com.stony.sso.facade.service.UserService;
import com.stony.sso.client.ClientInfoHold;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/6/12 </p>
 * <p>Time: 11:01 </p>
 * <p>Version: 1.0 </p>
 */
public class AdminSessionUserManagerImpl implements AdminSessionUserManager {


    @Override
    public SessionUser getSessionUser(String username) {
        Subject subject = SecurityUtils.getSubject();
        PermissionContext context = (PermissionContext) subject.getPrincipal();
        User user = context.getUser();
        if(user == null){
            return null;
        }
        Session session = subject.getSession();
        String ticket = "";
        if(null != session){
            ticket = String.valueOf(session.getId());
        }
        return new SessionUser(user.getId() ,
                            user.getUsername(),
                            user.getPhone(),
                            DateUtils.dateTimeString(),
                            ticket,
                            user.getGasStationId(),
                            ClientInfoHold.APP_KEY);
    }
}
