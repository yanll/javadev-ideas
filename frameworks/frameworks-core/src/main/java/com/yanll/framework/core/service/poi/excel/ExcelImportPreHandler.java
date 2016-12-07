package com.yanll.framework.core.service.poi.excel;

import com.yanll.framework.data.mysql.domain.VOEntity;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * consumer端的Excel文件预处理类
 *
 * @param <V>
 */
public class ExcelImportPreHandler<V extends VOEntity> {


    private ExcelImportPreConfig excelImportPreConfig;

    private ExcelImportPreConfig getExcelImportPreConfig() {
        return excelImportPreConfig;
    }

    public ExcelImportPreHandler setExcelImportPreConfig(ExcelImportPreConfig excelImportPreConfig) {
        this.excelImportPreConfig = excelImportPreConfig;
        return this;
    }

    public List<V> handle(File file) {
        //获取导入参数配置
        ExcelImportPreConfig excelImportPreConfig = this.getExcelImportPreConfig();
        if (excelImportPreConfig == null) throw new RuntimeException("无预处理参数配置，无法执行导入操作");
        //校验Excel文件合法性
        String error = excelImportPreConfig.validation(file);
        if (null != error && !"".equals(error)) throw new RuntimeException(error);
        // 获取表格中的数据，按数据行构造VOList对象
        List<V> preExecution = new ArrayList<V>();
        Sheet sheet = null;
        //用第一行的列数当做总的数据列数
        int rows = sheet.getLastRowNum();
        int cells = sheet.getRow(0).getLastCellNum();
        for (int i = 1, length = rows; i <= length; i++) {
            Row row = sheet.getRow(i);
            Object[] tmp = new Object[cells];
            for (int j = 0; j < cells; j++) {
                tmp[j] = "";
            }
            V v = (V) excelImportPreConfig.buildVO(tmp);
            preExecution.add(v);
        }
        return preExecution;
    }

}
