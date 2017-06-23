package com.stony.sso.server.chain;


import com.stony.sso.commons.StringUtils;
import com.stony.sso.facade.entity.Resource;
import com.stony.sso.facade.service.ResourceService;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;

import java.text.MessageFormat;
import java.util.List;

/**
 * <p>Created with IntelliJ IDEA. </p>
 * <p>User: Stony </p>
 * <p>Date: 2016/4/29 </p>
 * <p>Time: 11:08 </p>
 * <p>Version: 1.0 </p>
 */
public class ChainDefinitionSectionMetaSource implements FactoryBean<Section> {

    private static final Logger logger = LoggerFactory.getLogger(ChainDefinitionSectionMetaSource.class);

    private String filterChainDefinitions;
    private String lastFilterChainDefinitions;
    public static final String PREMISSION_STRING="perms[\"{0}\"]";

    @javax.annotation.Resource
    ResourceService resourceService;

    @Override
    public Section getObject() throws Exception {
        Ini ini = new Ini();
        //默认的资源权限
        ini.load(filterChainDefinitions);
        Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        List<Resource> resources = resourceService.findAll();
        for(Resource resource : resources){
            if(StringUtils.isNotEmpty(resource.getUrl()) && StringUtils.isNotEmpty(resource.getPermission())) {
                section.put(resource.getUrl(),  MessageFormat.format(PREMISSION_STRING,resource.getPermission()));
            }
        }
        if(lastFilterChainDefinitions != null){
            ini.load(lastFilterChainDefinitions);
        }
        logger.info("section = {}", section.entrySet());
        return section;
    }

    @Override
    public Class<?> getObjectType() {
        return this.getClass();
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public void setFilterChainDefinitions(String filterChainDefinitions) {
        this.filterChainDefinitions = filterChainDefinitions;
    }

    public void setLastFilterChainDefinitions(String lastFilterChainDefinitions) {
        this.lastFilterChainDefinitions = lastFilterChainDefinitions;
    }
}
