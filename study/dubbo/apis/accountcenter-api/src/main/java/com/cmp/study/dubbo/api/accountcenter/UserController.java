package com.cmp.study.dubbo.api.accountcenter;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cmp.study.dubbo.businesses.accountcenter.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by breez on 2016/03/30.
 */

@RestController
@RequestMapping(value = "/accountcenter/user")
@EnableAutoConfiguration
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Reference
    IUserService userService;

    @RequestMapping(value = "/save")
    public String save() {
        try {
            return userService.save();
        } catch (Exception e) {
            logger.error("User Save error.", e);
            return "User Save error.";
        }
    }
}