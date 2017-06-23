package com.stony.sso.facade.service;



import com.stony.sso.facade.entity.App;

import java.util.List;
import java.util.Map;

/**
 * <p>Created with car-facade-security.</p>
 * <p>User: Stony</p>
 * <p>Date: 2016/4/25</p>
 * <p>Time: 18:30</p>
 * <p>Version: 1.0</p>
 */
public interface AppService {


    public App createApp(App app);
    public App updateApp(App app);
    int deleteApp(Long appId);

    /**
     * 批量删除应用
     * @param appIds
     */
    int deleteApps(String appIds);

    /**
     * 批量禁用应用
     * @param appIds
     */
    int disableApps(String appIds);

    public App findOne(Long appId);
    public List<App> findAll();
    public List<App> findAllApp(Integer userType);

    /**
     * 根据appKey查找AppId
     * @param appKey
     * @return
     */
    public App findAppByAppKey(String appKey);

    public App findAppByAppSecret(String appSecret);

    /**
     *
     * @return
     */
    Map<Long, App> findApps();
}
