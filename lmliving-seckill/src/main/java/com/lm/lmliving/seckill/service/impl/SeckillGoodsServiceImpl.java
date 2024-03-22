package com.lm.lmliving.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lm.lmliving.seckill.mapper.SeckillGoodsMapper;
import com.lm.lmliving.seckill.pojo.SeckillGoods;
import com.lm.lmliving.seckill.service.SeckillGoodsService;
import org.springframework.stereotype.Service;

/**
 * @author lm
 * @version 1.0
 */
@Service
public class SeckillGoodsServiceImpl
        extends ServiceImpl<SeckillGoodsMapper, SeckillGoods>
        implements SeckillGoodsService {
}
