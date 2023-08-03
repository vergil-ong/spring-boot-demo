package com.github.ong.vo.goods;

import com.github.ong.entity.goods.GoodsCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoodsCategoryVo {

    private GoodsCategory goodsCategory;

    private boolean hasChildren = false;

    private Long addedParentId;
}
