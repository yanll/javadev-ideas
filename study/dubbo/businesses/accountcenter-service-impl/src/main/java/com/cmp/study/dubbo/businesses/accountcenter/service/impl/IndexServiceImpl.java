package com.cmp.study.dubbo.businesses.accountcenter.service.impl;


import com.cmp.study.dubbo.businesses.accountcenter.dao.IIndexDao;
import com.cmp.study.dubbo.businesses.accountcenter.service.IIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by YANLL on 2016/10/08.
 */
@Service
public class IndexServiceImpl implements IIndexService {

    @Autowired
    IIndexDao indexDao;

    @Override
    public String hello() {
        System.out.println("Hello world service!");
        return indexDao.hello();
    }
}
