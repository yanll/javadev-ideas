package com.yanll.business.auth.service;

import com.yanll.business.auth.domain.UserBeanVO;
import com.yanll.framework.util.exception.BizException;

/**
 * Created by Administrator on 2016/11/17.
 */
public interface IAuthService {
    public UserBeanVO login(String username, String password) throws BizException;
}
