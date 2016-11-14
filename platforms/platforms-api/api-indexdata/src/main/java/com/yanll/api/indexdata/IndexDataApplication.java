package com.yanll.api.indexdata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by breez on 2016/03/30.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.yanll.framework.web", "com.yanll.business.indexdata", "com.yanll.api.indexdata"})
@MapperScan(basePackages = "com.yanll.business.indexdata.dao")
public class IndexDataApplication {


    public static void main(String[] args) {
        SpringApplication.run(IndexDataApplication.class, args);
    }
}