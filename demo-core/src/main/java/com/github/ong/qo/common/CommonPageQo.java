package com.github.ong.qo.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;

@Getter
@Setter
public class CommonPageQo {

    private Integer page = 0;

    private Integer pageSize = 10;

    public PageRequest getPageRequest() {
        return PageRequest.of(page, pageSize);
    }
}
