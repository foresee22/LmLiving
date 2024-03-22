package com.lm.lmliving.commodity.service.impl;

import com.lm.lmliving.commodity.dao.AttrAttrgroupRelationDao;
import com.lm.lmliving.commodity.dao.AttrgroupDao;
import com.lm.lmliving.commodity.entity.AttrAttrgroupRelationEntity;
import com.lm.lmliving.commodity.entity.AttrgroupEntity;
import com.lm.lmliving.commodity.service.AttrgroupService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lm.lmliving.common.utils.PageUtils;
import com.lm.lmliving.common.utils.Query;

import com.lm.lmliving.commodity.dao.AttrDao;
import com.lm.lmliving.commodity.entity.AttrEntity;
import com.lm.lmliving.commodity.service.AttrService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {
    @Resource
    private AttrAttrgroupRelationDao attrAttrgroupRelationDao;
    @Resource
    private AttrgroupDao attrgroupDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 实现接口，完成保存商品属性（基本属性）的同时，
     * 要需要保存该基本属性和属性组的关联关系
     * 因为该方法涉及到对多表操作[insert],因此需要进行事务控制
     */
    @Transactional
    @Override
    public void saveAttrAndRelation(AttrEntity attrEntity) {
        // 1.先保存基本数据
        this.save(attrEntity);
        // 2.保存商品属性（基本属性）和属性组的关联关系
        if (attrEntity.getAttrType() == 1 && attrEntity.getAttrGroupId() != null) {
            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
            attrAttrgroupRelationEntity.setAttrId(attrEntity.getAttrId());
            attrAttrgroupRelationEntity.setAttrGroupId(attrEntity.getAttrGroupId());
            attrAttrgroupRelationDao.insert(attrAttrgroupRelationEntity);
        }
    }
    @Transactional
    @Override
    public void updateAttrAndRelation(AttrEntity attrEntity) {
        this.updateById(attrEntity);
        AttrAttrgroupRelationEntity attrAttrgroupRelationEntity =
               attrAttrgroupRelationDao.selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrEntity.getAttrId()));
        if (attrEntity.getAttrType() == 1 && attrEntity.getAttrGroupId() != null) {
            if (attrAttrgroupRelationEntity != null) {
                attrAttrgroupRelationEntity.setAttrGroupId(attrEntity.getAttrGroupId());
                attrAttrgroupRelationDao.updateById(attrAttrgroupRelationEntity);
            } else {
                AttrAttrgroupRelationEntity attrAttrgroupRelationEntity1 = new AttrAttrgroupRelationEntity();
                attrAttrgroupRelationEntity1.setAttrId(attrEntity.getAttrId());
                attrAttrgroupRelationEntity1.setAttrGroupId(attrEntity.getAttrGroupId());
                attrAttrgroupRelationDao.insert(attrAttrgroupRelationEntity1);
            }
        }

    }

    // 增加根据分类categoryId+查询条件[key]进行分页检索[基本属性]的API接口
    // 根据自己的业务逻辑，进行定制
    @Override
    public PageUtils queryBaseAttrPage(Map<String, Object> params, Long categoryId) {
        QueryWrapper<AttrEntity> queryWrapper =
                new QueryWrapper<AttrEntity>().eq("attr_type", 1);
        if (categoryId != 0) {
            queryWrapper.eq("category_id", categoryId);
        }
        String key = (String) params.get("key");
        if (StringUtils.isNotBlank(key)) {
            queryWrapper.and((obj) -> {
                obj.eq("attr_id", key).or().like("attr_name", key);
            });
        }
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    // 增加根据分类categoryId+查询条件[key]进行分页检索[销售属性]的API接口
    // 根据自己的业务逻辑，进行定制
    @Override
    public PageUtils querySaleAttrPage(Map<String, Object> params, Long categoryId) {
        QueryWrapper<AttrEntity> queryWrapper =
                new QueryWrapper<AttrEntity>().eq("attr_type", 0);
        if (categoryId != 0) {
            queryWrapper.eq("category_id", categoryId);
        }
        String key = (String) params.get("key");
        if (StringUtils.isNotBlank(key)) {
            queryWrapper.and((obj) -> {
                obj.eq("attr_id", key).or().like("attr_name", key);
            });
        }
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    /**
     * 根据属性组id,返回该属性组关联的商品属性（基本属性）
     */
    @Override
    public List<AttrEntity> getRelationAttr(Long attrgroupId) {
        // 先查到commodity_attr_attrgroup_relation表中eq("attr_group_id", attrgroupId))的所有AttrAttrgroupRelationEntity
        List<AttrAttrgroupRelationEntity> attrAttrgroupRelationEntities =
                attrAttrgroupRelationDao.selectList(
                        new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", attrgroupId));
        // 收集到commodity_attr_attrgroup_relation表中eq("attr_group_id", attrgroupId))的所有AttrAttrgroupRelationEntity的attr_id
        // 例如[1,2,3]
        List<Long> attrIdList = attrAttrgroupRelationEntities.stream().map(AttrAttrgroupRelationEntity::getAttrId).collect(Collectors.toList());
        if (attrIdList.isEmpty()) {
            return null;
        }
        // 返回所有attrId对应的attrEntity
        Collection<AttrEntity> attrEntities = this.listByIds(attrIdList);

        return (List<AttrEntity>) attrEntities;
    }

    @Override
    public void deleteRelation(AttrAttrgroupRelationEntity[] attrAttrgroupRelationEntities) {
        attrAttrgroupRelationDao.deleteBatchRelation(Arrays.asList(attrAttrgroupRelationEntities));
    }

    /**
     * 获取某个属性组可以关联的基本属性
     * 1.如果某个基本属性已经和某个属性组关联了，就不能再关联
     * 2.某个属性组可以关联的基本属性，必须是同一个分类
     * 3.以分类301平板电视为例
     * 4.先找出301下的所有基本属性
     * 5.再找出301下的所有属性组(这里的业务需求是同一个分类下的基本属性和属性组可以随意搭配,但是某个基本属性已经和某个属性组关联了，就不能再关联)
     * 6.如果属性组在commodity_attr_attrgroup_relation表中有记录，说明已经关联了，收集attr_id
     * 7.在基本属性中排除已经关联的基本属性
     */
    @Override
    public PageUtils getAllowRelationAttr(Map<String, Object> params, Long attrGroupId) {
        Long categoryId = null;
        if (attrGroupId != null) {
            AttrgroupEntity attrgroup = attrgroupDao.selectById(attrGroupId);
            categoryId = attrgroup.getCategoryId();
        }


        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<>();
        if (categoryId != null) {
            queryWrapper.eq("category_id", categoryId).eq("attr_type", 1);
        }

        // 排除已经关联的基本属性
        // 1.先在commodity_attrgroup表收集categoryId对应的attr_group_id
        List<AttrgroupEntity> group =
                attrgroupDao.selectList(new QueryWrapper<AttrgroupEntity>().eq("category_id", categoryId));
        List<Long> attrGroupIds = group.stream().map(AttrgroupEntity::getId).collect(Collectors.toList());
        //2.获取到在commodity_attr_attrgroup_relation表中有attrGroupIds的AttrAttrgroupRelationEntities
        // 在获取commodity_attr_attrgroup_relation表中的id，在commodity_attr_attrgroup_relation表中有记录的，说明已经关联了
        // 因为在commodity_attr_attrgroup_relation表记录的就是他们的关联关系
        List<AttrAttrgroupRelationEntity> attrAttrgroupRelationEntities =
                attrAttrgroupRelationDao.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().in("attr_group_id",attrGroupIds));
        List<Long> isRelatedAttrId = attrAttrgroupRelationEntities.stream().map(AttrAttrgroupRelationEntity::getAttrId).collect(Collectors.toList());
        // 再排除掉不在commodity_attr_attrgroup_relation表中有记录的Entities
        if (!attrAttrgroupRelationEntities.isEmpty()) {
            queryWrapper.notIn("attr_id",isRelatedAttrId);
        }
        String key = (String) params.get("key");
        if (StringUtils.isNotBlank(key)) {
            queryWrapper.and((obj) -> {
                obj.eq("attr_id", key).or().like("attr_name", key);
            });
        }
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

}