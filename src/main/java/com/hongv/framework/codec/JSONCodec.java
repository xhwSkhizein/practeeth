package com.hongv.framework.codec;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by atom on 2017/7/16.
 */
public class JSONCodec {
    static com.fasterxml.jackson.databind.ObjectMapper mapper = null;
    static {
        mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static <T> T decode(String json, TypeReference<T> typeReference) {
        try {
            return mapper.readValue(json, typeReference);
        } catch (Exception e) {
            throw new RuntimeException( //
                    "parse json fail: requiredType=" + typeReference + ", json=" + json, e);
        }
    }

    public static <T> T decode(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException( //
                    "parse json fail: requiredType=" + clazz + ", json=" + json, e);
        }
    }

    public static <T> String encode(T object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException("write json fail: " + object, e);
        }
    }

    public static <T> void encode(T object, OutputStream out) throws IOException {
        try {
            mapper.writeValue(out, object);
        } catch (Exception e) {
            if (e instanceof IOException) {
                throw e;
            }
            throw new RuntimeException("write json fail: " + object, e);
        }
    }
}
