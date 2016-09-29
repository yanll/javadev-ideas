package com.cmp.test.study;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Date;

/**
 * Created by YAN on 2015/09/01.
 */
public class ConvertUtilsTest {


    public static void main(String[] args) throws Exception {
        String bd = "1991-01-01";
        Person p = new Person();

//        ConvertUtils.register(new DateConverter(null), java.util.Date.class);
        BeanUtils.setProperty(p, "name", "zhangsan");
        System.out.println(p);
    }


}

class Person {

    private String name;
    private Date birthday;

    public Person() {
    }

    public Person(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "name = " + this.name + " " + "birthday = " + this.birthday;
    }
}
