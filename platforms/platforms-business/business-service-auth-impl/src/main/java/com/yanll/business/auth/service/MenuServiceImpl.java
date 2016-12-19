package com.yanll.business.auth.service;

import com.yanll.business.auth.dao.MenuBeanMapper;
import com.yanll.business.auth.domain.MenuBean;
import com.yanll.business.auth.domain.MenuBeanVO;
import com.yanll.framework.core.service.mysql.BaseServiceImpl;
import com.yanll.framework.data.mysql.dao.BaseMapper;
import com.yanll.framework.util.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/11/17.
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuBean, MenuBeanVO> implements IMenuService {

    private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
    @Autowired
    MenuBeanMapper menuBeanMapper;

    @Override
    public List<MenuBeanVO> selectMenus() throws BizException {
        List<MenuBean> list_ = menuBeanMapper.selectMenus();
        List<MenuBeanVO> list = toVOList(list_);
        return list;
    }

    @Override
    public MenuBean getDataEntity() {
        return new MenuBean();
    }

    @Override
    public MenuBeanVO getVO() {
        return new MenuBeanVO();
    }

    @Override
    public BaseMapper<MenuBean> getMapper() {
        return menuBeanMapper;
    }
}
