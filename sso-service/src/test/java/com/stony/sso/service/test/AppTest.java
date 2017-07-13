package com.stony.sso.service.test;


import com.stony.sso.cache.redis.JedisTemplate;
import com.stony.sso.commons.JacksonUtil;
import com.stony.sso.facade.entity.*;
import com.stony.sso.facade.enums.ResourceType;
import com.stony.sso.facade.service.*;
import com.stony.sso.service.helper.PasswordHelper;
import com.stony.sso.service.mapper.AppMapper;
import com.stony.sso.service.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/4/26 </p>
 * <p>Time: 20:17 </p>
 * <p>Version: 1.0 </p>
 */
//@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-context.xml")
@TransactionConfiguration(transactionManager = "dataSourceTransactionManager")
public class AppTest {

    private static final Logger logger = LoggerFactory.getLogger(AppTest.class);


    @javax.annotation.Resource
    public JedisTemplate jedisTemplate;

    @javax.annotation.Resource
    UserMapper userMapper;

    @javax.annotation.Resource
    AppMapper appMapper;

    @javax.annotation.Resource
    AppService appService;

    @javax.annotation.Resource
    AuthorizationService authorizationService;

    @javax.annotation.Resource
    OrganizationService organizationService;

    @javax.annotation.Resource
    PermissionService permissionService;

    @javax.annotation.Resource
    ResourceService resourceService;

    @javax.annotation.Resource
    RoleService roleService;

    @javax.annotation.Resource
    UserService userService;

    //@Ignore
    @Rollback(false)
    @Test
    @Transactional(propagation = Propagation.SUPPORTS)
    public void testFindMenus(){
        logger.info("resourceService.findMenuAll = {}", resourceService.findMenuAll());
    }

    //@Ignore
    @Rollback
    @Test
    @Transactional(propagation = Propagation.SUPPORTS)
    public void test(){
        logger.info("userMapper.findAll = {}", userMapper.findAll());

        logger.info("appMapper.findAll = {}", appMapper.findAll());
    }
    //@Ignore
    @Rollback(true)
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = RuntimeException.class)
    @Test
    public void testAppServiceCache(){
        logger.info("appService.findAll = {}", appService.findAll());
        logger.info("appService.findAll = {}", appService.findAll());
        logger.info("appService.findAppByAppKey = {}", appService.findAppByAppKey("645ba612-370a-43a8-a8e0-993e7a590cf0"));
        logger.info("appService.findAppByAppKey = {}", appService.findAppByAppKey("645ba612-370a-43a8-a8e0-993e7a590cf0"));
        App app = new App();
        app.setName("test");
        app.setAppKey("645ba616-370a-43a8-a8e0-993e7a99999");
        app.setAppSecret("645ba616-370a-43a8-a8e0-993e7a99999");
        app.setAvailable(1);
        App createApp = appService.createApp(app);
        logger.info("appService.createApp = {}", createApp);
        logger.info("appService.findAll = {}", appService.findAll());
        logger.info("appService.findAll = {}", appService.findAll());
        logger.info("appService.findAppByAppKey = {}", appService.findAppByAppKey("645ba612-370a-43a8-a8e0-993e7a590cf0"));
        logger.info("appService.findAppByAppKey = {}", appService.findAppByAppKey("645ba612-370a-43a8-a8e0-993e7a590cf0"));
        logger.info("appService.findAppByAppKey = {}", appService.findAppByAppKey(app.getAppKey()));
        logger.info("appService.findAppByAppKey = {}", appService.findAppByAppKey(app.getAppKey()));
        appService.deleteApp(app.getId());
    }
    //@Ignore
    @Rollback(true)
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = RuntimeException.class)
    @Test
    public void testRoleServiceCache(){
//        jedisSentinelTemplate.del("SysFindRole_RoleList_1_2_3","SysFindRole_RoleList_1_2_3_4","SysFindRole_Roles_1_2_3","SysFindRole_Perms_1_2_3");
//        jedisSentinelTemplate.del("SysFindRole_RoleListByIds","SysFindRole_RolesByIds","SysFindRole_PermissionsByIds");
        logger.info("roleService.findAll = {}", roleService.findAll());
        logger.info("roleService.findAll = {}", roleService.findAll());

        logger.info("roleService.findRoles = {}", roleService.findRoleList(1L,2L,3L));
        logger.info("roleService.findRoles = {}", roleService.findRoleList(1L,2L,3L));

        logger.info("roleService.findRoles = {}", roleService.findRoleList("1,2,3"));
        logger.info("roleService.findRoles = {}", roleService.findRoleList("1,2,3"));

        logger.info("roleService.findRoleNames = {}", roleService.findRoleNames(1L,2L,3L));
        logger.info("roleService.findRoleNames = {}", roleService.findRoleNames(1L,2L,3L));

//        logger.info("roleService.findPermissions = {}", roleService.findPermissions(new Long[]{1L,2L,3L}));
//        logger.info("roleService.findPermissions = {}", roleService.findPermissions(new Long[]{1L,2L,3L}));

        Role role = new Role();
        role.setRole("xxxx");
        role.setDescription("xxxxxxxx");
//        role.setResourceIds("15,24");
        role.setAvailable(1);

        Role createRole = roleService.createRole(role);
        logger.info("roleService.createRole = {}", createRole);
        logger.info("roleService.findAll = {}", roleService.findAll());
        logger.info("roleService.findAll = {}", roleService.findAll());

        logger.info("roleService.findRoles = {}", roleService.findRoleList(1L,2L,3L));
        logger.info("roleService.findRoles = {}", roleService.findRoleList(1L,2L,3L));

        logger.info("roleService.findRoles = {}", roleService.findRoleList("1,2,3,4"));
        logger.info("roleService.findRoles = {}", roleService.findRoleList("1,2,3,4"));

        logger.info("roleService.findRoleNames = {}", roleService.findRoleNames(1L,2L,3L));
        logger.info("roleService.findRoleNames = {}", roleService.findRoleNames(1L,2L,3L));

//        logger.info("roleService.findPermissions = {}", roleService.findPermissions(new Long[]{1L,2L,3L}));
//        logger.info("roleService.findPermissions = {}", roleService.findPermissions(new Long[]{1L,2L,3L}));
        roleService.deleteRole(createRole.getId());

    }
    //@Ignore
    @Rollback(true)
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = RuntimeException.class)
    @Test
    public void testResourceService2(){
        jedisTemplate.del("com.car.sqoil.service.security.service.ResourceServiceImpl_findPermissions");
        Set<Long> resourceIds = new HashSet<>();
        resourceIds.add(11L);
        resourceIds.add(13L);
        resourceIds.add(21L);
        resourceIds.add(15L);
        resourceIds.add(55L);
        resourceIds.add(54L);
        resourceIds.add(16L);
        resourceIds.add(31L);
        resourceIds.add(51L);
        Set<String> permissions = resourceService.findPermissions(resourceIds);
        logger.info("resourceService.findPermissions = {}", permissions);
        permissions = resourceService.findPermissions(resourceIds);
        logger.info("resourceService.findPermissions = {}", permissions);
    }
    //@Ignore
    @Rollback(true)
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = RuntimeException.class)
    @Test
    public void testResourceService3(){
        Set<Long> resourceIds = new HashSet<>();
        resourceIds.add(11L);
        resourceIds.add(13L);
        resourceIds.add(21L);
        resourceIds.add(15L);
        resourceIds.add(55L);
        resourceIds.add(54L);
        resourceIds.add(16L);
        resourceIds.add(31L);
        resourceIds.add(51L);
        Set<String> permissions = resourceService.findPermissions(resourceIds);
        logger.info("resourceService.findPermissions = {}", permissions);
        logger.info("resourceService.findMenus = {}", resourceService.findMenus(permissions));
        logger.info("resourceService.findMenus = {}", resourceService.findMenus(permissions));
        logger.info("resourceService.findMenus = {}", resourceService.findMenus(resourceService.findMenuAll(), permissions));
        logger.info("resourceService.findMenus = {}", resourceService.findMenus(resourceService.findMenuAll(), permissions));
    }
    //@Ignore
    @Rollback(true)
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = RuntimeException.class)
    @Test
    public void testResourceService(){
//        jedisSentinelTemplate.del("com.car.sqoil.service.security.service.ResourceServiceImpl_findPermissions");
        logger.info("resourceService.findAll = {}", resourceService.findAll());
        logger.info("resourceService.findAll = {}", resourceService.findAll());
        logger.info("resourceService.findResources = {}", resourceService.findResources("11,12,13,15,32"));
        logger.info("resourceService.findResources = {}", resourceService.findResources("11,12,13,15,32"));



        com.stony.sso.facade.entity.Resource resource = new com.stony.sso.facade.entity.Resource();
        resource.setAvailable(1);
        resource.setName("uuuuu");
        resource.setParentId(11L);
        resource.setPermission("organization:*");
        resource.setUrl("");
        resource.setType(ResourceType.BUTTON.TYPE);

        com.stony.sso.facade.entity.Resource createResource = resourceService.createResource(resource);
        logger.info("resourceService.createResource = {}", createResource);

        Set<Long> resourceIds = new HashSet<>();
        resourceIds.add(11L);
        resourceIds.add(13L);
        resourceIds.add(21L);
        resourceIds.add(15L);
        resourceIds.add(55L);
        resourceIds.add(54L);
        resourceIds.add(16L);
        resourceIds.add(31L);
        resourceIds.add(51L);
        Set<String> permissions = resourceService.findPermissions(resourceIds);
        logger.info("resourceService.findPermissions = {}", permissions);
        permissions = resourceService.findPermissions(resourceIds);
        logger.info("resourceService.findPermissions = {}", permissions);

        logger.info("resourceService.findResources = {}", resourceService.findResources("11,12,13,15,32"));
        logger.info("resourceService.findMenus = {}", resourceService.findMenus(permissions));
        logger.info("resourceService.findMenus = {}", resourceService.findMenus(permissions));
        logger.info("resourceService.findMenus = {}", resourceService.findMenus(resourceService.findMenuAll(), permissions));
        logger.info("resourceService.findMenus = {}", resourceService.findMenus(resourceService.findMenuAll(), permissions));

        resourceService.deleteResource(createResource.getId());
    }
    //@Ignore
    @Rollback(true)
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = RuntimeException.class)
    @Test
    public void testResourceServiceDel(){
        resourceService.deleteResource(22L);
    }
    //@Ignore
    @Rollback(true)
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = RuntimeException.class)
    @Test
    public void testFindUserByName(){
        logger.info("userService.findByUsername = {}", userService.findByUsername("admin"));
        logger.info("userService.findByUsername = {}", userService.findByUsername("admin"));

    }
    //@Ignore
    @Rollback(true)
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = RuntimeException.class)
    @Test
    public void testUserService(){
        logger.info("userService.findAll = {}", userService.findAll());
        logger.info("userService.findAll = {}", userService.findAll());
        logger.info("userService.findByUsername = {}", userService.findByUsername("admin"));
        logger.info("userService.findByUsername = {}", userService.findByUsername("admin"));
        User user = new User();
        user.setUsername("LiXue");
        user.setOrganizationId(11L);
        user.setSalt("uadsfasdfasdf");
        user.setLocked(0);
        user.setPassword("sdfsdfsdf");
        user.setPhone("18210208023");

        User createUser = userService.createUser(user);

        logger.info("userService.findAll = {}", userService.findAll());
        logger.info("userService.findAll = {}", userService.findAll());
        logger.info("userService.createUser = {}", createUser);

        logger.info("userService.findByUsername = {}", userService.findByUsername(createUser.getUsername()));

        logger.info("userService.findOne = {}", userService.findOne(createUser.getId()));

        userService.changePassword(createUser.getId(),"555555");
        logger.info("userService.findOne 555555 = {}", userService.findOne(createUser.getId()));
        logger.info("userService.findByUsername 555555 = {}", userService.findByUsername(createUser.getUsername()));
        logger.info("userService.findByUsername = {}", userService.findByUsername("admin"));
        logger.info("userService.findByUsername = {}", userService.findByUsername("admin"));
//        userService.deleteUser(createUser.getId());

        userService.cleanUser(createUser.getId());

    }

    //@Ignore
    @Rollback(true)
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = RuntimeException.class)
    @Test
    public void testOrganizationService(){
        logger.info("organizationService.findAll = {}", organizationService.findAll());
        logger.info("organizationService.findAll = {}", organizationService.findAll());

        Organization organization = new Organization();
        organization.setParentId(11L);
        organization.setName("YYYYYYY");
        organization.setAvailable(1);
        Organization createOrg = organizationService.createOrganization(organization);

        logger.info("organizationService.findAll = {}", organizationService.findAll());
        logger.info("organizationService.findAll = {}", organizationService.findAll());
        logger.info("organizationService.createOrganization = {}", createOrg);

        logger.info("organizationService.findOne = {}", organizationService.findOne(createOrg.getId()));

        logger.info("organizationService.findAllWithExclude = {}", organizationService.findAllWithExclude(createOrg));

        organizationService.deleteOrganization(createOrg.getId());
    }

    //@Ignore
    @Rollback(true)
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = RuntimeException.class)
    @Test
    public void testAddAuthorizationService(){
        logger.info("authorizationService.findAll = {}", authorizationService.findAll());
        logger.info("authorizationService.findAll = {}", authorizationService.findAll());

        UserAppRole authorization = new UserAppRole();
        authorization.setAppId(9L);
        authorization.setUserId(9L);

        UserAppRole createAuth = authorizationService.createAuthorization(authorization);
        logger.info("authorizationService.findAll = {}", authorizationService.findAll());
        logger.info("authorizationService.findAll = {}", authorizationService.findAll());
        logger.info("authorizationService.createAuthorization = {}", createAuth);

        logger.info("authorizationService.findOne = {}", authorizationService.findOne(authorization.getId()));

        authorizationService.deleteAuthorization(authorization.getId());
    }

    //@Ignore
    @Rollback(true)
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = RuntimeException.class)
    @Test
    public void testMergeAuthorizationService(){
        UserAppRole authorization = new UserAppRole();
        authorization.setAppId(5L);
        authorization.setUserId(5L);

        UserAppRole createAuth = authorizationService.createAuthorization(authorization);
        logger.info("authorizationService.createAuthorization = {}", createAuth);
        logger.info("authorizationService.findAll = {}", authorizationService.findAll());
    }

    //@Ignore
    @Rollback(true)
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = RuntimeException.class)
    @Test
    public void testUpdateAuthorizationService(){
        UserAppRole authorization = new UserAppRole();
        authorization.setAppId(5L);
        authorization.setUserId(5L);

        UserAppRole updateAuthorizationRoles = authorizationService.updateAuthorizationRoles(authorization);
        logger.info("authorizationService.createAuthorization = {}", updateAuthorizationRoles);
        logger.info("authorizationService.findAll = {}", authorizationService.findAll());
    }

    //@Ignore
    @Rollback(true)
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = RuntimeException.class)
    @Test
    public void testQueryService(){
        String key = "645ba612-370a-43a8-a8e0-993e7a590cf1";
        logger.info("authorizationService.findRoles = {}", authorizationService.findRoles(key,"bu"));
        logger.info("authorizationService.findRoles = {}", authorizationService.findRoles(key,"bu"));
        logger.info("authorizationService.findResources = {}", authorizationService.findResources(key,"bu"));
        logger.info("authorizationService.findResources = {}", authorizationService.findResources(key,"bu"));

        key = "645ba612-370a-43a8-a8e0-993e7a590cf0";
        logger.info("authorizationService.findRoleNames = {}", authorizationService.findRoles(key,"admin"));
        logger.info("authorizationService.findRoleNames = {}", authorizationService.findRoles(key,"admin"));

        logger.info("authorizationService.findResources = {}", authorizationService.findResources(key,"admin"));
        logger.info("authorizationService.findResources = {}", authorizationService.findResources(key,"admin"));
    }

    @Repeat(5)
    @Rollback(true)
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = RuntimeException.class)
    @Test
    public void testMenuGroup(){
        long beginTime = System.currentTimeMillis();
        List<Resource> menus = resourceService.findMenuAll();
        logger.info("--------------------------------------------------------");
        logger.info("menus = {}", menus);
        Collections.sort(menus, new Comparator<Resource>() {
            @Override
            public int compare(Resource o1, Resource o2) {
                return Integer.valueOf((o1.getParentId() - o2.getParentId()) + "");
            }
        });
        logger.info("--------------------------------------------------------");
        logger.info("menus = {}", menus);
        Map<Long, List<Resource>> group = new HashMap<>();
        Map<Long,String> groupName = new HashMap<>();
        for(Resource resource : menus){
            if(resource.getParentId() == 0){
                List<Resource> clidren = group.get(resource.getId());
                if(clidren == null){
                    clidren = new ArrayList<>();
                }
                group.put(resource.getId(), clidren);
                groupName.put(resource.getId(), resource.getName());
            }else{
                List<Resource> clidren = group.get(resource.getParentId());
                if(clidren == null){
                    clidren = new ArrayList<>();
                    resource.setParentName(groupName.get(resource.getParentId()));
                    clidren.add(resource);
                    group.put(resource.getParentId(), clidren);
                }else{
                    clidren.add(resource);
                }
            }
        }
        logger.info("group = {}", group);
        List<Resource> menus_ = new ArrayList<>();
        for(Resource resource : menus){
            if(resource.getParentId() == 0){
                resource.setChildren(group.get(resource.getId()));
                menus_.add(resource);
            }
        }
        logger.info("---------------------Total {} milliseconds-----------------------------------",(System.currentTimeMillis() - beginTime));
        logger.info("menus_ = {}", JacksonUtil.write2JsonStr(menus_));
    }
    @Rollback(true)
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = RuntimeException.class)
    @Test
    public void testMenuTree(){
        List<Resource> menus = resourceService.findMenuAll();
        logger.info("--------------------------------------------------------");
        logger.info("menus = {}", menus);
        logger.info("--------------------------------------------------------");

        logger.info("tree = {}", JacksonUtil.write2JsonStr(menuTree(menus, 0L, null)));
    }
    public List<Resource> menuTree(List<Resource> all,Long id, String name){
        List<Resource> result = new ArrayList<>();
        for(Resource resource : all){
            if(id.compareTo(resource.getParentId()) == 0){
                List<Resource> children = menuTree(all, resource.getId(),resource.getName());
                resource.setChildren(children);
                resource.setParentName(name);
                result.add(resource);
            }
        }
        return result.isEmpty() ? null : result;
    }
    @Rollback(true)
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false, rollbackFor = RuntimeException.class)
    @Test
    public void testOrganicTree(){
        List<Organization> all = organizationService.findAll();
        logger.info("--------------------------------------------------------");
        logger.info("all = {}", all);
        logger.info("--------------------------------------------------------");
        List<Organization> tree = tree(all,0L,null);
        logger.info("tree = {}", JacksonUtil.write2JsonStr(tree));

    }
    public String recursive(List<Organization> tree){
        StringBuffer buffer = new StringBuffer();

        return buffer.toString();
    }
    public List<Organization> tree(List<Organization> all,Long id, String name){
        List<Organization> result = new ArrayList<>();
        for(Organization organization : all){
            if(id.compareTo(organization.getParentId()) == 0){
                List<Organization> children = tree(all, organization.getId(),organization.getName());
                organization.setChildren(children);
                organization.setParentName(name);
                result.add(organization);
            }
        }
        return result.isEmpty() ? null : result;
    }

    @Test
    public void testFindUserByRole(){
        logger.info("list = {}", userService.findUserByRole("25"));
    }

    @Test
    public void testfindMenusByAppUser(){
        List<Resource> all = authorizationService.findMenusByAppUser("645ba616-370a-43a8-a8e0-993e7a590cf0","admin");
        logger.info("--------------------------------------------------------");
        logger.info("all = {}", all);
        logger.info("--------------------------------------------------------");
    }


    @Test
    public void test_createUser(){
        User user = new User();
        user.setUsername("blue");
        user.setOrganizationId(3L);
        user.setLocked(0);
        user.setPassword("123456");
        user.setSea("123456");
        user.setPhone("13910208011");
        user.setEmail("blue@110.com");

//        PasswordHelper passwordHelper = new PasswordHelper();
//        passwordHelper.encryptPassword(user);

        System.out.println(user);

        User createUser = userService.createUser(user);
        System.out.println(createUser);
    }
}
