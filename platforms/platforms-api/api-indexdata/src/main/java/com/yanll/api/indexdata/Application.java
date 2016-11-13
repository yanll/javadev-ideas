package com.yanll.api.indexdata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * Created by breez on 2016/03/30.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.yanll.framework.web", "com.yanll.business.indexdata", "com.yanll.api.indexdata"})
@MapperScan(basePackages = "com.yanll.business.indexdata.dao")
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}