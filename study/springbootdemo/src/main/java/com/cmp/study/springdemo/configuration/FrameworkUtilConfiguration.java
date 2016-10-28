package com.cmp.study.springdemo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MyBeanConfiguration.class})
public class FrameworkUtilConfiguration {

}
