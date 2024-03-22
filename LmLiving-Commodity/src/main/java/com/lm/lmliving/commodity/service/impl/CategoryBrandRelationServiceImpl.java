package com.lm.lmliving.commodity.service.impl;

import com.lm.lmliving.commodity.dao.BrandDao;
import com.lm.lmliving.commodity.dao.CategoryDao;
import com.lm.lmliving.commodity.entity.BrandEntity;
import com.lm.lmliving.commodity.entity.CategoryEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lm.lmliving.common.utils.PageUtils;
import com.lm.lmliving.common.utils.Query;

import com.lm.lmliving.commodity.dao.CategoryBrandRelationDao;
import com.lm.lmliving.commodity.entity.CategoryBrandRelationEntity;
import com.lm.lmliving.commodity.service.CategoryBrandRelationService;

import javax.annotation.Resource;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {
    @Resource
    private CategoryDao categoryDao;
    @Resource
    private CategoryBrandRelationDao categoryBrandRelationDao;
    @Resource
    private BrandDao brandDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<CategoryBrandRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveAll(CategoryBrandRelationEntity categoryBrandRelation) {
        // 前端传过来brandId和categoryId保存到了CategoryBrandRelationEntity中
        Long brandId = categoryBrandRelation.getBrandId();
        Long categoryId = categoryBrandRelation.getCategoryId();

        // 得到BrandEntity和CategoryEntity
        BrandEntity brandEntity = brandDao.selectById(brandId);
        CategoryEntity categoryEntity = categoryDao.selectById(categoryId);

        // 将brandName和categoryName保存到CategoryBrandRelationEntity
        categoryBrandRelation.setBrandName(brandEntity.getName());
        categoryBrandRelation.setCategoryName(categoryEntity.getName());

        // 将CategoryBrandRelationEntity保存到数据库中
        this.save(categoryBrandRelation);

    }
    // 根据categoryId返回该分类关联的品牌信息
    @Override
    public List<BrandEntity> getBrandsByCategoryId(Long categoryId) {
        List<Long> longList = null;
        List<BrandEntity> brandEntities = null;
        List<CategoryBrandRelationEntity> brandRelationEntityList =
                categoryBrandRelationDao.selectList(new QueryWrapper<CategoryBrandRelationEntity>().eq("category_id", categoryId));
        if (!brandRelationEntityList.isEmpty()) {
            longList = brandRelationEntityList.stream().map(CategoryBrandRelationEntity::getBrandId).collect(Collectors.toList());
        }
        if (longList != null){
            brandEntities = brandDao.selectBatchIds(longList);
        }

        return brandEntities;
    }

}