package com.cmp.test.util.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * Created by YANLL on 2016/05/10.
 */
public class M {


    public static void main(String[] args) {
        try {
            VelocityEngine velocityEngine = new VelocityEngine();
            Properties properties = new Properties();
            String bastPath  ="D:/workspace/projs/jcommon/src/main/java/";
            properties.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH,bastPath);
            velocityEngine.init(properties);
            Velocity.init(properties);
            //初始化vm模板
            Template template = velocityEngine.getTemplate("vm.vm", "UTF-8");
            Template template_ = Velocity.getTemplate("vm.vm", "UTF-8");
            //初始化上下文
            VelocityContext context = new VelocityContext();
            //添加数据到上下文中
            context.put("title", "商品详情");
            context.put("content", "商品详情");
            //生成html页面
            PrintWriter pw = new PrintWriter("/goods_detail.html");
            template.merge(context, pw);
            //关闭流
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        } catch (ParseErrorException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
