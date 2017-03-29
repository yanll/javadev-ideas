package com.cmp.test;

/**
 * Created by Administrator on 2016/12/30.
 */
public class Main {
    private void change(String s, Integer n, U u) {
        System.out.println(u);
        s = "admin";
        n = 100;
        u.setName("u");
    }

    public static void main(String[] args) {
        //String s = "user";
        String s = new String("user");
        Integer n = new Integer(0);
        U u = new U();
        u.setName("uu");
        System.out.println(u);
        new Main().change(s, n, u);
        //System.out.println(s);
        //System.out.println(n);
        System.out.println(u);
        System.out.println(u.getName());
    }
}

class U {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
