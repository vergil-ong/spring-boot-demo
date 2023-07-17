package com.github.ong.entity.goods;

import com.github.ong.util.NumberUtil;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "goods_category")
public class GoodsCategory {

    public static final Long PARENT_ROOT = NumberUtil.ZERO_L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "update_time")
    private Date updateTime;
}
