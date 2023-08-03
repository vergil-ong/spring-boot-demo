package com.github.ong.controller.goods;

import com.github.ong.entity.goods.GoodsCategory;
import com.github.ong.qo.GoodsQo;
import com.github.ong.service.goods.GoodsCategoryService;
import com.github.ong.util.LogUtil;
import com.github.ong.vo.ResultVo;
import com.github.ong.vo.goods.GoodsCategoryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/goods/category")
public class GoodsCategoryController {

    @Resource
    private GoodsCategoryService goodsCategoryService;

    @GetMapping("/list")
    public ResultVo listCategory(GoodsQo goodsQo) {
        log.info("listCategory qo is {}", LogUtil.logObject(goodsQo));

        Page<GoodsCategoryVo> categoryPage = goodsCategoryService.goodsCategoryPage(goodsQo);
        return ResultVo.createSuccess(categoryPage);
    }

    @PostMapping("/add")
    public ResultVo addCategory(@RequestBody GoodsCategory goodsCategory) {
        log.info("addCategory qo is {}", LogUtil.logObject(goodsCategory));
        goodsCategory.setUpdateTime(new Date());
        GoodsCategory savedEntity = goodsCategoryService.addGoodsCategory(goodsCategory);
        GoodsCategoryVo goodsCategoryVo = new GoodsCategoryVo();
        goodsCategoryVo.setGoodsCategory(savedEntity);
        GoodsCategory parentGoodsCategory = goodsCategoryService.getParentGoodsCategory(savedEntity);
        if (Objects.nonNull(parentGoodsCategory)) {
            goodsCategoryVo.setAddedParentId(parentGoodsCategory.getParentId());
        }

        return ResultVo.createSuccess(goodsCategoryVo);
    }

    @PutMapping("/update")
    public ResultVo updateCategory(@RequestBody GoodsCategory goodsCategory) {
        log.info("updateCategory qo is {}", LogUtil.logObject(goodsCategory));
        goodsCategory.setUpdateTime(new Date());
        GoodsCategory savedEntity = goodsCategoryService.updateGoodsCategory(goodsCategory);
        return ResultVo.createSuccess(savedEntity);
    }

    @DeleteMapping("/{id}")
    public ResultVo deleteCategory(@PathVariable(value = "id") Long id) {
        log.info("deleteCategory qo is {}", id);
        goodsCategoryService.deleteLoop(id);
        return ResultVo.SUCCESS;
    }
}
