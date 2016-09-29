package com.cmp.common.db;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于动态数据源的选择注解
 * 动态数据源一般情况下经SpringAOP设置，不需要手动使用此@DataSource
 * 仅在特殊情况时使用此注解，例如某一业务单一且数据量较大（和主数据放一起会影响性能）时，可以单独开一台数据库服务器并设置其数据源。
 * Created by YAN on 2015/12/13.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DataSource {
    String value();
}
