package com.yanll.framework.util.mybatis.generator.plugins;

import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;

import java.io.*;

/**
 * Created by Administrator on 2016/11/8.
 */
public class PluginUtil {
    static final String WARN = "当前文件为MybatisGenerator自动生成，重新生成时会被覆盖，请勿修改！（表结构变化时请重新生成）";

    public static Field getSerialVersionUIDField() {
        Field field = new Field();
        field.setVisibility(JavaVisibility.PRIVATE);
        field.setStatic(true);
        field.setFinal(true);
        field.setType(new FullyQualifiedJavaType("long"));
        field.setName("serialVersionUID");
        field.setInitializationString("1L");
        return field;
    }

    public static void writeFile(File file, String content, String fileEncoding) throws IOException {
        FileOutputStream fos = new FileOutputStream(file, false);
        OutputStreamWriter osw;
        if (fileEncoding == null) {
            osw = new OutputStreamWriter(fos);
        } else {
            osw = new OutputStreamWriter(fos, fileEncoding);
        }
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(content);
        bw.close();
    }
}
