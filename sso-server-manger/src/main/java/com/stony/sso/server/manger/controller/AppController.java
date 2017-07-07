package com.stony.sso.server.manger.controller;

import com.stony.sso.commons.DESUtils;
import com.stony.sso.commons.DateUtils;
import com.stony.sso.facade.entity.App;
import com.stony.sso.facade.service.AppService;
import com.stony.sso.facade.view.MenusView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/4/26 </p>
 * <p>Time: 16:49 </p>
 * <p>Version: 1.0 </p>
 */
@Controller
@RequestMapping("/app")
public class AppController {

    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    @Resource
    private AppService appService;

    @RequestMapping(method = RequestMethod.GET)
    public MenusView list(Model model) {
        logger.debug("Enter");
        model.addAttribute("list", appService.findAll());
        //blue,green yellow purple pink orange beige gray
        return new MenusView("app/list");
    }


    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(App app) {
        logger.debug("Enter");
        if(null != app.getId()){
            return update(app);
        }
        app.setAppKey(DESUtils.generateAppKey());
        app.setAppSecret(DESUtils.generateAppSecret());
        app.setInsertDate(DateUtils.now());
        return appService.createApp(app);
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(App app) {
        logger.debug("Enter");
        if(null == app.getId()){
            return create(app);
        }
        app.setUpdateDate(DateUtils.now());
        appService.updateApp(app);
        return app;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public Object delete(@PathVariable("id") Long id) {
        logger.debug("Enter");
        Map<String,Object> map = new HashMap<>();
        map.put("count",appService.deleteApp(id));
        return map;
    }
}
