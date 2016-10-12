package com.cmp.study.dubbo.businesses.accountcenter.service.impl;

import com.cmp.study.dubbo.businesses.accountcenter.service.IUserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * Created by YANLL on 2016/10/08.
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class UserServiceImpl implements IUserService {

    private static final Log logger = LogFactory.getLog(UserServiceImpl.class);

    @Override
    public String save() {
        String err = "Hello user save!";
        logger.info(err);
        return err;
    }
}
