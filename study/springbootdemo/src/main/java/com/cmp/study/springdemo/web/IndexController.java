package com.cmp.study.springdemo.web;

import com.cmp.study.springdemo.MySettings;
import com.cmp.study.springdemo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    MySettings mySettings;

    @RequestMapping(value = "/hello")
    public UserVO hello() {
        System.out.println("hello...");
        System.out.println(mySettings.getName() + mySettings.getFix());
        UserVO user = new UserVO();
        user.setName("admin");
        user.setAddress("china");
        return user;
    }

}