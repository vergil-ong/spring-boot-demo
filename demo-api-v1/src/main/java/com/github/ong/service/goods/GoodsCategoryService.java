package com.github.ong.service.goods;

import com.github.ong.dao.goods.GoodsCategoryDao;
import com.github.ong.entity.goods.GoodsCategory;
import com.github.ong.qo.GoodsQo;
import com.github.ong.qo.common.CommonPageQo;
import com.github.ong.util.NumberUtil;
import com.github.ong.util.goods.GoodsUtil;
import com.github.ong.vo.goods.GoodsCategoryVo;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GoodsCategoryService {

    @Resource
    private GoodsCategoryDao goodsCategoryDao;

    public Page<GoodsCategoryVo> goodsCategoryPage(GoodsQo goodsQo) {
        Long parentId = goodsQo.getParentId();
        if (Objects.isNull(parentId)) {
            parentId = GoodsCategory.PARENT_ROOT;
        }
        Page<GoodsCategory> categoryPage = goodsCategoryDao.findAllByParentId(parentId, goodsQo.getPageRequest());
        if (categoryPage.getTotalElements() <= NumberUtil.ZERO_L) {
            return GoodsUtil.getCategoryPage(categoryPage);
        }
        Set<Long> idSet = GoodsUtil.getIdSet(categoryPage);
        Map<Long, List<GoodsCategory>> categoryGroupMap = goodsCategoryDao.findAllByParentIdIn(new ArrayList<>(idSet))
                .stream()
                .collect(Collectors.groupingBy(GoodsCategory::getParentId));

        return GoodsUtil.getCategoryPage(categoryPage, categoryGroupMap);
    }

    public GoodsCategory addGoodsCategory(GoodsCategory goodsCategory) {
        return goodsCategoryDao.saveAndFlush(goodsCategory);
    }

    public GoodsCategory updateGoodsCategory(GoodsCategory goodsCategory) {
        return goodsCategoryDao.saveAndFlush(goodsCategory);
    }

    public void deleteLoop(Long id) {
        GoodsCategory goodsCategory = goodsCategoryDao.getReferenceById(id);
        CommonPageQo commonPageQo = new CommonPageQo();
        Page<GoodsCategory> categoryPage = goodsCategoryDao.findAllByParentId(id, commonPageQo.getPageRequest());
        goodsCategoryDao.delete(goodsCategory);
        if (categoryPage.getTotalElements() <= NumberUtil.ZERO_L) {
            return;
        }

        Set<Long> deleteIdSet = new HashSet<>();
        Set<Long> parentIdSet = new HashSet<>();
        for (int i = 0; i < categoryPage.getTotalPages(); i++) {
            commonPageQo.setPage(i);
            categoryPage = goodsCategoryDao.findAllByParentId(id, commonPageQo.getPageRequest());
            Set<Long> currentIdSet = GoodsUtil.getIdSet(categoryPage);
            deleteIdSet.addAll(currentIdSet);
            parentIdSet.addAll(currentIdSet);
        }

        while (parentIdSet.size() > 0) {
            Set<Long> tempIdSet = new HashSet<>();
            parentIdSet.forEach( parentId -> {
                CommonPageQo pageQo = new CommonPageQo();
                Page<GoodsCategory> categoryPageForParent = goodsCategoryDao.findAllByParentId(parentId, pageQo.getPageRequest());

                if (categoryPageForParent.getTotalElements() <= NumberUtil.ZERO_L) {
                    return;
                }
                categoryPageForParent
                        .getContent()
                        .stream()
                        .map(GoodsCategory::getId)
                        .forEach(categoryId -> {
                            deleteIdSet.add(categoryId);
                            tempIdSet.add(categoryId);
                        });
            });
            parentIdSet = tempIdSet;
        }

        if (!CollectionUtils.isEmpty(deleteIdSet)){
            goodsCategoryDao.deleteAllById(deleteIdSet);
        }
    }

    public GoodsCategory getParentGoodsCategory(GoodsCategory goodsCategory) {
        Long parentId = goodsCategory.getParentId();
        if (Objects.isNull(parentId) || Objects.equals(parentId, GoodsCategory.PARENT_ROOT)) {
            return null;
        }
        return goodsCategoryDao.getReferenceById(parentId);
    }
}
