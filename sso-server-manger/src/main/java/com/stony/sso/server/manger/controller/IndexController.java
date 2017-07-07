package com.stony.sso.server.manger.controller;

import com.stony.sso.commons.DateUtils;
import com.stony.sso.commons.security.SecurityConstants;
import com.stony.sso.facade.entity.*;
import com.stony.sso.facade.service.*;
import com.stony.sso.facade.view.MenusView;
import com.stony.sso.commons.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/6/3 </p>
 * <p>Time: 11:57 </p>
 * <p>Version: 1.0 </p>
 */
@Controller
@RequestMapping("")
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Resource
    private AppService appService;

    @Resource
    private AuthorizationService authorizationService;

    @Resource
    VerificationService verificationService;

    @Resource
    GlobalVariableService globalVariableService;

    @RequestMapping(value = {"/index","/"})
    public MenusView index(Model model) {
        model.addAttribute("v","2");
        model.addAttribute("list", appService.findAll());
        return new MenusView("index");
    }

    @RequestMapping(value = "/notFound")
    public String notFound(){
        return "error/404";
    }

    @RequestMapping(value = "/error")
    public String error(){
        return "error/500";
    }


    @RequestMapping("/unauthorized")
    public MenusView unauthorized(){
        logger.debug("Enter");
        return new MenusView("error/unauthorized");
    }

    @RequestMapping(value = "/login"    )
    public String showLoginForm(HttpServletRequest req, Model model) {
        if(SecurityUtils.getSubject().isAuthenticated()){
            return  ("redirect:/index");
        }
        String exceptionClassName = (String)req.getAttribute("shiroLoginFailure");
        String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        model.addAttribute("error", error);
        String backUrl =  req.getParameter(SecurityConstants.PARAMETER_BACKURL);
        model.addAttribute(SecurityConstants.PARAMETER_BACKURL, backUrl);
        logger.info("backUrl = ",backUrl);
        return "login2";
    }

    @RequestMapping(value = "/cron")
    public String cron(){
        logger.debug("Enter");
        return "cron/index";
    }

    @ResponseBody
    @RequestMapping(value = "/cron/runtime", method = {RequestMethod.POST})
    public Object cronRunTime(Map parameters){
        logger.info("parameters : ", parameters);
        List<Object> list = new ArrayList<>();
        list.add(parameters.get("expression"));
        return list;
    }
    @RequestMapping(value = "/register" ,method = {RequestMethod.GET})
    public String register(){
        return "register";
    }

    @ResponseBody
    @RequestMapping(value = "/send/sms/{username}", method = {RequestMethod.POST})
    public Object sendSms(@PathVariable(value = "username") String username){
        Map<String,String> map = new HashMap<>();
        if(StringUtils.isEmpty(username)){
            map.put("code", "0");
            map.put("msg","用户名不能为空");
            return map;
        }
        boolean result = verificationService.sendSms(username);
        if(result){
            map.put("code", "1");
            map.put("msg","短信成功下发到您的手机号");
            return map;
        }else{
            map.put("code", "-1");
            map.put("msg","下发短信失败，请稍后再试");
            return map;
        }
    }

    @RequestMapping(value = "/global/variable", method = RequestMethod.GET)
    public MenusView globalVariable(Model model){
        logger.debug("Enter");
        model.addAttribute("list", globalVariableService.findAll());
        logger.info("model = {}", model);
        return new MenusView("global/variable");
    }

    @ResponseBody
    @RequestMapping(value = "/update/global/variable", method = {RequestMethod.POST,RequestMethod.PUT})
    public Object updateGlobalVariable(GlobalVariable globalVariable) {
        logger.debug("Enter");
        if (null == globalVariable.getId()) {
            globalVariable.setInsertDate(DateUtils.now());
            return globalVariableService.createVariable(globalVariable);
        }
        return globalVariableService.updateVariable(globalVariable);
    }
    @ResponseBody
    @RequestMapping(value = "/del/global/variable/{id}", method = {RequestMethod.POST,RequestMethod.DELETE})
    public Object delGlobalVariable(@PathVariable(value = "id") long id) {
        logger.debug("Enter");
        Map<String,Object> map = new HashMap<>();
        map.put("count",globalVariableService.delVariable(id));
        return map;
    }
}
