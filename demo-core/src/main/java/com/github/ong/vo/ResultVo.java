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

    public static final Integer SUCCESS_STATUS = 200;

    public static final ResultVo SUCCESS = new ResultVo(SUCCESS_STATUS, StringUtil.BLANK, null);

    private Integer status;

    private String message;

    private Object data;
}
