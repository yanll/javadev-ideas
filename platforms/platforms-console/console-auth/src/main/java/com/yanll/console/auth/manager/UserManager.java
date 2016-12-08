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
import java.io.InputStream;
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

    public void imp(String filename, InputStream is) {
        List<UserBeanVO> pre_list = excelImportPreHandler.setExcelImportPreConfig(new ExcelImportPreConfig() {
            @Override
            public String validation(File file) {
                if (file == null) return "导入文件为空！";
                return null;
            }

            @Override
            public UserBeanVO buildVO(String[] value) {
                UserBeanVO vo = new UserBeanVO();
                vo.setUsername(value[1]);
                vo.setNickname(value[2]);
                vo.setPassword(value[0]);
                return vo;
            }
        }).handle(filename, is);
        Integer i = userService.batchInsertFromExcel(pre_list);
        System.out.println(i);
    }
}
