package com.hongv.framework.model;

import com.hongv.framework.codec.JSONCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by atom on 2017/7/16.
 */
public class ModelPropertyBuilder {

    private final static Logger logger = LoggerFactory.getLogger(ModelPropertyBuilder.class);

    public static Builder newBuilder() {
        return new Builder();
    }
    static class Builder {

        private Map<String, Object> dataMap;

        /**
         * 用于新构建
         */
        public Builder() {
            dataMap = new HashMap<>();
        }

        public Builder load(String oldData){
            dataMap.putAll(JSONCodec.decode(oldData, Map.class));
            return this;
        }

        /**
         * 增加/替换一个key的值
         *
         * @param key
         * @param value 可以传null，不会写进dataMap
         * @return
         */
        public <T> Builder add(ModelPropertyKey<T> key, T value) {
            if (value != null) {
                Object raw = key.getConverter() != null ? key.getConverter().encode(value) : value;
                if (raw != null) {
                    logger.trace("add value: {}, {}", key.getPersistentName(), raw);
                    dataMap.put(key.getPersistentName(), raw);
                }
            }
            return this;
        }

        /**
         * 删除
         *
         * @param key
         * @return
         */
        public <T> Builder del(ModelPropertyKey<T> key) {
            logger.trace("remove key: {}", key.getPersistentName());
            dataMap.remove(key.getPersistentName());
            return this;
        }

        /**
         * 是否包含key
         *
         * @param key
         * @return
         */
        public <T> boolean containsKey(ModelPropertyKey<T> key) {
            return dataMap.containsKey(key.getPersistentName());
        }

        public Map<String, Object> build() {
            return dataMap;
        }

        public String buildString() {
            return JSONCodec.encode(dataMap);
        }
    }
}
