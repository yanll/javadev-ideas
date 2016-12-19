package com.yanll.console.auth.controller;


import com.yanll.business.auth.domain.UserBeanVO;
import com.yanll.console.auth.manager.UserManager;
import com.yanll.framework.util.exception.BizCode;
import com.yanll.framework.web.result.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.InputStream;


/**
 * Created by YANLL on 2016/08/29.
 */
@RestController
@RequestMapping(value = "/console/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserManager userManager;

    @RequestMapping(value = "/list", method = RequestMethod.GET, name = "查询用户列表")
    @ResponseBody
    public JSON list() {
        return new JSON(BizCode.OK.getValue(), userManager.getUsers(""));
    }


    @RequestMapping(value = "/imp", method = RequestMethod.POST, name = "导入用户")
    @ResponseBody
    public JSON imp() {
        try {
            InputStream is = new FileInputStream("D:/u.xlsx");
            userManager.imp("u.xlsx", is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JSON(BizCode.OK.getValue());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, name = "保存用户")
    @ResponseBody
    public JSON save(String nickname) {
        UserBeanVO user = new UserBeanVO();
        user.setId(10000L);
        user.setNickname(nickname);
        userManager.save(user);
        return new JSON(BizCode.OK.getValue());
    }

}

