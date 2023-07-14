package com.github.ong.service.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ong.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class JsonHelper {
    @Resource
    private ObjectMapper objectMapper;

    public <T> T getBean(String content, Class<T> valueType) {
        try {
            return objectMapper.readValue(content, valueType);
        } catch (Exception e) {
            log.info("JsonHelper getBean Exp {}", ExceptionUtils.getStackTrace(e));
        }
        return  null;
    }

    public String getString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.info("JsonHelper getString Exp {}", ExceptionUtils.getStackTrace(e));
        }
        return StringUtil.BLANK;
    }
}
