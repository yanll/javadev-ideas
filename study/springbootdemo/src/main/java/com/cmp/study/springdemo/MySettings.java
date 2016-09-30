package com.cmp.study.springdemo;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by YANLL on 2016/09/30.
 */
@ConfigurationProperties(prefix = "mysettings", locations = "classpath:mysettings.properties")
public class MySettings {
    private String name;
    private String fix;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFix() {
        return fix;
    }

    public void setFix(String fix) {
        this.fix = fix;
    }
}
