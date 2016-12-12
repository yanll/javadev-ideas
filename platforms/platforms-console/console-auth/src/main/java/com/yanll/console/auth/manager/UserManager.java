package com.yanll.console.auth.manager;

import com.yanll.business.auth.domain.UserBeanVO;
import com.yanll.business.auth.service.IUserService;
import com.yanll.framework.core.service.poi.excel.ExcelImportHandler;
import com.yanll.framework.util.jackson.UtilJackson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    ExcelImportHandler<UserBeanVO> excelImportPreHandler;

    public void imp(String filename, InputStream is) {
        List<UserBeanVO> pre_list = excelImportPreHandler.handle(filename, is, (error_index, values) -> {
            UserBeanVO vo = new UserBeanVO();
            try {
                vo.setId(Long.parseLong(values[0]));
                vo.setUsername(values[1]);
                vo.setNickname(values[2]);
                vo.setPassword("pwd");
                if (vo.getUsername().length() > 6) {
                    throw new RuntimeException("第" + error_index + "行第" + 2 + "列：名称长度不能大于6个字符！");
                }
                System.out.println(UtilJackson.toJSON(vo));
            } catch (Exception e) {
                throw new RuntimeException("第" + error_index + "行解析出错！");
            }
            return vo;
        });
        Integer i = userService.batchInsertFromExcel(pre_list);
        System.out.println(i);
    }
}
