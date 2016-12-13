package com.yanll.framework.util.poi.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

/**
 * Created by yanll on 2016/12/7.
 */
public class ExcelUtil {


    public static final String[] excel_suffixes = new String[]{"xls", "xlsx"};


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
        String value = null;
        if (null != cell) {
            // 以下是判断数据的类型
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC: // 数字
                    value = cell.getNumericCellValue() + "";
                    value = BigDecimal.valueOf(Double.parseDouble(value)).stripTrailingZeros().toPlainString();
                    break;
                case Cell.CELL_TYPE_STRING: // 字符串
                    value = cell.getStringCellValue() + "";
                    break;
                case Cell.CELL_TYPE_BOOLEAN: // Boolean
                    value = cell.getBooleanCellValue() + "";
                    break;
                case Cell.CELL_TYPE_FORMULA: // 公式
                    value = cell.getCellFormula() + "";
                    break;
                case Cell.CELL_TYPE_BLANK: // 空值
                    break;
                case Cell.CELL_TYPE_ERROR: // 故障
                    break;
                default:
                    break;
            }
        }
        return value;
    }

    public static String getExtensionName(String filename) {
        if (filename != null && filename.length() > 0) {
            int dot = filename.lastIndexOf('.');
            if (dot > -1 && dot < (filename.length() - 1)) {
                return filename.substring(dot + 1).toLowerCase();
            }
        }
        return null;
    }

    public static boolean isExcel(String file_suffix) {
        if (file_suffix == null) return false;
        for (String s : excel_suffixes) {
            if (file_suffix.equals(s)) {
                return true;
            }
        }
        return false;
    }
}
