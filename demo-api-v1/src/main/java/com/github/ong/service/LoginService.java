package com.github.ong.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.ong.entity.LoginUser;
import com.github.ong.service.common.JsonHelper;
import com.github.ong.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Service
public class LoginService {

    @Resource(name = "loginCache")
    public Cache<String, String> loginCache;

    @Resource
    private JsonHelper jsonHelper;

    public static final String TOKEN_HEADER = "Authtoken";

    public LoginUser getUserByToken(HttpServletRequest request) {
        if (Objects.isNull(request)) {
            return  null;
        }

        String tokenHeader = request.getHeader(TOKEN_HEADER);
        if (StringUtils.isBlank(tokenHeader)){
            return null;
        }
        String cacheData = loginCache.getIfPresent(tokenHeader);
        return jsonHelper.getBean(cacheData, LoginUser.class);
    }

    public String createAndStoreToken(LoginUser loginUser) {
        String jsonStr = jsonHelper.getString(loginUser);
        String token = StringUtil.randomUUID();

        loginCache.put(token, jsonStr);

        return token;
    }
}
