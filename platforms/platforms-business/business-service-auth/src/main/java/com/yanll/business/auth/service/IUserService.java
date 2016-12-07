package com.yanll.business.auth.service;

import com.yanll.business.auth.domain.UserBeanVO;
import com.yanll.framework.util.exception.BizException;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/11/17.
 */
public interface IUserService {
    public UserBeanVO selectUser(String username, String password) throws BizException;

    public Integer batchInsertFromExcel(List<UserBeanVO> list) throws BizException;
}
