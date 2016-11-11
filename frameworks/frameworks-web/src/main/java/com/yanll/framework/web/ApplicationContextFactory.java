package com.yanll.framework.web;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: YAN
 * Date: 14-4-24
 * Time: 下午1:38
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationContextFactory {
    private static ApplicationContext context;

    static {
        if (context == null) {
            context = new ClassPathXmlApplicationContext("classpath:/*.xml");
        }
    }

    public static ApplicationContext getContext() {
        return context;
    }
}
