package com.yanll.business.auth.dao;

import com.yanll.business.auth.domain.MenuBean;
import com.yanll.framework.data.mysql.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuBeanMapper extends BaseMapper<MenuBean> {
    public List<MenuBean> selectMenus();
}