package com.lm.lmliving.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lm.lmliving.common.utils.PageUtils;
import com.lm.lmliving.commodity.entity.ProductAttrValueEntity;

import java.util.List;
import java.util.Map;

/**
 * spu 基本属性值
 *
 * @author lm
 * @email 1802387260@qq.com
 * @date 2023-11-28 12:21:21
 */
public interface ProductAttrValueService extends IService<ProductAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);
    // 保存基本属性，支持批量添加
    void saveProductAttr(List<ProductAttrValueEntity> productAttrValueEntities);
}

