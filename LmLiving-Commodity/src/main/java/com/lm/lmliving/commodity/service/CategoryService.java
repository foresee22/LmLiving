package com.lm.lmliving.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.lm.lmliving.commodity.entity.CategoryEntity;
import com.lm.lmliving.commodity.vo.Catalog2Vo;
import com.lm.lmliving.common.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 商品分类表
 *
 * @author lm
 * @email 1802387260@qq.com
 * @date 2023-11-08 11:17:10
 */
public interface CategoryService extends IService<CategoryEntity> {
    //返回所有分类及其子分类（带有层级关系-即树形！）
    List<CategoryEntity> listTree();
    PageUtils queryPage(Map<String, Object> params);
    // 返回Long[] cascadedCategoryId，数据的形式[1,21,301]
    // 前端可传categoryId
    Long[] getCascadedCategoryId(Long categoryId);
    // 查询一级分类
    List<CategoryEntity> getLevel1Categories();
    //返回二级分类(包括三级分类)的数据-按照规定的格式
    Map<String,List<Catalog2Vo>> getCatalogJson();
}

