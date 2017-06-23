package com.stony.sso.admin.monitor.controller;

import com.stony.sso.facade.view.MenusView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/4/29 </p>
 * <p>Time: 18:24 </p>
 * <p>Version: 1.0 </p>
 */
@Controller
@RequestMapping("")
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);


    @RequestMapping(value = {"/index","/"})
    public MenusView index(){
        logger.info("Enter");
        return new MenusView("index");
    }

    @RequestMapping(value = "/notFound")
    public String notFound(){
        logger.warn("Enter");
        return "error/404";
    }

    @RequestMapping(value = "/error")
    public String error(){
        logger.warn("Enter");
        return "error/500";
    }
}
