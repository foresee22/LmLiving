package com.lm.lmliving.commodity.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lm.lmliving.common.utils.PageUtils;
import com.lm.lmliving.common.utils.Query;

import com.lm.lmliving.commodity.dao.BrandDao;
import com.lm.lmliving.commodity.entity.BrandEntity;
import com.lm.lmliving.commodity.service.BrandService;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        //1.通过分析后端的代码，指定如果需要进行带条件查询，就给QueryWrapper设置查询条件和参数
        //2.这里就简单实现一把
        //3.这里演示一下，后面有更好的方案
        // QueryWrapper<BrandEntity> brandEntityQueryWrapper = new QueryWrapper<>();
        // brandEntityQueryWrapper.like("name",params.get("name"));
        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
                new QueryWrapper<BrandEntity>()
                // brandEntityQueryWrapper
        );

        return new PageUtils(page);
    }

}