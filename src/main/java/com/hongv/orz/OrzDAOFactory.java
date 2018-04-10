package com.hongv.orz;

import com.hongv.orz.annotation.DAO;

import java.lang.annotation.Annotation;

/**
 * Created by hongweixu at 2018/4/6 01:58
 */
public interface OrzDAOFactory<Id, T> {

    static <D> D createDAO(Class<D> beanClass, String beanName, Annotation[] annotations) {



        return (D) null;
    }
}