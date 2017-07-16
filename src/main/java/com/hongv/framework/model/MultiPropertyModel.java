package com.hongv.framework.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.reflect.TypeToken;
import com.hongv.framework.codec.JSONCodec;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * - 缺点： 需要model继承
 *
 * Created by atom on 2017/7/16.
 */
public abstract class MultiPropertyModel {

    protected TypeToken<Long> TYPE_TOKEN_LONG = TypeToken.of(Long.class);

    protected TypeToken<Float> TYPE_TOKEN_FLOAT = TypeToken.of(Float.class);

    protected TypeToken<List<Long>> TYPE_TOKEN_LONG_LIST = new TypeToken<List<Long>>() {
        private static final long serialVersionUID = 5593866722976691714L;
    };

    private transient volatile Map<String, Object> dataMap;

    protected abstract String getData();

    private static final Logger LOGGER = LoggerFactory.getLogger(MultiPropertyModel.class);

    private Map<String, Object> getDataMap() {
        // convert data to Map if fail return emptyMap.
        if (dataMap == null) {
            synchronized (this) {
                if (dataMap == null) {
                    if (StringUtils.isBlank(getData())) {
                        dataMap = Collections.emptyMap();
                    } else {
                        try {
                            dataMap = JSONCodec.decode(getData(), new TypeReference<Map<String, Object>>() {
                            });
                        } catch (Exception e) {
                            LOGGER.warn("解析JSON失败，使用空Map", e);
                            dataMap = Collections.emptyMap();
                        }
                    }
                }
                return dataMap;
            }
        }
        return dataMap;
    }


    protected <T> T getProperty(ModelPropertyKey<T> propertyKey) {
        Map<String, Object> dataMap = getDataMap();
        if (dataMap == null || dataMap.isEmpty()) {
            return null;
        }
        Object val = dataMap.get(propertyKey.getPersistentName());
        if (val == null) {
            // 取不到值返回默认值
            return propertyKey.getDefaultValue();
        }

        TypeToken<T> valueTypeToken = propertyKey.getValueTypeToken();
        // patch: jackons把未超过int范围的long都转换成int
        if (valueTypeToken.equals(TYPE_TOKEN_LONG)) {
            long longValue = ((Number) val).longValue();
            return (T) Long.valueOf(longValue);
        } else if (valueTypeToken.equals(TYPE_TOKEN_FLOAT)) {
            return (T) Float.valueOf(((Number) val).floatValue());
        } else if (valueTypeToken.equals(TYPE_TOKEN_LONG_LIST)) {
            return (T) ((List<Number>) val).stream()
                    .map(e -> e.longValue()).collect(Collectors.toList());
        }

        // 除了以上特殊处理，其他交给使用者自定义的Converter
        PropertyTypeConverter<T> converter = propertyKey.getConverter();
        return converter != null ? converter.decode(val) : (T) val;
    }
}
