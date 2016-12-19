package com.yanll.console.auth.controller;


import com.yanll.business.auth.domain.UserBeanVO;
import com.yanll.business.auth.service.IAuthService;
import com.yanll.business.auth.service.IUserService;
import com.yanll.framework.util.exception.BizCode;
import com.yanll.framework.web.annotation.Permission;
import com.yanll.framework.web.result.JSON;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by YANLL on 2016/08/29.
 */
@Permission(controlled = false)
@RestController
@RequestMapping(value = "/console/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    IAuthService authService;
    @Autowired
    IUserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, name = "用户登录")
    @ResponseBody
    public JSON login(HttpServletRequest request, String username, String password) {
        UserBeanVO vo = userService.selectUser(username, password);
        if (vo != null && vo.getId() != null) {
            Map<String, String> map = new HashMap();
            map.put("/console/menu/list:GET", "");
            request.getSession().setAttribute("user", username);
            request.getSession().setAttribute("user_permission", map);
        }
        return new JSON(BizCode.OK.getValue(), vo);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET, name = "注销登录")
    @ResponseBody
    public JSON logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("user_permission");
        return new JSON(BizCode.OK.getValue());
    }

}

