package com.cmp.study.dubbo.businesses.accountcenter.dao.impl;

import com.cmp.study.dubbo.businesses.accountcenter.dao.IIndexDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by YANLL on 2016/10/08.
 */
@Repository
public class IndexDaoImpl implements IIndexDao {

    private static final Logger logger = LoggerFactory.getLogger(IndexDaoImpl.class);

    @Override
    public String hello() {
        logger.info("Hello world dao!");
        return "Hello world dao!\n";
    }
}
