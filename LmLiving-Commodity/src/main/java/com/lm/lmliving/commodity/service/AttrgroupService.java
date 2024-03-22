package com.lm.lmliving.commodity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lm.lmliving.commodity.vo.AttrGroupWithAttrsVo;
import com.lm.lmliving.common.utils.PageUtils;
import com.lm.lmliving.commodity.entity.AttrgroupEntity;

import java.util.List;
import java.util.Map;

/**
 * 家居商品属性分组
 *
 * @author lm
 * @email 1802387260@qq.com
 * @date 2023-11-18 16:42:30
 */
public interface AttrgroupService extends IService<AttrgroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    //增加根据分类（第3级分类）+查询条件[key和categoryId]+分页的API接口
    //根据自己的业务逻辑，进行定制
    PageUtils queryPage(Map<String, Object> params,Long categoryId);

    /**
     * 根据分类categoryId.返回该分类关联的属性组和这些属性组关联的基本属性
     */
    List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCategoryId(Long categoryId);
}

