package com.cmp.study.springdemo.configuration;

import com.cmp.study.springdemo.service.IIndexService;
import com.cmp.study.springdemo.service.IUserService;
import com.cmp.study.springdemo.service.IndexServiceImpl;
import com.cmp.study.springdemo.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBeanConfiguration {

    @Bean
    public IUserService httpProxy2() {
        return new UserServiceImpl();
    }

    @Bean
    public IIndexService httpProxy(IUserService userService) {
        userService.hello();
        return new IndexServiceImpl();
    }
}
