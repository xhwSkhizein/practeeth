package com.hongv.framework.model;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.reflect.TypeToken;

import java.util.Objects;

/**
 * 属性KEY
 * - 所属model
 * - 持久化keyName
 * - 属性值类型
 * - 类型转化器
 * - 默认值
 * Created by atom on 2017/7/16.
 */
public class ModelPropertyKey<T> {

    // 用来做校验，model的属性是否重复定义，持久化中的keyName是否冲突等
    // 持久化时key的名称
    private final String persistentName;
    // 此属性的值类型
    private final TypeToken<T> valueTypeToken;
    // 值转化
    private final PropertyTypeConverter<T> converter;
    // 默认值
    private final T defaultValue;

    // 所有对象共有一份
    private final static Multimap<Class<?>, String> PROPERTY_MAP = HashMultimap.create();

    public ModelPropertyKey(Class<?> modelClass, String persistentName, TypeToken<T> valueTypeToken) {
        check(modelClass, persistentName);
        this.persistentName = persistentName;
        this.valueTypeToken = valueTypeToken;
        this.converter = null;
        this.defaultValue = null;
    }

    public ModelPropertyKey(Class<?> modelClass, String persistentName, TypeToken<T> valueTypeToken, PropertyTypeConverter<T> convertor, T defaultValue) {
        check(modelClass, persistentName);
        this.persistentName = persistentName;
        this.valueTypeToken = valueTypeToken;
        this.converter = convertor;
        this.defaultValue = defaultValue;
    }

    public String getPersistentName() {
        return persistentName;
    }

    public TypeToken<T> getValueTypeToken() {
        return valueTypeToken;
    }

    public PropertyTypeConverter<T> getConverter() {
        return converter;
    }

    public T getDefaultValue() {
        return defaultValue;
    }

    private static void check(Class<?> modelClass, String keyName) {
        Objects.requireNonNull(modelClass);
        Objects.requireNonNull(keyName);

        if (!PROPERTY_MAP.put(modelClass, keyName)) {
            throw new IllegalArgumentException("属性持久化名称已经被使用, modelClass=" + modelClass.getClass().getName() + ", keyName=" + keyName);
        }
    }

}
