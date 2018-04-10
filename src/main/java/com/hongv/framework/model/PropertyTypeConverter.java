package com.hongv.framework.model;

/**
 * 类型转换
 * Created by atom on 2017/7/16.
 */
public interface PropertyTypeConverter<T> {

    T decode(Object rawValue);

    Object encode(T value);
}
