package com.stony.sso.cache.aspect;

import com.stony.sso.cache.annotation.Cachezable;
import com.stony.sso.cache.Cachez;
import com.stony.sso.cache.CachezManager;
import com.stony.sso.cache.support.map.CachezMapManager;
import com.stony.sso.commons.DateUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

/**
 * Created by ShiHui on 2016/1/23.
 */
@Aspect
public class CacheManagerAspect implements ApplicationContextAware{

    private static final Logger logger = LoggerFactory.getLogger(CacheManagerAspect.class);

    public static final String DEFAULT_DELIMITER = ",";
    private ApplicationContext ac;

    private CachezManager cachezManager;

    public CacheManagerAspect() {}

    public CacheManagerAspect(CachezManager cachezManager) {
        setCachezManager(cachezManager);
    }
    public void setCachezManager(CachezManager cachezManager){
        this.cachezManager = cachezManager;
    }
    public CachezManager getCachezManager(){
        if(cachezManager == null){
            setCachezManager(new CachezMapManager());
        }
        return this.cachezManager;

    }
    protected Cachez getCachez(){
        return getCachezManager().getCachez();
    }
    protected void set(String key,Object value,int expire){
        getCachez().set(key,value,expire);
    }
    protected Object get(String key){
        return getCachez().get(key);
    }
    protected void del(String key){
        getCachez().del(key);
    }
    protected void del(String[] keys){
        for(String key : keys){
            del(key);
        }
    }


    @Pointcut("@annotation(com.stony.sso.cache.annotation.Cachezable)")
    public void cachePointcut() {}


    @Around("cachePointcut()")
    public Object caching(final ProceedingJoinPoint pjp) throws Throwable {
        return cache(pjp);
    }
    protected Object cache(final ProceedingJoinPoint pjp) throws Throwable{
        logger.info("Enter Caching : {}#{}.",pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName());
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        Cachezable cachezable = AnnotationUtils.findAnnotation(method, Cachezable.class);
        if(cachezable == null){
            return pjp.proceed();
        }
        if(cachezable.type() == Cachezable.CachezType.REMOVE){
            return cacheRemove(pjp, cachezable);
        }
        if(cachezable.type() == Cachezable.CachezType.UPDATE){
            return cacheUpdate(pjp, cachezable);
        }
        return cacheSet(pjp, cachezable);
    }
    protected Object cacheSet(final ProceedingJoinPoint pjp, Cachezable cachezable) throws Throwable{
        String cacheKey = processCacheKey(pjp, cachezable);
        try{
            final Object result = get(cacheKey);
            if (result != null) {
                logger.info("CacheSet on method {} hit key [{}]", pjp.toShortString(), cacheKey);
                return result;
            }
        } catch (Exception ex) {
            logger.warn("CacheSet on method {} hit key [{}] aborted due to an error {}.", pjp.toShortString(), cacheKey, ex.getMessage());
            return pjp.proceed();
        }
        final Object result = pjp.proceed();
        if(result != null){
            try {
                set(cacheKey,result,cachezable.expire());
                processWatchCacheKey(pjp, cachezable, cacheKey);
                logger.info("CacheSet on method {} put key [{}]", pjp.toShortString(), cacheKey);
            } catch (Exception ex) {
                logger.warn("CacheSet on method {} put key [{}] aborted due to an error {}.", pjp.toShortString(), cacheKey, ex.getMessage());
            }
        }else{
            logger.warn("CacheSet on method {} put key [{}] fail to result is null.", pjp.toShortString(), cacheKey);
        }
        return result;
    }

    /**
     *
     * @param pjp
     * @param cachezable
     * @param cacheKey REMOVE 传入 cacheKey 为空值
     */
    protected void processWatchCacheKey(ProceedingJoinPoint pjp, Cachezable cachezable, String cacheKey) {
        String watchKey = cachezable.watch();
        String delimiter = cachezable.delimiter();
        if(isNotEmpty(watchKey)){
            String prefix = cachezable.watchPrefix();
            if(cachezable.type() == Cachezable.CachezType.REMOVE){
                String[] watchKeys = watchKey.split(DEFAULT_DELIMITER);
                if(isNotEmpty(prefix)){
                    for(int i = 0, len = watchKeys.length; i < len; i++){
                        String _watchKey = (prefix + delimiter + watchKeys[i]);
                        watchKeys[i] = _watchKey;
                    }
                }
                for(String _watchKey : watchKeys){
                    unwatch(pjp, _watchKey);
                }
            }else if(cachezable.type() == Cachezable.CachezType.SET){
                String _watchKey = (prefix + delimiter + watchKey);
                watch(pjp, _watchKey, cacheKey);
            }
        }
    }

    /**
     * 保存 cacheKey 到watchKey集合
     * @param pjp
     * @param watchKey 集合KEY
     * @param cacheKey 缓存KEY
     */
    private void watch(ProceedingJoinPoint pjp, String watchKey, String cacheKey){
        try {
            getCachez().watch(watchKey,cacheKey);
            logger.info("WatchCacheKey on method {} put key [{}] to [{}]", pjp.toShortString(), cacheKey, watchKey);
        } catch (Exception ex) {
            logger.warn("WatchCacheKey on method {} put key [{}] to [{}] aborted due to an error {}.", pjp.toShortString(), cacheKey,watchKey, ex.getMessage());
        }
    }

    /**
     *
     * 删除watchKey集合中全部 cacheKey
     * @param pjp
     * @param watchKey 集合KEY
     * @return 返回 watchKey集合
     */
    private void unwatch(ProceedingJoinPoint pjp,String watchKey){
        try {
            Set<String> keys =  getCachez().unwatch(watchKey);
            logger.info("UnWatchCacheKey on method {} del keys [{}] to [{}]", pjp.toShortString(), keys, watchKey);
        } catch (Exception ex) {
            logger.warn("UnWatchCacheKey on method {} del watch key [{}] aborted due to an error {}.", pjp.toShortString(), watchKey, ex.getMessage());
        }
    }
    protected Object cacheRemove(final ProceedingJoinPoint pjp, Cachezable cachezable) throws Throwable{
        String[] cacheKeys = processCacheKeys(pjp, cachezable);
        try {
            del(cacheKeys);
            processWatchCacheKey(pjp, cachezable, null);
            logger.info("CacheRemove on method {} del key [{}]", pjp.toShortString(), Arrays.toString(cacheKeys));
        } catch (Exception ex) {
            logger.warn("CacheRemove on method {} del key [{}] aborted due to an error {}.", pjp.toShortString(), Arrays.toString(cacheKeys), ex.getMessage());
        }
        return pjp.proceed();
    }
    protected Object cacheUpdate(final ProceedingJoinPoint pjp, Cachezable cachezable) throws Throwable{
        String cacheKey = processCacheKey(pjp, cachezable);
        try {
            String updateExc = cachezable.updateExc();
            String updateExcArgs = cachezable.updateExcArgs();
            String[] updateExcLine = updateExc.split("#");
            String[] updateExcArgsLine = updateExcArgs.split(DEFAULT_DELIMITER);
            String targetName = updateExcLine[0];
            String methodName = updateExcLine[1];
            Object target = ac.getBean(targetName);
            Method method = ReflectionUtils.findMethod(target.getClass(),methodName);

            Object[] args = new Object[updateExcArgsLine.length];
            if(updateExcArgsLine.length > 0){
                EvaluationContext context = new StandardEvaluationContext();
                for(int i = 0; i < updateExcArgsLine.length; i++){

                }
            }
            Object result = ReflectionUtils.invokeMethod(method,target,args);
            set(cacheKey,result,cachezable.expire());
        } catch (Exception e) {
            logger.warn("CacheUpdate on method {} update key [{}] aborted due to an error {}.", pjp.toShortString(), cacheKey, e.getMessage());
        }
        return pjp.proceed();
    }
    public static final ExpressionParser EXPRESSION_PARSER = new SpelExpressionParser();
    public boolean isEmpty(String v){
        return v == null || v.length() == 0;
    }
    public boolean isNotEmpty(String v){
        return !isEmpty(v);
    }

    private String[] processCacheKeys(final ProceedingJoinPoint pjp, Cachezable cachezable){
        String _cacheKey = cachezable.key();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String className = pjp.getSignature().getDeclaringTypeName();
        String methodName = pjp.getSignature().getName();
        String delimiter = cachezable.delimiter();
        if(isEmpty(_cacheKey)) {
            return new String[]{(className + delimiter + methodName)};
        }
        String[] cacheKeys = _cacheKey.split(DEFAULT_DELIMITER);

        try {
            Object[] args = pjp.getArgs();
            String[] argNames = signature.getParameterNames();
            if(args != null && args.length > 0){
                EvaluationContext context = new StandardEvaluationContext();
                for(int i = 0; i < argNames.length; i++){
                    context.setVariable(argNames[i],args[i]);
                }
                for(int i = 0; i < cacheKeys.length; i++){
                    String useCacheKey = cacheKeys[i];
                    if("*".equals(useCacheKey)){
                        continue;
                    }
                    if("ALL".equals(useCacheKey)){
                        continue;
                    }
                    if(useCacheKey.contains("#")){
                        Object obj = EXPRESSION_PARSER.parseExpression(useCacheKey).getValue(context);
                        useCacheKey = obj.toString();
                        cacheKeys[i] = useCacheKey;
                    }
                }
            }
            for(int i = 0; i < cacheKeys.length; i++){
                String useCacheKey = cacheKeys[i];
                useCacheKey = processCachePrefix(useCacheKey,className,methodName,cachezable);
                useCacheKey = processCacheSuffix(useCacheKey,cachezable);
                cacheKeys[i] = useCacheKey;
            }
        } catch (Exception e) {
            logger.warn("Process cache keys error  {}.", e.getMessage());
            return new String[]{(className + delimiter + methodName)};
        }
        logger.info("cacheKeys[{}]", Arrays.toString(cacheKeys));
        return cacheKeys;
    }
    private String processCacheKey(final ProceedingJoinPoint pjp, Cachezable cachezable){
        String cacheKey = cachezable.key();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String className = pjp.getSignature().getDeclaringTypeName();
        String methodName = pjp.getSignature().getName();
        String delimiter = cachezable.delimiter();
        if(isEmpty(cacheKey)){
            return className + delimiter + methodName;
        }else{
            try {
                Object[] args = pjp.getArgs();
                String[] argNames = signature.getParameterNames();
                if(args != null && args.length > 0){
                    EvaluationContext context = new StandardEvaluationContext();
                    for(int i = 0; i < argNames.length; i++){
                        context.setVariable(argNames[i],args[i]);
                    }
                    if(cacheKey.contains("#")){
                        Object obj = EXPRESSION_PARSER.parseExpression(cacheKey).getValue(context);
                        cacheKey = obj.toString();
                    }
                }
                String useCacheKey = cacheKey;
                cacheKey = processCachePrefix(useCacheKey,className,methodName,cachezable);
                useCacheKey = cacheKey;
                cacheKey = processCacheSuffix(useCacheKey,cachezable);
            } catch (Exception e) {
                logger.warn("Process cache key error  {}.", e.getMessage());
                return className + delimiter + methodName;
            }
        }
        return cacheKey;
    }
    protected String processCachePrefix(String cacheKey, String className, String methodName, Cachezable cachezable){
        String prefix = getCachePrefix(className, methodName, cachezable);
        String delimiter = cachezable.delimiter();
        if(isNotEmpty(prefix)) {
            if(prefix.endsWith(delimiter)){
                cacheKey = prefix + cacheKey;
            }else{
                cacheKey = prefix + delimiter + cacheKey;
            }
        }
        return cacheKey;
    }
    protected String processCacheSuffix(String cacheKey, Cachezable cachezable){
        String suffix = getCacheSuffix(cachezable);
        String delimiter = cachezable.delimiter();
        if(isNotEmpty(suffix)){
            cacheKey += (delimiter + suffix);
        }
        return cacheKey;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ac = applicationContext;
    }

    protected String getCachePrefix(final ProceedingJoinPoint pjp, Cachezable cachezable) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String className = pjp.getSignature().getDeclaringTypeName();
        String methodName = pjp.getSignature().getName();
        return getCachePrefix(className, methodName, cachezable);
    }
    protected String getCachePrefix(String className, String methodName, Cachezable cachezable){
        String prefix = cachezable.prefix();
        String delimiter = cachezable.delimiter();
        if(isEmpty(prefix)){
            Cachezable.PrefixType prefixType = cachezable.prefixType();
            if(Cachezable.PrefixType.CLASS_METHOD_NAME == prefixType){
                prefix = className + delimiter + methodName + delimiter;
            } else if (Cachezable.PrefixType.CLASS_NAME == prefixType){
                prefix = className + delimiter;
            } else if(Cachezable.PrefixType.METHOD_NAME == prefixType){
                prefix = methodName + delimiter;
            }
        }
        return prefix;
    }
    protected String getCacheSuffix(Cachezable cachezable){
        String suffix = cachezable.suffix();
        if(isEmpty(suffix)){
            Cachezable.SuffixType suffixType = cachezable.suffixType();
            if(Cachezable.SuffixType.DATE_NAME == suffixType){
                suffix = DateUtils.formatDate(DateUtils.now(),"_yyyy_MM_dd");
            }else if(Cachezable.SuffixType.TIME_NAME == suffixType){
                suffix = DateUtils.formatDate(DateUtils.now(),"_HH_mm_ss");
            }else if(Cachezable.SuffixType.DATE_TIME_NAME == suffixType){
                suffix = DateUtils.formatDate(DateUtils.now(),"_yyyy_MM_dd_HH_mm_ss");
            }
        }
        return suffix;
    }
}
