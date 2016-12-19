package com.yanll.business.auth.service;

import com.yanll.business.auth.domain.MenuBeanVO;
import com.yanll.framework.core.service.mysql.BaseService;
import com.yanll.framework.util.exception.BizException;

import java.util.List;

/**
 * Created by Administrator on 2016/11/17.
 */
public interface IMenuService extends BaseService<MenuBeanVO> {

    public List<MenuBeanVO> selectMenus() throws BizException;

}
