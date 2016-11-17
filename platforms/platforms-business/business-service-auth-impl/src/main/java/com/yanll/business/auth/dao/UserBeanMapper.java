package com.yanll.business.auth.dao;

import com.yanll.business.auth.domain.UserBean;
import com.yanll.framework.data.mysql.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserBeanMapper extends BaseMapper<UserBean> {

    public UserBean selectUser(@Param("username") String username, @Param("password") String password);

}