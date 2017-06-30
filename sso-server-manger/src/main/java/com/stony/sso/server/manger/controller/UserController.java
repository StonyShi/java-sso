package com.stony.sso.server.manger.controller;

import com.stony.sso.commons.DateUtils;
import com.stony.sso.facade.entity.User;
import com.stony.sso.facade.service.UserService;
import com.stony.sso.facade.view.MenusView;
import com.stony.sso.commons.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/4/26 </p>
 * <p>Time: 16:57 </p>
 * <p>Version: 1.0 </p>
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @javax.annotation.Resource
    private UserService userService;

    /**
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public MenusView list(ModelMap model) {
        logger.debug("Enter");
        User user = userService.findByUsername((String) SecurityUtils.getSubject().getPrincipal());
        Integer userType = -1;
        if (user.getUserType() != null) userType = user.getUserType();
        model.put("users", userService.findAll());
        return new MenusView("user/list");
    }

    /**
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(User user) {
        logger.debug("Enter", user);
        if (null != user.getId()) {
            return update(user);
        }
        user.setInsertDate(DateUtils.now());
        return userService.createUser(user);
    }

    /**
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(User user) {
        logger.debug("Enter", user);
        if (null == user.getId()) {
            return create(user);
        }
        if (StringUtils.isEmpty(user.getSea())) {
            user.setSea(null);
            user.setPassword(null);
        }
        user.setUpdateDate(DateUtils.now());
        return userService.updateUser(user);
    }
}
