package com.github.ong.controller;

import com.github.ong.dao.TestDao;
import com.github.ong.entity.Test;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Resource
    private TestDao testDao;

    @RequestMapping("/test1")
    public String test1(String data) {
        if (StringUtils.isNotBlank(data)) {
            return "hello "+ data;
        }
        return "hello test1";
    }

    @RequestMapping("/list")
    public List<Test> listTest() {
        return testDao.findAll();
    }
}
