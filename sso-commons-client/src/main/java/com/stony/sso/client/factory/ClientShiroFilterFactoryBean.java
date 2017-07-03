package com.stony.sso.client.factory;


import com.stony.sso.client.ClientInfoHold;
import com.stony.sso.commons.StringUtils;
import com.stony.sso.facade.entity.Resource;
import com.stony.sso.facade.service.PermissionService;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import javax.servlet.Filter;
import java.text.MessageFormat;
import java.util.List;

/**
 * <p>Created with car-security-client.</p>
 * <p>User: Stony</p>
 * <p>Date: 2016/4/25</p>
 * <p>Time: 18:30</p>
 */
public class ClientShiroFilterFactoryBean extends ShiroFilterFactoryBean implements ApplicationContextAware,InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(ClientShiroFilterFactoryBean.class);

    private ApplicationContext applicationContext;

    @javax.annotation.Resource
    PermissionService permissionService;

    public static final String PREMISSION_STRING="perms[\"{0}\"]";
    public static final String DELIMITER = ";";
    public static final String DELIMITER_KEY_VAL = "=";

    String lastFilterChainDefinitionsStr;
    String filterChainDefinitionsStr;
    boolean isInitLast = false;

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void setFiltersStr(String filters) {
        if(StringUtils.isEmpty(filters)) {
            return;
        }
        String[] filterArray = filters.split(DELIMITER);
        for(String filter : filterArray) {
            String[] o = filter.split(DELIMITER_KEY_VAL);
            getFilters().put(o[0], (Filter)applicationContext.getBean(o[1]));
        }
    }

    public void setLastFilterChainDefinitionsStr(String filterChainDefinitions) {
        this.lastFilterChainDefinitionsStr = filterChainDefinitions;

    }
    public void setFilterChainDefinitionsStr(String filterChainDefinitions) {
        this.filterChainDefinitionsStr = filterChainDefinitions;
    }



    private String formatMessage(String pattern, Object... arguments) {
        return MessageFormat.format(PREMISSION_STRING, arguments);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        doInitFilterChainDefinitionsStr(this.filterChainDefinitionsStr);

        List<Resource> resources = permissionService.getResources(ClientInfoHold.APP_KEY);
        for(Resource resource : resources){
            if(StringUtils.isNotEmpty(resource.getUrl()) && StringUtils.isNotEmpty(resource.getPermission())) {
                getFilterChainDefinitionMap().put(resource.getUrl(),formatMessage(PREMISSION_STRING,resource.getPermission()));
            }
        }

        if (!isInitLast) {
            doInitFilterChainDefinitionsStr(this.lastFilterChainDefinitionsStr);
        }

        logger.info("FilterChainDefinitionMap = {}",getFilterChainDefinitionMap());
    }
    void doInitFilterChainDefinitionsStr(String filterChainDefinitions){
        if(StringUtils.isEmpty(filterChainDefinitions)) {
            return;
        }
        String[] chainDefinitionsArray = filterChainDefinitions.split(DELIMITER);
        for(String filter : chainDefinitionsArray) {
            String[] o = filter.split(DELIMITER_KEY_VAL);
            getFilterChainDefinitionMap().put(o[0], o[1]);
        }
    }
}
