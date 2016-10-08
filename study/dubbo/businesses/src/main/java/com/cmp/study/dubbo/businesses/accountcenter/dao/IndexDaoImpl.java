package com.cmp.study.dubbo.businesses.accountcenter.dao;

import org.springframework.stereotype.Repository;

/**
 * Created by YANLL on 2016/10/08.
 */
@Repository
public class IndexDaoImpl implements IIndexDao {
    @Override
    public String hello() {
        System.out.println("Hello world dao!");
        return "Hello world dao!\n";
    }
}
