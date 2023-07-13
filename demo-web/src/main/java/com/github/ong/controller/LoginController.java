package com.github.ong.controller;

import com.github.ong.qo.LoginQo;
import com.github.ong.util.LogUtil;
import com.github.ong.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @RequestMapping("/user")
    public ResultVo loginUser(LoginQo loginQo) {
        log.info("loginQo is {}", LogUtil.logObject(loginQo));

        return ResultVo.SUCCESS;
    }
}
