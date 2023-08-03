package com.github.ong.interceptor;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.ong.entity.LoginUser;
import com.github.ong.service.LoginService;
import com.github.ong.service.common.JsonHelper;
import com.github.ong.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Objects;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private LoginService loginService;

    @Resource
    private JsonHelper jsonHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof ResourceHttpRequestHandler) {
            return  true;
        }

        String method = request.getMethod();
        if (StringUtils.equals(method, HttpMethod.OPTIONS.name())) {
            return true;
        }

        LoginUser loginUser = loginService.getUserByToken(request);
        if (Objects.nonNull(loginUser)){
            return true;
        }
        log.info("loginUser is null");

        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "*");
        try (PrintWriter writer = response.getWriter()){
            writer.write(jsonHelper.getString(ResultVo.ERROR_LOGIN));
            writer.flush();
        }

        return false;
    }
}
