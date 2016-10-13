package com.cmp.study.activitidemo.activiti;

import org.activiti.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by YANLL on 2016/10/13.
 */
@Controller
@RequestMapping("/activiti_process")
public class ActivitiController {
    @Autowired
    ProcessEngine processEngine;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        System.out.println(processEngine.getName());
        return "hello activiti!";
    }

}
