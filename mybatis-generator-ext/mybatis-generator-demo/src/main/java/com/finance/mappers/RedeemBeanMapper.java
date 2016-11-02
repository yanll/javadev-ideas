package com.finance.mappers;

import com.finance.bean.RedeemBean;

public interface RedeemBeanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RedeemBean record);

    int insertSelective(RedeemBean record);

    RedeemBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RedeemBean record);

    int updateByPrimaryKey(RedeemBean record);
}