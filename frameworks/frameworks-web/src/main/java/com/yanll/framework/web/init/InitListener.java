package com.yanll.framework.web.init;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class InitListener implements ServletContextListener {

    private static final Log logger = LogFactory.getLog(InitListener.class);

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {

    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            logger.info("执行系统初始化InitListener...");
        } catch (Exception e) {
            logger.error("执行系统初始化InitListener出错：", e);
        }
    }


}
