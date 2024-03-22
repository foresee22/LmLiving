package com.lm.lmliving.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lm.lmliving.commodity.entity.BrandEntity;
import com.lm.lmliving.common.utils.PageUtils;
import com.lm.lmliving.commodity.entity.CategoryBrandRelationEntity;

import java.util.List;
import java.util.Map;

/**
 * 品牌分类关联表
 *
 * @author lm
 * @email 1802387260@qq.com
 * @date 2023-11-20 18:55:24
 */
public interface CategoryBrandRelationService extends IService<CategoryBrandRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
    // 添加方法，得到brandId和categoryId对应的brandName和categoryName保存到CategoryBrandRelationEntity中
    // 前端传过来brandId和categoryId保存到了CategoryBrandRelationEntity中
    void saveAll(CategoryBrandRelationEntity categoryBrandRelation);
    //根据categoryId返回该分类关联的品牌信息
    List<BrandEntity> getBrandsByCategoryId(Long categoryId);
}

