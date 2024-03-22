package com.lm.lmliving.commodity.dao;

import com.lm.lmliving.commodity.entity.SpuInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 商品 spu 信息
 * 
 * @author lm
 * @email 1802387260@qq.com
 * @date 2023-11-27 15:18:16
 */
@Mapper
public interface SpuInfoDao extends BaseMapper<SpuInfoEntity> {
    void updateSpuStatus(@Param("spuId") Long spuId , @Param("statusCode") int statusCode);
}
