package com.yanll.mybatis.generator.plugins;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.DefaultJavaFormatter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.List;

/**
 * Created by YANLL on 2016/11/4.
 * <p>
 * 基于Mybatis-generator的扩展插件，用于生成Mapper.java、Entity.java、Mapper.xml。
 * 自动生成的代码和手写的代码应该分开文件存放。
 * <p>
 * 鉴于现有开发框架framework-core项目已经针对数据库增删改做了抽象（findById、modify等等），故Mapper.java无需额外生成，直接调用现有即可。
 * <p>
 * <p>
 * 代码生成原则：
 * 生成的不修改，修改的不生成。
 * <p>
 * 后期规划：生成VO、Service、查询扩展
 */
public class MapperPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> list) {
        System.out.println("[INFO] MapperPlugin开始生成Mapper文件...");
        return true;
    }

    @Override
    public void initialized(IntrospectedTable introspectedTable) {
        //修改默认的命名（匹配现有框架的命名格式）
        introspectedTable.setUpdateByPrimaryKeyStatementId("update");
        introspectedTable.setUpdateByPrimaryKeySelectiveStatementId("updateSelective");
        introspectedTable.setDeleteByPrimaryKeyStatementId("deleteById");
        introspectedTable.setSelectByPrimaryKeyStatementId("findById");
        super.initialized(introspectedTable);
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();//数据库表名
        topLevelClass.addJavaDocLine("/*");
        topLevelClass.addJavaDocLine("* " + PluginUtil.WARN);
        topLevelClass.addJavaDocLine("* " + "table:" + tableName);
        topLevelClass.addJavaDocLine("*/");
        //DO默认都增加DataEntity继承
        //topLevelClass.setSuperClass("DataEntity");
        //topLevelClass.addImportedType("com.h2finance.framework.data.mysql.domain.DataEntity");

        //增加serialVersionUID
        topLevelClass.addField(PluginUtil.getSerialVersionUIDField());
        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
    }


    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        //DataEntity已经存在，此处忽略不生成。
        if ("createTime".equals(field.getName()) || "modifyTime".equals(field.getName())) return false;
        return super.modelFieldGenerated(field, topLevelClass, introspectedColumn, introspectedTable, modelClassType);
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        //DataEntity已经存在，此处忽略不生成。
        if ("getCreateTime".equals(method.getName()) || "getModifyTime".equals(method.getName())) return false;
        return super.modelGetterMethodGenerated(method, topLevelClass, introspectedColumn, introspectedTable, modelClassType);
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        //DataEntity已经存在，此处忽略不生成。
        if ("setCreateTime".equals(method.getName()) || "setModifyTime".equals(method.getName())) return false;
        return super.modelGetterMethodGenerated(method, topLevelClass, introspectedColumn, introspectedTable, modelClassType);
    }

    /**
     * 增加deleteByIds方法
     *
     * @param document
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        XmlElement parentElement = document.getRootElement();
        //生成Cache
        parentElement.addElement(0, new TextElement("<cache type=\"org.mybatis.caches.ehcache.EhcacheCache\"/>"));
        parentElement.addElement(0, new TextElement("<!--" + PluginUtil.WARN + "-->"));
        //增加deleteByIds接口的sql元素
        String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();//数据库表名
        XmlElement deleteByIdsElement = new XmlElement("delete");
        deleteByIdsElement.addAttribute(new Attribute("id", "deleteByIds"));
        StringBuffer sql_xml = new StringBuffer();
        sql_xml.append("delete from ").append(tableName).append(" where id IN ");
        sql_xml.append("<foreach collection=\"ids\" item=\"id\" separator=\",\" open=\"(\" close=\")\">");
        sql_xml.append("#{id}");
        sql_xml.append("</foreach>");
        deleteByIdsElement.addElement(new TextElement(sql_xml.toString()));
        parentElement.addElement(deleteByIdsElement);
        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        List<GeneratedJavaFile> files = introspectedTable.getGeneratedJavaFiles();
        if (files == null) return null;
        for (GeneratedJavaFile file : files) {
            CompilationUnit compilationUnit = file.getCompilationUnit();
            String fileEncoding = file.getFileEncoding();
            String targetPackage = file.getTargetPackage();
            String targetProject = file.getTargetProject();
            if (compilationUnit.isJavaInterface()) continue;
            TopLevelClass original = (TopLevelClass) compilationUnit;
            String baseRecordType = introspectedTable.getBaseRecordType() + "VO";
            TopLevelClass newModel = new TopLevelClass(baseRecordType);
            newModel.addJavaDocLine("/*");
            newModel.addJavaDocLine("* " + "当前文件为MybatisGenerator自动生成的VO，请手动剪切到*-service项目。");
            newModel.addJavaDocLine("*/");
            newModel.addImportedTypes(compilationUnit.getImportedTypes());
            newModel.addStaticImports(compilationUnit.getStaticImports());
            newModel.setAbstract(false);
            newModel.setStatic(false);
            newModel.setFinal(false);
            newModel.setVisibility(JavaVisibility.PUBLIC);
            //VO默认都增加VoEntity继承
            newModel.setSuperClass("VoEntity");
            newModel.addImportedType("com.h2finance.framework.data.mysql.domain.VoEntity");
            List<Field> fields = original.getFields();
            if (fields != null) {
                for (Field field : fields) {
                    newModel.addField(field);
                }
            }
            List<Method> methods = original.getMethods();
            if (methods != null) {
                for (Method method : methods) {
                    newModel.addMethod(method);
                }
            }
            GeneratedJavaFile f = new GeneratedJavaFile(newModel, targetProject, fileEncoding, new DefaultJavaFormatter());

            File targetFile;
            String source;
            try {
                DefaultShellCallback callback = new DefaultShellCallback(true);
                File directory = callback.getDirectory(f.getTargetProject(), f.getTargetPackage());
                targetFile = new File(directory, f.getFileName());
                source = f.getFormattedContent();
                PluginUtil.writeFile(targetFile, source, f.getFileEncoding());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
