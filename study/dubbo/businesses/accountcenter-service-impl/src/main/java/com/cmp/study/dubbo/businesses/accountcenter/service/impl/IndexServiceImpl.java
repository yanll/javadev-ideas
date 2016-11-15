package com.cmp.study.dubbo.businesses.accountcenter.service.impl;


import com.cmp.study.dubbo.businesses.accountcenter.dao.IIndexDao;
import com.cmp.study.dubbo.businesses.accountcenter.service.IIndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by YANLL on 2016/10/08.
 */
@Service
public class IndexServiceImpl implements IIndexService {

    private static final Logger logger = LoggerFactory.getLogger(IndexServiceImpl.class);

    @Autowired
    IIndexDao indexDao;

    @Override
    public String hello() {
        logger.info("Hello world service!");
        "".substring(0, 100);
        if (true) throw new RuntimeException();
        return indexDao.hello();
    }
}
