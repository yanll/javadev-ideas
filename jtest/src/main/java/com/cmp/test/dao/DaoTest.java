package com.cmp.test.dao;

import com.cmp.common.dao.IDao;
import com.cmp.test.Base;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;

/**
 * Created by YLL on 2015/07/10.
 */
@Ignore
public class DaoTest extends Base {

    @Resource
    protected IDao dao;

    @Resource
    protected JdbcTemplate jdbcTemplate;


    @Test
    public void test() {
        logger.info(dao.queryForList("select count(*) from t_index_data").size(), null);
    }

}
