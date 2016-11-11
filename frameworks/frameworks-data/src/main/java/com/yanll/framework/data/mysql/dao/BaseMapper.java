package com.yanll.framework.data.mysql.dao;


import com.yanll.framework.data.mysql.domain.DataEntity;

public interface BaseMapper<T extends DataEntity> {

    T selectByPrimaryKey(Long id);

    int deleteByPrimaryKey(Long id);

    int deleteByPrimaryKeys(Long... id);

    int insert(T record);

    int insertSelective(T record);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

}
