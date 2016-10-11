package com.cmp.study.dubbo.businesses.accountcenter.service.impl;

import com.cmp.study.dubbo.businesses.accountcenter.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * Created by YANLL on 2016/10/08.
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class UserServiceImpl implements IUserService {

    @Override
    public String save() {
        String err = "Hello user save!";
        System.out.println(err);
        return err;
    }
}
