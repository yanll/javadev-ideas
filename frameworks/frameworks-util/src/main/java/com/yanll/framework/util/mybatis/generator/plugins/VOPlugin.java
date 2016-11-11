package com.yanll.framework.util.mybatis.generator.plugins;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.DefaultJavaFormatter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.List;

/**
 * Created by YANLL on 2016/11/4.
 * 生成VO
 */
public class VOPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> list) {
        System.out.println("[INFO] MapperPlugin开始生成VO文件...");
        return true;
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
            newModel.setSuperClass("VOEntity");
            newModel.addImportedType("com.yanll.framework.data.mysql.domain.VOEntity");
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
