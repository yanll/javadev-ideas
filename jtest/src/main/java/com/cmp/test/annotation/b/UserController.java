package com.cmp.test.annotation.b;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by YAN on 2015/08/15.
 */
public class UserController {

    @Permission(value = "v", ignore = false)
    public void save() {
        System.out.println(this.getClass().getAnnotations().length);
    }

    @Permission
    public void query() {

    }

    public static void main(String[] args) {
        UserController userClass = new UserController();
        userClass.save();
        Annotation[] as = (Annotation[]) userClass.getClass().getAnnotations();
        //UserController.class.getAnnotations()
        Method[] field = UserController.class.getDeclaredMethods();
        for (int i = 0; i < field.length; i++) {
            Annotation[] annotations = field[i].getAnnotations();
            Annotation annotation = field[i].getAnnotation(Permission.class);
            Permission p = (Permission) annotation;
            System.out.println(annotation);
            System.out.println(p);
            if (p != null) {
                System.out.println(p.ignore());
                System.out.println(p.value());
            }
        }
    }
}
