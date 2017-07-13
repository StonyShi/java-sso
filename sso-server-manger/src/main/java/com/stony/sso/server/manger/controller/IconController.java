package com.stony.sso.server.manger.controller;

import com.github.pagehelper.Page;
import com.stony.sso.facade.entity.*;
import com.stony.sso.facade.service.*;
import com.stony.sso.facade.view.MenusView;
import com.stony.sso.web.form.BaseForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping("/icon")
public class IconController {
    private static final Logger logger = LoggerFactory.getLogger(IconController.class);

    @javax.annotation.Resource
    IconService iconService;


    @RequestMapping(method = {RequestMethod.GET})
    public MenusView index(Model model){
        logger.debug("Enter");
//        model.addAttribute("list", iconService.findAll());
        return new MenusView("icon/list");
    }

    @ResponseBody
    @RequestMapping(value = "/all", method = {RequestMethod.POST})
    public Object list(){
        logger.debug("Enter");
        List<Icon> all = iconService.findAll();
        return all;
    }

    @ResponseBody
    @RequestMapping(value = "data", method = RequestMethod.POST)
    public Map<String, Object> data(BaseForm form) {
        logger.debug("Enter {}", form);
        Icon icon = new Icon();
        icon.setPageNum(form.getPageNum());
        icon.setPageSize(form.getPageSize());
        Page<Icon> icons = iconService.findIconList(icon);
        Map<String, Object> map = new HashMap<>();
        map.put("sEcho", form.getEcho());
        //总行数
        map.put("iTotalRecords", icons.getTotal());
        //显示的行数,和上面一致
        map.put("iTotalDisplayRecords", icons.getTotal());
        map.put("aaData", icons);
        return map;
    }
}
