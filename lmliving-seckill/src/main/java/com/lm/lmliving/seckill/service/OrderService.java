package com.lm.lmliving.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lm.lmliving.seckill.pojo.Order;
import com.lm.lmliving.seckill.pojo.User;
import com.lm.lmliving.seckill.vo.GoodsVo;

/**
 * @author lm
 * @version 1.0
 */
public interface OrderService extends IService<Order> {

    //方法：秒杀
    Order seckill(User user, GoodsVo goodsVo);

    //方法: 生成秒杀路径/值(唯一)
    String createPath(User user, Long goodsId);

    //方法: 对秒杀路径进行校验
    boolean checkPath(User user, Long goodsId, String path);

    //方法: 验证用户输入的验证码是否正确
    boolean checkCaptcha(User user,Long goodsId, String captcha);
}
