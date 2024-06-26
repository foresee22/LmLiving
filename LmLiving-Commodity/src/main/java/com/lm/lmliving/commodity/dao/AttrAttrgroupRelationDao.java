package com.lm.lmliving.commodity.dao;

import com.lm.lmliving.commodity.entity.AttrAttrgroupRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品属性和商品属性组的关联表
 * 
 * @author lm
 * @email 1802387260@qq.com
 * @date 2023-11-21 18:12:00
 */
@Mapper
public interface AttrAttrgroupRelationDao extends BaseMapper<AttrAttrgroupRelationEntity> {
    //增加批量删除属性组和属性的关联关系
    void deleteBatchRelation(@Param("entities") List<AttrAttrgroupRelationEntity> entities);
}
