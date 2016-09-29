package com.cmp.common.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 用于读写分离的动态数据源，使用AOP设置数据源
 * 少数情况使用@DataSource注解手动设置
 * Created by YAN on 2015/12/13.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return HandleDataSource.getDataSource();
    }

}