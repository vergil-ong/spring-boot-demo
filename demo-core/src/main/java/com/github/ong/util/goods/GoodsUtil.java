package com.github.ong.util.goods;

import com.github.ong.entity.goods.GoodsCategory;
import com.github.ong.vo.goods.GoodsCategoryVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

public class GoodsUtil {

    public static Page<GoodsCategoryVo> getCategoryPage(Page<GoodsCategory> categoryPage) {
        return new PageImpl<>(
                listCategory(categoryPage.getContent(), null),
                categoryPage.getPageable(),
                categoryPage.getTotalElements());
    }

    public static Page<GoodsCategoryVo> getCategoryPage(Page<GoodsCategory> categoryPage,
                                                        Map<Long, List<GoodsCategory>> categoryGroupMap) {

        return new PageImpl<>(
                listCategory(categoryPage.getContent(), categoryGroupMap),
                categoryPage.getPageable(),
                categoryPage.getTotalElements());
    }

    public static List<GoodsCategoryVo> listCategory(List<GoodsCategory> categoryList,
                                                     Map<Long, List<GoodsCategory>> categoryGroupMap) {
        return categoryList.stream()
                .map(category -> {
                    GoodsCategoryVo goodsCategoryVo = new GoodsCategoryVo();
                    goodsCategoryVo.setGoodsCategory(category);
                    if (Objects.nonNull(categoryGroupMap)) {
                        List<GoodsCategory> subCategoryList = categoryGroupMap.get(category.getId());
                        if (!CollectionUtils.isEmpty(subCategoryList)) {
                            goodsCategoryVo.setHasChildren(true);
                        }
                    }
                    return goodsCategoryVo;
                })
                .collect(Collectors.toList());
    }

    public static Set<Long> getIdSet(Page<GoodsCategory> categoryPage) {
        return categoryPage
                .getContent()
                .stream()
                .map(GoodsCategory::getId)
                .collect(Collectors.toSet());
    }
}
