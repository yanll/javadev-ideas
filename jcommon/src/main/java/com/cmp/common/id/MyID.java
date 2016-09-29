package com.cmp.common.id;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 全局ID生成机制
 *
 * @author HongjianZ
 */
public class MyID {
    public static UUID uuid = null;

    /**
     * 主键ID：18位
     *
     * @return
     */
    public static Long nextID() {
        if (uuid == null) {
            ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-uuid.xml");
            uuid = (UUID) applicationContext.getBean("uuid");
            applicationContext.close();
        }
        return uuid.nextID();
    }

}
