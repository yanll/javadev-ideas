package com.yanll.framework.core.service.poi.excel;

import com.yanll.framework.data.mysql.domain.VOEntity;
import com.yanll.framework.util.poi.excel.ExcelUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
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

    public List<V> handle(String filename, InputStream is) {
        if (filename == null || filename.length() == 0) throw new RuntimeException("文件名为空，无法执行导入操作");
        if (is == null) throw new RuntimeException("文件流为空，无法执行导入操作");
        //获取导入参数配置
        ExcelImportPreConfig excelImportPreConfig = this.getExcelImportPreConfig();
        if (excelImportPreConfig == null) throw new RuntimeException("无预处理参数配置，无法执行导入操作");
        Workbook workBook = null;
        try {
            String file_suffix = ExcelUtil.getExtensionName(filename);
            workBook = ExcelUtil.getWeebWork(is, file_suffix);
        } catch (IOException e) {
            throw new RuntimeException("读取文件工作簿失败，无法执行导入操作");
        }
        if (workBook == null || workBook.getSheetAt(0) == null) throw new RuntimeException("读取工作簿Sheet失败，无法执行导入操作");
        Sheet sheet = workBook.getSheetAt(0);
        //校验sheet合法性
        String error = excelImportPreConfig.validation(new File(""));
        if (null != error && !"".equals(error)) throw new RuntimeException(error);
        // 获取表格中的数据，按数据行构造VOList对象
        List<V> preExecution = new ArrayList<V>();
        Iterator<Row> it = sheet.rowIterator();
        while (it.hasNext()) {
            Row row = it.next();
            //跳过第一行
            if (row.getRowNum() == 0) {
                continue;
            }
            int cell_count = row.getLastCellNum();
            String[] tmp = new String[cell_count];
            Iterator<Cell> cell_it = row.cellIterator();
            int i = 0;
            while (cell_it.hasNext()) {
                Cell cell = cell_it.next();
                String value = ExcelUtil.getCellValue(cell);
                tmp[i++] = value;
            }
            V v = (V) excelImportPreConfig.buildVO(tmp);
            preExecution.add(v);
        }
        return preExecution;
    }

}
