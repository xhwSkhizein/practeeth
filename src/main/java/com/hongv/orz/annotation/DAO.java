package com.hongv.orz.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hongweixu at 2018/4/6 18:04
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DAO {

    Class<?> modelClass() default Object.class;

    String dataSource();

    String slaveDataSource();
}
