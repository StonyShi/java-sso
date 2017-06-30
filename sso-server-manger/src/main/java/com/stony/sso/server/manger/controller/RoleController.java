package com.stony.sso.server.manger.controller;

import com.stony.sso.commons.DateUtils;
import com.stony.sso.facade.entity.Resource;
import com.stony.sso.facade.entity.Role;
import com.stony.sso.facade.entity.User;
import com.stony.sso.facade.service.*;
import com.stony.sso.facade.view.MenusView;
import com.stony.sso.web.constants.ResponseConstant;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/4/26 </p>
 * <p>Time: 16:56 </p>
 * <p>Version: 1.0 </p>
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @javax.annotation.Resource
    private RoleService roleService;

    @javax.annotation.Resource
    private ResourceService resourceService;

    @javax.annotation.Resource
    private UserService userService;

    /**
     * 角色列表
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public MenusView list(Model model) {
        logger.debug("Enter");
        model.addAttribute("list", roleService.findAll());
        return new MenusView("role/list");
    }

    /**
     * 创建或更新
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object create(Role role) {
        logger.debug("Enter");
        if(null != role.getId()){
            return update(role);
        }
        role.setInsertDate(DateUtils.now());
        return roleService.createRole(role);
    }
    /**
     * 更新 或者创建
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(Role role) {
        logger.debug("Enter");
        if(null == role.getId()){
            return create(role);
        }
        role.setUpdateDate(DateUtils.now());
        roleService.updateRole(role);
        return role;
    }
    /**
     * 角色资源
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{id}/resources", method = RequestMethod.POST)
    public Object roleResources(@PathVariable("id") Long id) {
        logger.debug("Enter");
        /*获取当前登录人信息*/
        User user = userService.findByUsername((String) SecurityUtils.getSubject().getPrincipal());
        List<Resource> resources = resourceService.findAllUser(user.getUserType());
        List<Resource> roleResources = resourceService.findResourcesByRoleIds(String.valueOf(id));
        List<Long> roleResourceIds = new ArrayList<>();
        for (Resource re : roleResources) {
            roleResourceIds.add(re.getId());
        }
        List<Resource> result = new ArrayList<>();
        for (Resource resource : resources) {
            if (roleResourceIds.contains(resource.getId())) {
                resource.setHasRole(true);
            }
            result.add(resource);
        }
        logger.debug("result = {}", result);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object delete(@RequestParam("ids") String ids) {
        logger.info("Delete ids = {}", ids);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 1);
        map.put("count", roleService.deleteRoles(ids));
        return map;
    }

    @ResponseBody
    @RequestMapping(value = "/disable", method = RequestMethod.POST)
    public Object disable(@RequestParam("ids") String ids) {
        logger.info("Disable ids = {}", ids);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 1);
        map.put("count", roleService.deleteRoles(ids));
        return map;
    }


    @ResponseBody
    @RequestMapping(value = "/{id}/update/resources", method = RequestMethod.POST)
    public Object updateRoleResources(@PathVariable("id") Long id, @RequestParam(value = "resourceIds", required = true) String resourceIds){
        logger.debug("Enter");
        Map<String,Integer> map = new HashMap<>();
        map.put("count", resourceService.updateRoleResources(id, resourceIds));
        map.put("responseCode", ResponseConstant.CODE_SUCUESS);
        return map;
    }


}
