package com.cmp.test.sdk.annotation.a;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by YAN on 2015/08/15.
 */
@Resource
@VOTable("tb")
public class TarResearch {
    @IgnoreProperty
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String version;

    private String grade;

    public static void main(String[] args) {
        Class<TarResearch> userClass = TarResearch.class;
        VOTable ann =TarResearch.class.getAnnotation(VOTable.class);
        System.out.println(ann.value());
        Annotation[] as = (Annotation[]) TarResearch.class.getAnnotations();

        Field[] field = userClass.getDeclaredFields();
        for (int i = 0; i < field.length; i++) {
            Annotation annotation = field[i].getAnnotation(IgnoreProperty.class);

            if (field[i].getAnnotation(IgnoreProperty.class) != null) {
                continue;
            }
            System.out.println(field[i].getName());
        }


    }
}
