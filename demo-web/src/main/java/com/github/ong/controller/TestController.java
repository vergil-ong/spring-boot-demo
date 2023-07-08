package com.github.ong.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @RequestMapping("/test1")
    public String test1(String data) {
        if (StringUtils.isNotBlank(data)) {
            return "hello "+ data;
        }
        return "hello test1";
    }
}
