package com.yanll.framework.core.service.mysql;


import com.yanll.framework.util.exception.BizException;


public interface BaseService<V> {

    V selectByPrimaryKey(Long id) throws BizException;

    int deleteByPrimaryKey(Long id) throws BizException;

    int deleteByPrimaryKeys(Long... id) throws BizException;

    int insert(V record) throws BizException;

    int insertSelective(V record) throws BizException;

    int updateByPrimaryKeySelective(V record) throws BizException;

    int updateByPrimaryKey(V record) throws BizException;


}
