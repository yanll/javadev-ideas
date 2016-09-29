package com.cmp.test.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YLL on 2015/07/28.
 */
public class ClassPathXmlApplicationContext implements BeanFactory {
    private Map<String, Object> beans = new HashMap<String, Object>();

    public ClassPathXmlApplicationContext() throws Exception, Exception {

        beans.put("student", Class.forName("com.cm.core.test.factory.Student").newInstance());
        beans.put("dao", Class.forName("com.cm.core.test.factory.Dao").newInstance());
    }


    @Override
    public Object getBean(String id) {
        return beans.get(id);
    }
}
