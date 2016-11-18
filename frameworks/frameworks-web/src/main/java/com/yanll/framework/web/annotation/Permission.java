package com.yanll.framework.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by YANLL on 2016/05/29.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Permission {

    /**
     * 描述
     *
     * @return
     */
    String description() default "";

    /**
     * 是否受控（默认受控）
     *
     * @return
     */
    boolean controlled() default true;

}
