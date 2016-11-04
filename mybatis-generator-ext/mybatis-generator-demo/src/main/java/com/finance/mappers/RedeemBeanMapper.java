package com.finance.mappers;

import com.finance.bean.RedeemBean;

public interface RedeemBeanMapper {
    int deleteById(Long id);

    int add(RedeemBean record);

    int addSelective(RedeemBean record);

    RedeemBean findById(Long id);

    int modifySelective(RedeemBean record);

    int modify(RedeemBean record);
}