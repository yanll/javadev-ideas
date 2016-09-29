package com.cmp.test.mybatis.page;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 使用方式：mybatis插件配置此类<plugin interceptor="com.xxx.PageHelper"></plugin>
 * Mybatis - 自定义分页拦截器
 * <p>
 * 查询总条数和计算总页数
 * <p>
 * <p>
 * 目前仅Manager项目需要用到。
 * <p>
 * <p>
 * Created by YANLL on 2016/06/06.
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PageHelper implements Interceptor {

    private static final Logger logger = Logger.getLogger(PageHelper.class);

    public static final ThreadLocal<PageVO> local_page = new ThreadLocal<PageVO>();

    public static void start(Integer page, Integer limit) {
        if (page == null || page <= 0) page = 1;
        if (limit == null || limit <= 0) limit = 20;
        local_page.set(new PageVO(page, limit));
    }

    /**
     * 此方法必须被调用，否则local_page会保留到到下一次start才会清空
     *
     * @return
     */
    public static PageVO end() {
        PageVO page = local_page.get();
        local_page.remove();
        return page;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (local_page.get() == null || local_page.get().getPage() == null) {
            return invocation.proceed();
        }
        if (invocation.getTarget() instanceof StatementHandler) {
            StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
            MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
            MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
            PageVO page = local_page.get();
            BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
            String sql = boundSql.getSql();
            Connection connection = (Connection) invocation.getArgs()[0];
            setPageParameter(sql, connection, mappedStatement, boundSql, page);
            return invocation.proceed();
        }
        return null;
    }

    /**
     * 只拦截这两种类型的StatementHandler
     *
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }

    /**
     * 获取总记录数和总页数
     *
     * @param sql
     * @param connection
     * @param mappedStatement
     * @param boundSql
     * @param page
     */
    private void setPageParameter(String sql, Connection connection, MappedStatement mappedStatement, BoundSql boundSql, PageVO page) {
        String countSql = "select count(0) from (" + sql + ") as tbx";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            preparedStatement = connection.prepareStatement(countSql);
            BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
            setParameters(preparedStatement, mappedStatement, countBS, boundSql.getParameterObject());
            rs = preparedStatement.executeQuery();
            int total = 0;
            if (rs.next()) total = rs.getInt(1);
            int pages = total / page.getLimit() + ((total % page.getLimit() == 0) ? 0 : 1);
            page.setTotal(total);
            page.setPages(pages);
        } catch (SQLException e) {
            logger.error("setPageParameter exception.", e);
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                logger.error("ResultSet close exception.", e);
            }
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                logger.error("PreparedStatement close exception.", e);
            }
        }
    }

    /**
     * 代入参数值
     *
     * @param ps
     * @param mappedStatement
     * @param boundSql
     * @param parameterObject
     * @throws SQLException
     */
    private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject) throws SQLException {
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
        parameterHandler.setParameters(ps);
    }
}
