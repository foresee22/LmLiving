package com.lm.lmliving.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lm.lmliving.common.utils.PageUtils;
import com.lm.lmliving.commodity.entity.SkuImagesEntity;

import java.util.Map;

/**
 * sku 图片
 *
 * @author lm
 * @email 1802387260@qq.com
 * @date 2023-11-28 16:11:29
 */
public interface SkuImagesService extends IService<SkuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

