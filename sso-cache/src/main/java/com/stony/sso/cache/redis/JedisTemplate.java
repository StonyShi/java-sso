package com.stony.sso.cache.redis;

import java.util.Collection;
import java.util.Set;

/**
 * <p>Java-SSO
 * <p>com.stony.sso.cache.redis
 *
 * @author stony
 * @version 下午2:41
 * @since 2017/6/23
 */
public interface JedisTemplate  {

    /** ####################################################    字符串(String)      ########################################################### **/
    /**
     * 将字符串值value关联到key。
     * 如果key已经持有其他值，SET就覆写旧值，无视类型。
     * @param key
     * @param value
     * @param seconds
     * @return 总是返回OK，因为SET不可能失败。
     */
    String setStr(String key, String value, Integer seconds);

    String set(String key, Object value);

    String set(String key, Object value, Integer seconds);

    Object get(String key);

    String getStr(String key);


    /** ####################################################    集合(Set)      ########################################################### **/
    /**
     * 将一个或多个member元素加入到集合key当中，已经存在于集合的member元素将被忽略
     * 假如key不存在，则创建一个只包含member元素作成员的集合。
     * 当key不是集合类型时，返回一个错误。
     * @param key Key
     * @param seconds 过期时间
     * @param members String
     * @return
     */
    Long sadd(String key, Integer seconds, String... members);
    /**
     * 返回集合key中的所有成员。
     *
     * @param key
     * @return 返回集合key中的所有成员。
     */
    Set<String> smembers(String key);


    Long sadd(String key, String member);



    /** ####################################################    功能      ########################################################### **/
    /**
     * 删除
     * @param key
     * @return
     */
    Long del(String key);

    Long del(String...keys);

    Long del(Collection<String> keys);

    long expire(String key);

    long expire(String key,int seconds);

    boolean exists(String key);
}
