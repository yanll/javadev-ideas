package com.yanll.framework.web.init;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class InitListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(InitListener.class);

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
