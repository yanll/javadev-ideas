package com.yanll.console.core.config;

import com.yanll.console.core.filter.permission.PermissionInteceptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class PermissionCongfiguration extends WebMvcConfigurerAdapter {
    private final Log logger = LogFactory.getLog(PermissionCongfiguration.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("******Registry session interceptor.*******");
        registry.addInterceptor(permissionInteceptor())/*.addPathPatterns("*//*")*/;
        super.addInterceptors(registry);
    }

    @Bean
    public PermissionInteceptor permissionInteceptor() {
        return new PermissionInteceptor();
    }
}
