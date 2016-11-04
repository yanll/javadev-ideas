package com.yanll.mybatis.generator.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

import java.util.List;

public class MapperPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public void initialized(IntrospectedTable introspectedTable) {
        //修改默认的命名（匹配现有框架的命名格式）
        introspectedTable.setInsertStatementId("add");
        introspectedTable.setInsertSelectiveStatementId("addSelective");
        introspectedTable.setUpdateByPrimaryKeyStatementId("modify");
        introspectedTable.setUpdateByPrimaryKeySelectiveStatementId("modifySelective");
        introspectedTable.setDeleteByPrimaryKeyStatementId("deleteById");
        introspectedTable.setSelectByPrimaryKeyStatementId("findById");
        super.initialized(introspectedTable);
    }
}
