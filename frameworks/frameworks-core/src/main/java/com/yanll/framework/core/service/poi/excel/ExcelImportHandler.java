package com.yanll.framework.core.service.poi.excel;

import com.yanll.framework.data.mysql.domain.VOEntity;
import com.yanll.framework.util.poi.excel.ExcelUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

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
public class ExcelImportHandler<V extends VOEntity> {


    public List<V> handle(String filename, InputStream is, ExcelImportHandlerCallback excelImportPreConfig) {
        if (filename == null || filename.length() == 0) throw new RuntimeException("文件名为空，导入操作失败！");
        if (is == null) throw new RuntimeException("文件流为空，导入操作失败！");
        if (excelImportPreConfig == null) throw new RuntimeException("无预处理参数配置，导入操作失败！");
        Workbook workBook = null;
        try {
            String file_suffix = ExcelUtil.getExtensionName(filename);
            workBook = ExcelUtil.getWeebWork(is, file_suffix);
        } catch (IOException e) {
            throw new RuntimeException("读取文件工作簿失败，导入操作失败！");
        }
        if (workBook == null) throw new RuntimeException("工作簿为空，导入操作失败！");
        Sheet sheet = workBook.getSheetAt(0);
        if (sheet == null) throw new RuntimeException("Sheet为空，导入操作失败！");
        // 获取表格中的数据，按数据行构造VOList对象
        List<V> preExecution = new ArrayList<V>();
        Iterator<Row> it = sheet.rowIterator();
        int row_index = 0;
        int cell_count = 0;
        while (it.hasNext()) {
            Row row = it.next();
            //跳过第一行
            if (row.getRowNum() == 0) {
                row_index++;
                cell_count = row.getLastCellNum();
                continue;
            }
            String[] tmp = new String[cell_count];
            for (int i = 0; i < cell_count; i++) {
                Cell cell = row.getCell(i);
                String value = ExcelUtil.getCellValue(cell);
                if (value == null)
                    throw new RuntimeException("Excel解析空数据异常。【行索引：" + (row_index + 1) + "，列索引：" + (i + 1) + "】");
                tmp[i] = value;
            }
            V v = (V) excelImportPreConfig.preHandle((row_index), tmp);
            row_index++;
            preExecution.add(v);
        }
        return preExecution;
    }

}