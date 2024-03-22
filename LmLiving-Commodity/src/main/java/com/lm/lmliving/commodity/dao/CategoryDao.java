package com.lm.lmliving.commodity.dao;

import com.lm.lmliving.commodity.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品分类表
 * 
 * @author lm
 * @email 1802387260@qq.com
 * @date 2023-11-08 11:17:10
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
