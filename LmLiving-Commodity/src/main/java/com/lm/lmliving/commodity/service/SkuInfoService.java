package com.lm.lmliving.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lm.lmliving.commodity.vo.SearchResult;
import com.lm.lmliving.common.utils.PageUtils;
import com.lm.lmliving.commodity.entity.SkuInfoEntity;

import java.util.Map;

/**
 * sku 信息
 *
 * @author lm
 * @email 1802387260@qq.com
 * @date 2023-11-28 13:37:21
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
    void saveSkuInfo(SkuInfoEntity skuInfoEntity);
    PageUtils queryPageByCondition(Map<String, Object> params);
    //返回购买用户检索的结果 PageUtils -> SearchResult
    SearchResult querySearchPageByCondition(Map<String, Object> params);
}

