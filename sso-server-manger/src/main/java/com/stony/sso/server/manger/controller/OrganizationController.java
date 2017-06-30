package com.stony.sso.server.manger.controller;

import com.stony.sso.commons.DateUtils;
import com.stony.sso.facade.entity.*;
import com.stony.sso.facade.service.*;
import com.stony.sso.facade.util.TreeUtil;
import com.stony.sso.facade.view.MenusView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/4/26 </p>
 * <p>Time: 16:55 </p>
 * <p>Version: 1.0 </p>
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController {

    private static final Logger logger = LoggerFactory.getLogger(OrganizationController.class);


    @javax.annotation.Resource
    private OrganizationService organizationService;

    @RequestMapping(method = RequestMethod.GET)
    public MenusView index(Model model) {
        logger.debug("Enter");
        List<Organization> list = organizationService.findAll();
        model.addAttribute("list", list);
        model.addAttribute("treeOrgan", TreeUtil.treeOrganization(list));
        return new MenusView("organization/index");
    }

    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public String showTree(Model model) {
        model.addAttribute("organizationList", organizationService.findAll());
        return "organization/tree";
    }

    @RequestMapping(value = "/appendChild/{parentId}", method = RequestMethod.GET)
    public String showAppendChildForm(@PathVariable("parentId") Long parentId, Model model) {
        Organization parent = organizationService.findOne(parentId);
        model.addAttribute("parent", parent);
        Organization child = new Organization();
        child.setParentId(parentId);
        model.addAttribute("child", child);
        model.addAttribute("op", "新增");
        return "organization/appendChild";
    }

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(Organization organization) {
        logger.debug("Enter");
        if(null != organization.getId()){
            return update(organization);
        }
        organization.setInsertDate(DateUtils.now());
        return organizationService.createOrganization(organization);
    }
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(Organization organization) {
        logger.debug("Enter");
        if(null == organization.getId()){
            return create(organization);
        }
        organization.setUpdateDate(DateUtils.now());
        organizationService.updateOrganization(organization);
        return organization;
    }

    @RequestMapping(value = "/maintain/{id}", method = RequestMethod.GET)
    public String showMaintainForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("organization", organizationService.findOne(id));
        return "organization/maintain";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public Object delete(@PathVariable("id") Long id) {
        logger.debug("Enter");
        Map<String,Object> map = new HashMap<>();
        map.put("count", organizationService.deleteOrganization(id));
        return map;
    }


}
