package com.cmp.study.dubbo.businesses.accountcenter.service.impl;


import com.cmp.study.dubbo.businesses.accountcenter.dao.IIndexDao;
import com.cmp.study.dubbo.businesses.accountcenter.service.IIndexService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by YANLL on 2016/10/08.
 */
@Service
public class IndexServiceImpl implements IIndexService {

    private static final Log logger = LogFactory.getLog(IndexServiceImpl.class);

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
