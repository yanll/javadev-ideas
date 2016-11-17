package com.yanll.business.auth.service;

import com.yanll.business.auth.dao.UserBeanMapper;
import com.yanll.business.auth.domain.UserBean;
import com.yanll.business.auth.domain.UserBeanVO;
import com.yanll.framework.core.service.mysql.BaseServiceImpl;
import com.yanll.framework.data.mysql.dao.BaseMapper;
import com.yanll.framework.util.exception.BizCode;
import com.yanll.framework.util.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/11/17.
 */
@Service
public class AuthServiceImpl extends BaseServiceImpl<UserBean, UserBeanVO> implements IAuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    @Autowired
    UserBeanMapper userBeanMapper;

    @Override
    public UserBeanVO login(String username, String password) {
        UserBean userBean = userBeanMapper.selectUser(username, password);
        if (userBean == null || userBean.getId() == null)
            throw new BizException(BizCode.LOGIN_FAILD.getValue(), BizCode.LOGIN_FAILD.getDesc());
        return toVO(userBean);
    }

    @Override
    public UserBean getDataEntity() {
        return new UserBean();
    }

    @Override
    public UserBeanVO getVO() {
        return new UserBeanVO();
    }

    @Override
    public BaseMapper<UserBean> getMapper() {
        return userBeanMapper;
    }
}
