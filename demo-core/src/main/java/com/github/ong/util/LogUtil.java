package com.github.ong.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class LogUtil {

    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static final String DEFAULT_NULL = "";

    public static final Integer MAX_LOG_SIZE = 5000;

    public static String logCollection(Collection<?> collection) {
        if (Objects.isNull(collection)) {
            return DEFAULT_NULL;
        }
        return String.valueOf(collection.size());
    }

    public static String logMap(Map<?, ?> map) {
        if (Objects.isNull(map)) {
            return DEFAULT_NULL;
        }
        return String.valueOf(map.size());
    }

    public static String getObjectJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            return DEFAULT_NULL;
        }
    }

    public static String logStr(String logStr, Integer logLength) {
        if (Objects.isNull(logStr)){
            return DEFAULT_NULL;
        }
        if (logStr.length() > logLength) {
            return logStr.substring(0, logLength);
        }
        return logStr;
    }

    public static String logObject(Object object) {
        if (Objects.isNull(object)){
            return DEFAULT_NULL;
        }
        if (object instanceof String){
            return logStr((String) object, MAX_LOG_SIZE);
        }

        if (object instanceof Collection){
            return logCollection((Collection<?>) object);
        }

        if (object instanceof Map) {
            return logMap((Map<?, ?>) object);
        }

        String objectJson = getObjectJson(object);
        return logStr(objectJson, MAX_LOG_SIZE);
    }
}
