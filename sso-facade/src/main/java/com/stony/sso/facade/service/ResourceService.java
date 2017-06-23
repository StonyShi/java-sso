package com.stony.sso.facade.service;



import com.stony.sso.facade.entity.Resource;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>Created with car-facade-security.</p>
 * <p>User: Stony</p>
 * <p>Date: 2016/4/25</p>
 * <p>Time: 18:30</p>
 * <p>Version: 1.0</p>
 */
public interface ResourceService {


    public Resource createResource(Resource resource);
    public Resource updateResource(Resource resource);
    public int deleteResource(Long resourceId);

    /**
     * 批量删除资源
     * @param resourceIds
     */
    int deleteResources(String resourceIds);

    /**
     * 批量禁用资源
     * @param resourceIds
     */
    int disableResources(String resourceIds);

    Resource findOne(Long resourceId);
    List<Resource> findAll();
    List<Resource> findAllUser(Integer userType);
    public List<Resource> findResources(String ids);

    /**
     * 得到资源对应的权限字符串
     * @param resourceIds
     * @return
     */
    Set<String> findPermissions(Set<Long> resourceIds);


    /**
     * 根据用户权限得到菜单
     * @param permissions
     * @return
     */
    List<Resource> findMenus(Set<String> permissions);

    public List<Resource> findMenuAll();
    /**
     * 根据用户权限得到菜单
     * @param allResources 全部资源
     * @param permissions
     * @return
     */
    public List<Resource> findMenus(List<Resource> allResources, Set<String> permissions);

    /**
     * 查询APP用户资源
     * @param appKey
     * @param username
     * @return
     */
    List<Resource> findResourcesByAppUser(String appKey, String username);

    /**
     * 查询角色资源
     * @param roleIds
     * @return
     */
    List<Resource> findResourcesByRoleIds(String roleIds);

    /**
     *
     * @param permissionsResources
     * @return
     */
    List<Resource> findMenus(List<Resource> permissionsResources);

    int updateRoleResources(Long id, String resourceIds);

    /**
     *
     * @return
     */
    Map<Long, Resource> findResources();
}
