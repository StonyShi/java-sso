package com.stony.sso.cache;

import java.util.Set;

/**
 * Created by Stony on 2016/3/11.
 */
public interface Cachez<T> {

    /**
     * 保持缓存数据
     * @param key
     * @param value
     * @return
     */
    public Integer set(String key, T value);

    /**
     * 保存缓存数据
     * @param key
     * @param value
     * @param expire 秒
     * @return
     */
    public Integer set(String key, T value, int expire);

    /**
     * 获取缓存数据
     * @param key
     * @return
     */
    public T get(String key);

    /**
     * 是否支持过期
     * @return
     */
    public boolean supportExpire();

    /**
     * 删除缓存KEY
     * @param key
     * @return
     */
    public int del(String key);

    /**
     * 将缓存KEY 存入watchKey集合
     * @param watchKey
     * @param cacheKey
     * @return
     */
    public int watch(String watchKey, String cacheKey);

    /**
     * 删除watchKey SET集合中的全部KEYS
     * @param key
     * @return
     */
    public Set<String> unwatch(String key);
}
