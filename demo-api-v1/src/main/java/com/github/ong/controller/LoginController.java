package com.github.ong.controller;

import com.github.ong.qo.LoginQo;
import com.github.ong.util.LogUtil;
import com.github.ong.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @RequestMapping("/user")
    public ResultVo loginUser(@RequestBody LoginQo loginQo) {
        log.info("loginQo is {}", LogUtil.logObject(loginQo));
        String password = loginQo.getPassword();
        if (StringUtils.equals(password, "2")) {
            return ResultVo.SUCCESS;
        }
        return ResultVo.ERROR;
    }
}
