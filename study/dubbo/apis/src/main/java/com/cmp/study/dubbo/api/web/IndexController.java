package com.cmp.study.dubbo.api.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by breez on 2016/03/30.
 */

@RestController
@RequestMapping(value = "/index")
@EnableAutoConfiguration
public class IndexController {


    @RequestMapping(value = "/hello")
    public String hello() {
        System.out.println("hello...");
        return "Hello world!";
    }

}