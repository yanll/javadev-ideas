package com.cmp.study.dubbo.api.accountcenter;

import com.cmp.study.dubbo.businesses.accountcenter.service.IIndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by breez on 2016/03/30.
 */

@RestController
@RequestMapping(value = "/accountcenter/index")
@EnableAutoConfiguration
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    IIndexService indexService;

    @RequestMapping(value = "/hello")
    public String index() {
        try {
            return indexService.hello();
        } catch (Exception e) {
            logger.error("Index hello error.", e);
            return "error.";
        }
    }
}