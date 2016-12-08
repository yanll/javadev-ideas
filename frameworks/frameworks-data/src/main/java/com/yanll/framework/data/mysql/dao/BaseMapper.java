package com.yanll.framework.data.mysql.dao;


import com.yanll.framework.data.mysql.domain.DataEntity;

import java.util.List;

public interface BaseMapper<T extends DataEntity> {

    T selectByPrimaryKey(Long id);

    int deleteByPrimaryKey(Long id);

    int deleteByPrimaryKeys(Long... id);

    int insert(T record);

    int batchInsert(List<T> list);

    int insertSelective(T record);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

}
