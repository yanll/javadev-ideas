package com.yanll.framework.core.service.poi.excel;

import com.yanll.framework.data.mysql.dao.BaseMapper;

import java.util.List;


public interface ExcelImportCallBack {


    void preOperation(BaseMapper baseMapper, List<Object[]> data);


    void postOperation(BaseMapper baseMapper, List<Object[]> data);
}
