package com.stony.sso.server.manger.controller;

import com.stony.sso.commons.DateUtils;
import com.stony.sso.facade.entity.Resource;
import com.stony.sso.facade.enums.ResourceTypeEnum;
import com.stony.sso.facade.service.*;
import com.stony.sso.facade.view.MenusView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/4/26 </p>
 * <p>Time: 16:56 </p>
 * <p>Version: 1.0 </p>
 */
@Controller
@RequestMapping("/resource")
public class ResourceController {
    private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @javax.annotation.Resource
    private ResourceService resourceService;
    @javax.annotation.Resource
    IconService iconService;

    @ModelAttribute("types")
    public ResourceTypeEnum[] resourceTypes() {
        return ResourceTypeEnum.values();
    }

    @RequestMapping(method = RequestMethod.GET)
    public MenusView list(Model model) {
        logger.debug("Enter");
        List<Resource> list = resourceService.findAll();
        Collections.sort(list, new Comparator<Resource>() {
            @Override
            public int compare(Resource o1, Resource o2) {
                return o1.getId().compareTo(o2.getId());
            }
        });
        model.addAttribute("list", list);
        model.addAttribute("icons", iconService.findAll());
        return new MenusView("resource/list");
    }


    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(Resource resource) {
        logger.debug("Enter");
        if(null != resource.getId()){
            return update(resource);
        }
        resource.setInsertDate(DateUtils.now());
        return resourceService.createResource(resource);
    }
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(Resource resource) {
        logger.debug("Enter");
        if(null == resource.getId()){
            return create(resource);
        }
        resource.setUpdateDate(DateUtils.now());
        resourceService.updateResource(resource);
        return resource;
    }
}
