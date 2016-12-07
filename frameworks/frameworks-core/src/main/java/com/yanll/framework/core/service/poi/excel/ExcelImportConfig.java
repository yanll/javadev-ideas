package com.yanll.framework.core.service.poi.excel;

import com.yanll.framework.data.mysql.domain.DataEntity;
import com.yanll.framework.data.mysql.domain.VOEntity;

/**
 * Excel导入配置类
 *
 * @param <T>
 * @param <V>
 */
public interface ExcelImportConfig<T extends DataEntity, V extends VOEntity> {


    /**
     * 获取导入操作回调接口
     *
     * @return {@link ExcelImportCallBack}
     * @see ExcelImportCallBack
     */
    ExcelImportCallBack getExcelImportCallBack();
}
