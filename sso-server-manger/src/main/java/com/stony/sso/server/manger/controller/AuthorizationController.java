package com.stony.sso.server.manger.controller;

import com.stony.sso.facade.entity.User;
import com.stony.sso.facade.entity.UserAppRole;
import com.stony.sso.facade.service.*;
import com.stony.sso.facade.view.MenusView;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/4/26 </p>
 * <p>Time: 16:53 </p>
 * <p>Version: 1.0 </p>
 * sys_user_app_role
 * 用户应用 角色授权
 */
@Controller
@RequestMapping("/authorization")
public class AuthorizationController {

    private  static  final  Logger logger = LoggerFactory.getLogger(AuthorizationController.class);

    @javax.annotation.Resource
    private AuthorizationService authorizationService;
    @javax.annotation.Resource
    private UserService userService;
    @javax.annotation.Resource
    private AppService appService;
    @javax.annotation.Resource
    private RoleService roleService;

    @RequestMapping(method = RequestMethod.GET)
    public MenusView list(Model model) {
        model.addAttribute("list", authorizationService.findAll());
        model.addAttribute("apps", appService.findAll());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", roleService.findAll());
        return new MenusView("authorization/list");
    }
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(@RequestParam(value = "user",required = true) Long user,
                         @RequestParam(value = "app",required = true) Long app,
                         @RequestParam(value = "roleIds",required = true) String roleIds) {
        logger.debug("Enter");
        Map<String,Object> map = new HashMap<>();
        map.put("count", authorizationService.createAuthorization(user, app, roleIds));
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(UserAppRole authorization) {
        logger.debug("Enter");
        List<UserAppRole> list = new ArrayList<>();
        list.add(authorization);
        Map<String,Object> map = new HashMap<>();
        map.put("count", authorizationService.deleteAuthorizations(list));
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(UserAppRole authorization) {
        logger.debug("Enter");
        authorizationService.updateAuthorization(authorization);
        return authorization;
    }
}
