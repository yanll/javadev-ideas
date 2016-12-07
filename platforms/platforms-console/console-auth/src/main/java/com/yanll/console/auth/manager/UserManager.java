package com.yanll.console.auth.manager;

import com.yanll.business.auth.domain.UserBeanVO;
import com.yanll.business.auth.service.IUserService;
import com.yanll.framework.core.service.poi.excel.ExcelImportPreConfig;
import com.yanll.framework.core.service.poi.excel.ExcelImportPreHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/7.
 */
@Component
public class UserManager {
    private static final Logger logger = LoggerFactory.getLogger(UserManager.class);
    @Autowired
    IUserService userService;

    @Autowired
    ExcelImportPreHandler excelImportPreHandler;

    public void imp(File file) {
        List<UserBeanVO> pre_list = excelImportPreHandler.setExcelImportPreConfig(new ExcelImportPreConfig() {
            @Override
            public String validation(File file) {
                return null;
            }

            @Override
            public UserBeanVO buildVO(Object[] value) {
                UserBeanVO vo = new UserBeanVO();
                vo.setUsername(value[0].toString());
                vo.setNickname(value[1].toString());
                return vo;
            }
        }).handle(file);
        List<UserBeanVO> list = new ArrayList<UserBeanVO>();
        Integer i = userService.batchInsertFromExcel(list);
    }
}
