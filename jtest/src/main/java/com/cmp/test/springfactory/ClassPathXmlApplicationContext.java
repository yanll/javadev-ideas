package com.cmp.test.springfactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YLL on 2015/07/28.
 */
public class ClassPathXmlApplicationContext implements BeanFactory {
    private Map<String, Object> beans = new HashMap<String, Object>();

    public ClassPathXmlApplicationContext() throws Exception, Exception {

        beans.put("student", Class.forName("com.cm.core.test.springfactory.Student").newInstance());
        beans.put("dao", Class.forName("com.cm.core.test.springfactory.Dao").newInstance());
    }


    @Override
    public Object getBean(String id) {
        return beans.get(id);
    }
}
