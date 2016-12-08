package com.yanll.framework.core.service.poi.excel;

import com.yanll.framework.data.mysql.domain.DataEntity;
import com.yanll.framework.data.mysql.domain.VOEntity;

import java.io.File;

/**
 * Excel导入配置类
 *
 * @param <T>
 * @param <V>
 */
public interface ExcelImportPreConfig<T extends DataEntity, V extends VOEntity> {

    /**
     * 校验Excel数据合法性
     *
     * @param file
     * @return
     */
    String validation(File file);

    /**
     * 根据Excel的Row构造VO
     *
     * @param value
     * @return
     */
    V buildVO(String[] value);
}
