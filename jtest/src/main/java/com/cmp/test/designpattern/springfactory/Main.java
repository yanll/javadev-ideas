package com.cmp.test.designpattern.springfactory;

/**
 * Created by YLL on 2015/07/28.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
        Student stu = (Student) context.getBean("student");
        stu.setName("n");
        System.out.println(stu.getName());

        IDao dao = (IDao) context.getBean("dao");
        dao.save("10000");
    }
}
