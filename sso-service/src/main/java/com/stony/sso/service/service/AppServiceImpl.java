package com.stony.sso.service.service;

import com.stony.sso.service.mapper.AppMapper;
import com.google.common.collect.Sets;

import com.stony.sso.cache.annotation.Cachezable;
import com.stony.sso.commons.CollectionUtil;
import com.stony.sso.commons.DateUtils;
import com.stony.sso.facade.entity.App;
import com.stony.sso.facade.enums.ResourceStatus;
import com.stony.sso.facade.keys.SecurityKeys;
import com.stony.sso.facade.service.AppService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/5/26 </p>
 * <p>Time: 11:29 </p>
 * <p>Version: 1.0 </p>
 */
@Service("appService")
public class AppServiceImpl implements AppService {

    @javax.annotation.Resource
    private AppMapper appMapper;

    public static final String REMOVE_WATCH_KEYS = "AppKeys";

    @Cachezable(key = "ALL,MAPALL", prefix = SecurityKeys.KEY_FIND_APP, type = Cachezable.CachezType.REMOVE, watch = REMOVE_WATCH_KEYS)
    public App createApp(App app) {
        appMapper.insert(app);
        return app;
    }

    @Override
    @Cachezable(key = "#app.id,ALL,MAPALL", prefix = SecurityKeys.KEY_FIND_APP, type = Cachezable.CachezType.REMOVE, watch = REMOVE_WATCH_KEYS)
    public App updateApp(App app) {
        return appMapper.updateByPrimaryKeySelective(app) > 0 ? app : null;
    }

    @Override
    @Cachezable(key = "#appId,ALL,MAPALL", prefix = SecurityKeys.KEY_FIND_APP, type = Cachezable.CachezType.REMOVE, watch = REMOVE_WATCH_KEYS)
    public int deleteApp(Long appId) {
        return appMapper.deleteByPrimaryKey(appId);
    }

    @Override
    @Cachezable(key = "#appIds,ALL,MAPALL", prefix = SecurityKeys.KEY_FIND_APP, type = Cachezable.CachezType.REMOVE, watch = REMOVE_WATCH_KEYS)
    public int deleteApps(String appIds) {
        Iterable<String> ids = CollectionUtil.split(appIds);
        HashSet<String> _ids = Sets.newHashSet(ids);
        int i = 0;
        for (String appId : _ids) {
            i += deleteApp(Long.valueOf(appId));
        }
        return i;
    }

    @Override
    @Cachezable(key = "#appIds,ALL,MAPALL", prefix = SecurityKeys.KEY_FIND_APP, type = Cachezable.CachezType.REMOVE, watch = REMOVE_WATCH_KEYS)
    public int disableApps(String appIds) {
        Iterable<String> ids = CollectionUtil.split(appIds);
        HashSet<String> _ids = Sets.newHashSet(ids);
        int i = 0;
        for (String appId : _ids) {
            if(null != updateApp(new App((Long.valueOf(appId)), ResourceStatus.UNAVAILABLE.STATUS, DateUtils.now()))){
                i++;
            }
        }
        return i;
    }

    @Override
    @Cachezable(key = "#appId", prefix = SecurityKeys.KEY_FIND_APP, type = Cachezable.CachezType.SET)
    public App findOne(Long appId) {
        return appMapper.selectByPrimaryKey(appId);
    }

    @Override
    @Cachezable(key = "ALL", prefix = SecurityKeys.KEY_FIND_APP, type = Cachezable.CachezType.SET)
    public List<App> findAll() {
        return appMapper.findAll();
    }
    public List<App> findAllApp(Integer userType) {
        return appMapper.findAllApp(userType);
    }

    @Override
    @Cachezable(key = "#appKey", prefix = SecurityKeys.KEY_FIND_APP, type = Cachezable.CachezType.SET, watch = REMOVE_WATCH_KEYS)
    public App findAppByAppKey(String appKey) {
        return appMapper.findAppByAppKey(appKey);
    }

    @Override
    @Cachezable(key = "#appSecret", prefix = SecurityKeys.KEY_FIND_APP, type = Cachezable.CachezType.SET, watch = REMOVE_WATCH_KEYS)
    public App findAppByAppSecret(String appSecret){
        return appMapper.findAppByAppSecret(appSecret);
    }

    @Override
    @Cachezable(key = "MAPALL", prefix = SecurityKeys.KEY_FIND_APP, type = Cachezable.CachezType.SET)
    public Map<Long, App> findApps() {
        Map<Long, App> map = new HashMap<>();
        List<App> list = findAll();
        for(App app : list){
            map.put(app.getId(), app);
        }
        return map;
    }
}
