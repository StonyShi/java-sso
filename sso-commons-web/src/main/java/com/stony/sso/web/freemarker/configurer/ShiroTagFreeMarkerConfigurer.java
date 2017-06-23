package com.stony.sso.web.freemarker.configurer;

import com.stony.sso.web.freemarker.tags.ShiroTags;
import freemarker.template.TemplateException;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;

public class ShiroTagFreeMarkerConfigurer extends FreeMarkerConfigurer {
  
    @Override
    public void afterPropertiesSet() throws IOException, TemplateException {
        super.afterPropertiesSet();
        this.getConfiguration().setSharedVariable("shiro", new ShiroTags());
    }
      
}
