package com.hongv.orz.bean;

import com.hongv.orz.OrzDAOFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

/**
 * Created by hongweixu at 2018/4/6 18:01
 */
public class CustomDAOBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (beanClass.isAnnotationPresent(com.hongv.orz.annotation.DAO.class)) {
            return OrzDAOFactory.createDAO(beanClass, beanName, beanClass.getAnnotations());
        }
        return null;
    }
}
