package com.yanll.framework.core.service.poi.excel;

import com.yanll.framework.data.mysql.domain.DataEntity;
import com.yanll.framework.data.mysql.domain.VOEntity;

/**
 * Excel导入数据预处理
 *
 * @param <T>
 * @param <V>
 */
@FunctionalInterface
public interface ExcelImportHandlerCallback<T extends DataEntity, V extends VOEntity> {


    /**
     * 数据校验、VO构建
     *
     * @param error_index Excel出现错误或警告的行索引
     * @param values      根据Excel行解析出的数组
     * @return
     */
    V preHandle(int error_index, String[] values);

}
