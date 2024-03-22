package com.lm.lmliving.commodity.service.impl;

import com.lm.lmliving.commodity.entity.AttrEntity;
import com.lm.lmliving.commodity.service.AttrService;
import com.lm.lmliving.commodity.vo.AttrGroupWithAttrsVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lm.lmliving.common.utils.PageUtils;
import com.lm.lmliving.common.utils.Query;

import com.lm.lmliving.commodity.dao.AttrgroupDao;
import com.lm.lmliving.commodity.entity.AttrgroupEntity;
import com.lm.lmliving.commodity.service.AttrgroupService;

import javax.annotation.Resource;


@Service("attrgroupService")
public class AttrgroupServiceImpl extends ServiceImpl<AttrgroupDao, AttrgroupEntity> implements AttrgroupService {
    @Resource
    private AttrService attrService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrgroupEntity> page = this.page(
                new Query<AttrgroupEntity>().getPage(params),
                new QueryWrapper<AttrgroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long categoryId) {
        // 先获取检索传入的关键字
        String key = (String) params.get("key");
        // 根据实际情况，封装查询条件到QueryWrapper对象
        // 这里只是先创建QueryWrapper
        QueryWrapper<AttrgroupEntity> queryWrapper = new QueryWrapper<>();
        // 如果key不为空，就进行条件封装
        if (StringUtils.isNotBlank(key)) {
            // 进行条件封装
            // id就是相等条件，name就是模糊查询
            // queryWrapper.eq("id", key).or().like("name", key);
            // 希望("id", key).or().like("name", key)为一组，就是用大括号包起来
            // (("id", key).or().like("name", key))
            queryWrapper.and((obj) -> {
                obj.eq("id", key).or().like("name", key);
            });
        }
        // 下面在处理是否需要封装categoryId检索条件
        // 这里先设置一个业务规刚：categoryId=0表示在查询属性组的时候，不加categoryId检索条件
        //否则就加入 And categoryId=XX,这里逻辑需求前端代码配合
        if (categoryId != 0){
            queryWrapper.eq("category_id",categoryId);
        }
        //使用renren提供的业务代码
        IPage<AttrgroupEntity> page = this.page(
                new Query<AttrgroupEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }
    // 根据分类categoryId.返回该分类关联的属性组和这些属性组关联的基本属性
    @Override
    public List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCategoryId(Long categoryId) {
        // 根据categoryId得到所有属性组
        List<AttrgroupEntity> attrgroupEntities =
                this.list(new QueryWrapper<AttrgroupEntity>().eq("category_id", categoryId));
        List<AttrGroupWithAttrsVo> attrGroupWithAttrsVos = attrgroupEntities.stream().map(attrgroupEntity -> {
            // 创建一个AttrGroupWithAttrsVo对象
            AttrGroupWithAttrsVo attrGroupWithAttrsVo = new AttrGroupWithAttrsVo();
            // 将attrgroupEntity的属性拷贝到attrGroupWithAttrsVo
            BeanUtils.copyProperties(attrgroupEntity, attrGroupWithAttrsVo);
            // 获取所有基本属性
            List<AttrEntity> relationAttr = attrService.getRelationAttr(attrgroupEntity.getId());
            // 设置到attrGroupWithAttrsVo
            attrGroupWithAttrsVo.setAttrs(relationAttr);
            return attrGroupWithAttrsVo;
        }).collect(Collectors.toList());

        return attrGroupWithAttrsVos;
    }

}