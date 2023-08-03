package com.github.ong.vo;

import com.github.ong.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultVo {

    public static final Integer STATUS_SUCCESS = 200;

    public static final Integer STATUS_ERROR = 500;

    public static final Integer STATUS_NO_AUTH = 401;

    public static final ResultVo SUCCESS = new ResultVo(STATUS_SUCCESS, StringUtil.BLANK, null);

    public static final ResultVo ERROR = new ResultVo(STATUS_ERROR, StringUtil.BLANK, null);

    public static final ResultVo ERROR_LOGIN = new ResultVo(STATUS_NO_AUTH, "登录失败或过期", null);

    public static ResultVo createError(String message) {
        return new ResultVo(STATUS_ERROR, message, null);
    }

    public static ResultVo createSuccess(Object data, String message) {
        return new ResultVo(STATUS_SUCCESS, message, data);
    }

    public static ResultVo createSuccess(Object data) {
        return createSuccess(data, null);
    }

    private Integer status;

    private String message;

    private Object data;
}
