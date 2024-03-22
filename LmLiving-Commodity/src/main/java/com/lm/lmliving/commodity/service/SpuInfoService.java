package com.lm.lmliving.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lm.lmliving.commodity.vo.SpuSaveVO;
import com.lm.lmliving.common.utils.PageUtils;
import com.lm.lmliving.commodity.entity.SpuInfoEntity;

import java.util.Map;

/**
 * 商品 spu 信息
 *
 * @author lm
 * @email 1802387260@qq.com
 * @date 2023-11-27 15:18:16
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
    void saveSpuInfo(SpuSaveVO spuSaveVo);
    void saveBaseSpuInfo(SpuInfoEntity spuInfoEntity);
    // 带条件的检索
    PageUtils queryPageByCondition(Map<String, Object> params);
    //商品上架
    void up(Long supId);
    //商品下架
    void down(Long supId);
}

