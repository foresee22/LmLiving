package com.lm.lmliving.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lm.lmliving.common.utils.PageUtils;
import com.lm.lmliving.commodity.entity.SkuSaleAttrValueEntity;

import java.util.Map;

/**
 * sku 的销售属性/值表
 *
 * @author lm
 * @email 1802387260@qq.com
 * @date 2023-11-28 17:21:26
 */
public interface SkuSaleAttrValueService extends IService<SkuSaleAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

