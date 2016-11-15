package com.yanll.api.indexdata.controller;


import com.yanll.business.indexdata.domain.IndexDataBeanVO;
import com.yanll.business.indexdata.service.IIndexDataService;
import com.yanll.framework.web.result.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by YANLL on 2016/08/29.
 */
@RestController
@RequestMapping(value = "/api/indexdata", name = "首页数据模块控制器")
public class IndexDataController {
    private static final Logger logger = LoggerFactory.getLogger(IndexDataController.class);
    @Autowired
    IIndexDataService indexDataService;

    @RequestMapping(value = "/navi", name = "首页数据导航")
    @ResponseBody
    public JSON navi() {
        try {
            IndexDataBeanVO vo = indexDataService.getIndexData(5L);
            return new JSON(1000, vo);
        } catch (Exception e) {
            logger.error("indexdata navi error.", e);
            return new JSON(0);
        }
    }
}

