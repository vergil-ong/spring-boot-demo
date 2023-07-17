package com.github.ong.dao.goods;

import com.github.ong.entity.goods.GoodsCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsCategoryDao extends JpaRepository<GoodsCategory, Long> {

    Page<GoodsCategory> findAllByParentId(Long parentId, Pageable pageable);

    List<GoodsCategory> findAllByParentIdIn(List<Long> parentIdList);
}
