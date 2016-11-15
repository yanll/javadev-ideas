package com.cmp.study.activitidemo.activiti;

import com.yanll.framework.util.FileUtil;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by YANLL on 2016/10/13.
 */
@Controller
@RequestMapping("/activiti")
public class ActivitiController {

    private static final Logger logger = LoggerFactory.getLogger(ActivitiController.class);

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    ProcessEngine processEngine;
    @Autowired
    RepositoryService repositoryService;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() throws Exception, SQLException {
        System.out.println(processEngine.getName());
        Model model = repositoryService.getModel("1");
        Map<String, Object> map = jdbcTemplate.queryForObject("select * from ACT_GE_BYTEARRAY where ID_ = 2", new RowMapper<Map<String, Object>>() {
            public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
                byte[] s = rs.getBytes("BYTES_");
                System.out.println(FileUtil.bytesToStirng(s));
                return new HashMap<String, Object>();
            }
        });
        return "hello activiti!";
    }


}
