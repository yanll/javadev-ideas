package com.cmp.study.springdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Created by breez on 2016/03/30.
 */

@SpringBootApplication
@EnableConfigurationProperties({MySettings.class})
public class Application {



    long a=111_0L;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}