package com.yanll.framework.data.mysql.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by YAN on 14-2-21.
 */


@Repository
public abstract class AbstractDao implements IDao {

    @Resource
    public JdbcTemplate jdbcTemplate;

    @Override
    public void execute(String sql) {
        jdbcTemplate.execute(sql);
    }

    @Override
    public int update(String sql, Object... args) {
        return jdbcTemplate.update(sql, args);
    }

    @Override
    public int[] batchUpdate(String sql, List<Object[]> args) {
        return jdbcTemplate.batchUpdate(sql, args);
    }

    @Override
    public int queryForInt(String sql, Object... args) {
        return jdbcTemplate.queryForObject(sql, Integer.class, args);
    }

    @Override
    public Map<String, Object> queryForMap(String sql, Object... args) {
        return jdbcTemplate.queryForMap(sql, args);
    }

    @Override
    public List<Map<String, Object>> queryForList(String sql, Object... args) {
        return jdbcTemplate.queryForList(sql, args);
    }

}
