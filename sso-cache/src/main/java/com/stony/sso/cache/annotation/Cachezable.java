package com.stony.sso.cache.annotation;


import java.lang.annotation.*;

/**
 * Created by ShiHui on 2016/1/23.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Cachezable {

    /**
     * 支持Spring el 表达式
     * 多个逗号分隔
     * @return
     */
    String key();

    String field() default "";

    CachezType type() default CachezType.SET;

    String unless() default "";

    String condition() default "";

    String prefix() default "";

    String suffix() default "";

    String delimiter() default "_";

    PrefixType prefixType() default PrefixType.NULL_NAME;

    SuffixType suffixType() default SuffixType.NULL_NAME;

    /**
     * cacheKey 集合 SET处理单个，REMOVE 可以处理多个key，用逗号隔开
     * @return
     */
    String watch() default "";

    /**
     * watch key 集合前缀，默认WATCH
     * @return
     */
    String watchPrefix() default "WATCH";

    /**
     * 秒 86400
     * 默认 90天
     * @return
     */
    int expire() default 86400*90;

    /**
     * 调用方法，将结果集放入缓存
     * @return
     */
    String updateExc() default "";

    /**
     * 调用方法参数,多个用逗号分隔
     * @return
     */
    String updateExcArgs() default "";

    public enum PrefixType{
        METHOD_NAME,CLASS_NAME,CLASS_METHOD_NAME,NULL_NAME
    }
    public enum SuffixType{
        NULL_NAME,DATE_NAME,TIME_NAME,DATE_TIME_NAME
    }
    public enum CachezType{
        UPDATE,SET,REMOVE
    }
}
