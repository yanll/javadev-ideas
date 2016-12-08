package com.yanll.framework.util.poi.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yanll on 2016/12/7.
 */
public class ExcelUtil {

    public static Workbook getWeebWork(InputStream is, String file_suffix) throws IOException {
        Workbook workbook = null;
        if (null != is) {
            if (file_suffix.toLowerCase().equals("xls")) {
                workbook = new HSSFWorkbook(is);
            }
            if (file_suffix.toLowerCase().equals("xlsx")) {
                workbook = new XSSFWorkbook(is);
            }
        }
        return workbook;
    }

    public static String getCellValue(Cell cell) {
        String value = "";
        if (null != cell) {
            // 以下是判断数据的类型
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC: // 数字
                    value = cell.getNumericCellValue() + "";
                    break;
                case Cell.CELL_TYPE_STRING: // 字符串
                    value = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_BOOLEAN: // Boolean
                    value = cell.getBooleanCellValue() + "";
                    break;
                case Cell.CELL_TYPE_FORMULA: // 公式
                    value = cell.getCellFormula() + "";
                    break;
                case Cell.CELL_TYPE_BLANK: // 空值
                    value = "";
                    break;
                case Cell.CELL_TYPE_ERROR: // 故障
                    value = "非法字符";
                    break;
                default:
                    value = "未知类型";
                    break;
            }
        }
        return value;
    }

    public static String getExtensionName(String filename) {
        if (filename != null && filename.length() > 0) {
            int dot = filename.lastIndexOf('.');
            if (dot > -1 && dot < (filename.length() - 1)) {
                return filename.substring(dot + 1);
            }
        }
        return null;
    }
}
