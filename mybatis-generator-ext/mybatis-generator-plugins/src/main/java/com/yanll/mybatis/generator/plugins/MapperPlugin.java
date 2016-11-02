package com.yanll.mybatis.generator.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

public class MapperPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> list) {
        return false;
    }

    @Override
    public boolean providerInsertSelectiveMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

        System.out.print(method.getName());
        return super.providerInsertSelectiveMethodGenerated(method, topLevelClass, introspectedTable);
    }
}
