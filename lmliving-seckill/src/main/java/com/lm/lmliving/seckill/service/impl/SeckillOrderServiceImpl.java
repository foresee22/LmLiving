package com.lm.lmliving.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lm.lmliving.seckill.pojo.SeckillOrder;
import com.lm.lmliving.seckill.mapper.SeckillOrderMapper;
import com.lm.lmliving.seckill.service.SeckillOrderService;
import org.springframework.stereotype.Service;

/**
 * @author lm
 * @version 1.0
 */
@Service
public class SeckillOrderServiceImpl
        extends ServiceImpl<SeckillOrderMapper, SeckillOrder>
        implements SeckillOrderService {
}
