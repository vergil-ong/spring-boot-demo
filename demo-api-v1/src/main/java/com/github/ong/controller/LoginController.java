package com.github.ong.controller;

import com.github.ong.entity.LoginUser;
import com.github.ong.qo.LoginQo;
import com.github.ong.service.LoginService;
import com.github.ong.util.LogUtil;
import com.github.ong.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {

    public static final String DEFAULT_PASSWORD = "123";

    @Resource
    private LoginService loginService;

    @RequestMapping("/user")
    public ResultVo loginUser(@RequestBody LoginQo loginQo) {
        log.info("loginQo is {}", LogUtil.logObject(loginQo));
        String password = loginQo.getPassword();
        if (StringUtils.equals(password, DEFAULT_PASSWORD)) {
            LoginUser loginUser = new LoginUser();
            loginUser.setAccount(loginQo.getAccount());
            String token = loginService.createAndStoreToken(loginUser);
            ResultVo resultVo = ResultVo.SUCCESS;
            resultVo.setData(token);
            return resultVo;
        }
        return ResultVo.ERROR_LOGIN;
    }
}
