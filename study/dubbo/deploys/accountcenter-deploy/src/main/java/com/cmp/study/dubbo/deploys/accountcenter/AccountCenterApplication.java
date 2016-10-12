package com.cmp.study.dubbo.deploys.accountcenter;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@EnableAutoConfiguration
@ImportResource(locations = "classpath:accountcenter-provider.xml")
@ComponentScan(basePackages = "com.cmp.study.dubbo.businesses.accountcenter")
public class AccountCenterApplication {
    private static final Log logger = LogFactory.getLog(AccountCenterApplication.class);

    public static void main(String[] args) {
        try {
            new SpringApplicationBuilder(AccountCenterApplication.class).web(false).run(args);
            logger.info("AccountCenterApplication服务启动成功。");
            Object lock = new Object();
            synchronized (lock) {
                try {
                    while (true) lock.wait();
                } catch (InterruptedException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        } catch (Exception ex) {
            logger.error("AccountCenterApplication服务启动失败。", ex);
        }
    }
}
