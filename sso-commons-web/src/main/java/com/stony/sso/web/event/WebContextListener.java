package com.stony.sso.web.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextStartedEvent;

/**
 * @author Stony
 *  Created Date : 2016/4/14  11:15
 */
public class WebContextListener implements ApplicationListener {
    private static final Logger logger = LoggerFactory.getLogger(WebContextListener.class);

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof ContextStartedEvent){
            logger.debug("It was Started.");
        }

        if (applicationEvent instanceof ContextClosedEvent){
            ContextClosedEvent event = (ContextClosedEvent) applicationEvent;
            if(event.getApplicationContext().getParent()==null){
                logger.debug("App context was Closed.");
                synchronized (WebContextListener.class) {
                    try {
                        WebContextListener.class.notifyAll();
                    } catch (Throwable e) {}
                }
                Runtime.getRuntime().exit(0);
            }else{
                logger.debug("Web context was Closed.");
            }
            event = null;
        }
    }
}
