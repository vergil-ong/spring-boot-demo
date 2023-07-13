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

    public static final ResultVo SUCCESS = new ResultVo(STATUS_SUCCESS, StringUtil.BLANK, null);

    public static final ResultVo ERROR = new ResultVo(STATUS_ERROR, StringUtil.BLANK, null);

    private Integer status;

    private String message;

    private Object data;
}
