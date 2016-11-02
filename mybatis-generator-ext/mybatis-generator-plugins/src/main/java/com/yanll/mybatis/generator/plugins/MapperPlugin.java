package com.yanll.mybatis.generator.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.rules.Rules;

import java.util.List;

public class MapperPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public void initialized(IntrospectedTable introspectedTable) {
        Rules rules = introspectedTable.getRules();
        
        super.initialized(introspectedTable);
    }

    @Override
    public boolean providerInsertSelectiveMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

        System.out.println("222222222222222222222222222");
        System.out.print(method.getName());
        return super.providerInsertSelectiveMethodGenerated(method, topLevelClass, introspectedTable);
    }
}
