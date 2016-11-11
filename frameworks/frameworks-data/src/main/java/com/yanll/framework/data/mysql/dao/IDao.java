package com.yanll.framework.data.mysql.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by YAN on 14-2-21.
 */

public interface IDao {

    public void execute(String sql);

    public int update(String sql, Object... args);

    public int[] batchUpdate(String sql, List<Object[]> args);

    public int queryForInt(String sql, Object... args);

    public Map<String, Object> queryForMap(String sql, Object... args);

    public List<Map<String, Object>> queryForList(String sql, Object... args);

    public List<Map<String, Object>> queryForList(String sql, int page, int limit, Object... args);

}
