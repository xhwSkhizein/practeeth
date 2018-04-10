package com.hongv.orz.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hongweixu at 2018/4/6 18:12
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GetByCursor {
    Class<?> offsetType() default Long.class;

    String offsetField() default "id";

    boolean asc() default false;

    String pageSize() default "limit";
}

