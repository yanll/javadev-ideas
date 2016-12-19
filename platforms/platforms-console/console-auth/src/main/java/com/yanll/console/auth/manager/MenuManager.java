package com.yanll.console.auth.manager;

import com.yanll.business.auth.domain.MenuBeanVO;
import com.yanll.business.auth.service.IMenuService;
import com.yanll.framework.util.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */
@Component
public class MenuManager {
    private static final Logger logger = LoggerFactory.getLogger(MenuManager.class);
    @Autowired
    IMenuService menuService;


    public void save(MenuBeanVO menu) {
        if (menu == null) throw new BizException("菜单对象空！");
        if (menu.getId() != null && menu.getId() > 0) menuService.updateByPrimaryKeySelective(menu);
        else menuService.insertSelective(menu);

    }

    public List<MenuBeanVO> getMenus() {
        return menuService.selectMenus();
    }
}
