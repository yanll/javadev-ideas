package com.cmp.study.dubbo.api.accountcenter;

import com.alibaba.dubbo.config.annotation.Reference;
import com.cmp.study.dubbo.businesses.accountcenter.service.IUserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    private static final Log logger = LogFactory.getLog(UserController.class);

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