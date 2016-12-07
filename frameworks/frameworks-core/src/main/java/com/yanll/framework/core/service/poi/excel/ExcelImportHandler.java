package com.yanll.framework.core.service.poi.excel;

import com.yanll.framework.core.service.mysql.BaseServiceImpl;
import com.yanll.framework.data.mysql.dao.BaseMapper;
import com.yanll.framework.data.mysql.domain.DataEntity;
import com.yanll.framework.data.mysql.domain.VOEntity;

import java.util.List;


public abstract class ExcelImportHandler<T extends DataEntity, V extends VOEntity> extends BaseServiceImpl {


    private BaseMapper<T> baseMapper;
    private ExcelImportConfig importConfig;

    private ExcelImportConfig getImportConfig() {
        return importConfig;
    }

    public ExcelImportHandler setImportConfig(ExcelImportConfig importConfig) {
        this.importConfig = importConfig;
        return this;
    }


    public void handle(List<V> src) {
        List<T> list = toVOList(src);
        //获取导入参数配置
        ExcelImportConfig importConfig = this.getImportConfig();
        if (importConfig == null) throw new RuntimeException("无后处理参数配置，无法执行导入操作");
        importConfig.
        T e = (T) list.get(0);
        baseMapper.insert(e);
    }

}
