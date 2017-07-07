package com.stony.sso.server.manger.controller;

import com.stony.sso.facade.entity.*;
import com.stony.sso.facade.service.*;
import com.stony.sso.facade.view.MenusView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/6/3 </p>
 * <p>Time: 11:57 </p>
 * <p>Version: 1.0 </p>
 */
@Controller
@RequestMapping("/icon")
public class IconController {
    private static final Logger logger = LoggerFactory.getLogger(IconController.class);

    @javax.annotation.Resource
    IconService iconService;


    @RequestMapping(method = {RequestMethod.GET})
    public MenusView index(Model model){
        logger.debug("Enter");
        model.addAttribute("list", iconService.findAll());
        return new MenusView("icon/list");
    }

    @RequestMapping(value = "/data", method = {RequestMethod.POST, RequestMethod.GET})
    public Object list(){
        logger.debug("Enter");
        List<Icon> all = iconService.findAll();
        return all;
    }
}
