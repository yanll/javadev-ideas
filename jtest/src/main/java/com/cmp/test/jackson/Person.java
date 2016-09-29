package com.cmp.test.jackson;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by YAN on 2015/09/01.
 */
public class Person {

    private String name;
    private int age;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date birthday;

    private Date d;

    public Person() {
    }

    public Person(String name, int age, Date birthday, Date d) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.d = d;
    }

    public Person(String name, Date birthday, Date d) {
        this.name = name;
        this.birthday = birthday;
        this.d = d;
    }

    public Person(int age, Date birthday, Date d) {
        this.age = age;
        this.birthday = birthday;
        this.d = d;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getD() {
        return d;
    }

    public void setD(Date d) {
        this.d = d;
    }
}
