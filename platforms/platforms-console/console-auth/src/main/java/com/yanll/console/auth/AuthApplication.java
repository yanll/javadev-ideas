package com.yanll.console.auth;

import com.yanll.console.core.config.PermissionCongfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by breez on 2016/03/30.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.yanll.framework.web", "com.yanll.business.auth", "com.yanll.console.auth"}, basePackageClasses = {PermissionCongfiguration.class})
@MapperScan(basePackages = "com.yanll.business.auth.dao")
public class AuthApplication {


    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}