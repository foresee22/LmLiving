package com.lm.lmliving.commodity.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.lm.lmliving.commodity.entity.AttrAttrgroupRelationEntity;
import com.lm.lmliving.commodity.entity.AttrEntity;
import com.lm.lmliving.commodity.service.AttrAttrgroupRelationService;
import com.lm.lmliving.commodity.service.AttrService;
import com.lm.lmliving.commodity.service.CategoryService;
import com.lm.lmliving.commodity.vo.AttrGroupWithAttrsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lm.lmliving.commodity.entity.AttrgroupEntity;
import com.lm.lmliving.commodity.service.AttrgroupService;
import com.lm.lmliving.common.utils.PageUtils;
import com.lm.lmliving.common.utils.R;

import javax.annotation.Resource;


/**
 * 家居商品属性分组
 *
 * @author lm
 * @email 1802387260@qq.com
 * @date 2023-11-18 16:42:30
 */
@RestController
@RequestMapping("commodity/attrgroup")
public class AttrgroupController {
    @Autowired
    private AttrgroupService attrgroupService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private AttrService attrService;
    @Resource
    private AttrAttrgroupRelationService attrAttrgroupRelationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("commodity:attrgroup:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrgroupService.queryPage(params);

        return R.ok().put("page", page);
    }
    /**
     * 列表
     * 1.根据业务需求，增加根据分类（第3级分类）+查询条件+分页的API接口/方法
     */
    @RequestMapping("/list/{categoryId}")
    // @RequiresPermissions("commodity:attrgroup:list")
    public R list(@RequestParam Map<String, Object> params,@PathVariable("categoryId") Long categoryId){
        PageUtils page = attrgroupService.queryPage(params,categoryId);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    // @RequiresPermissions("commodity:attrgroup:info")
    public R info(@PathVariable("id") Long id){
		AttrgroupEntity attrgroup = attrgroupService.getById(id);
        Long categoryId = attrgroup.getCategoryId();

        Long[] cascadedCategoryId = categoryService.getCascadedCategoryId(categoryId);
        attrgroup.setCascadedCategoryId(cascadedCategoryId);

        return R.ok().put("attrgroup", attrgroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("commodity:attrgroup:save")
    public R save(@RequestBody AttrgroupEntity attrgroup){
		attrgroupService.save(attrgroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("commodity:attrgroup:update")
    public R update(@RequestBody AttrgroupEntity attrgroup){
		attrgroupService.updateById(attrgroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("commodity:attrgroup:delete")
    public R delete(@RequestBody Long[] ids){
		attrgroupService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
    /**
     * 根据属性组id,返回该属性组关联的基本属性
     */
    @RequestMapping("/{attrgroupId}/attr/relation")
    // @RequiresPermissions("commodity:attrgroup:delete")
    public R attrRelation(@PathVariable("attrgroupId") Long attrgroupId){
        List<AttrEntity> relationAttrs = attrService.getRelationAttr(attrgroupId);

        return R.ok().put("data",relationAttrs);
    }
    /**
     * 响应批量删除属性组和属性的关联关系
     */
    @RequestMapping("/attr/relation/delete")
    // @RequiresPermissions("commodity:attrgroup:delete")
    public R deleteAttrRelation(@RequestBody AttrAttrgroupRelationEntity[] attrAttrgroupRelationEntities){
        attrService.deleteRelation(attrAttrgroupRelationEntities);

        return R.ok();
    }
    /**
     * 根据attrgroupId返回可以关联的基本属性
     */
    @RequestMapping("/{attrGroupId}/attr/allowrelation")
    public R attrAllowRelation(@RequestParam Map<String,Object> params ,
                               @PathVariable("attrGroupId") Long attrGroupId) {
        PageUtils allowRelationAttr = attrService.getAllowRelationAttr(params, attrGroupId);
        return R.ok().put("page",allowRelationAttr);
    }
    /**
     * 批量添加属性组和属性（基本属性）的关联关系
     * 前端传来的是[{attrId:1,attrGroupId:2},{attrId:2,attrGroupId:3}]数组
     */
    @RequestMapping("/attr/relation")
    public R addRelation(@RequestBody List<AttrAttrgroupRelationEntity> attrAttrgroupRelationEntities) {
        attrAttrgroupRelationService.saveBatch(attrAttrgroupRelationEntities);
        return R.ok();
    }
    /**
     * 获取某个分类的所有属性组及其关联的基本属性
     */
    @RequestMapping("/{catalogId}/withattr")
    public R getAttrGroupWithAttrs(@PathVariable("catalogId") Long categoryId){
        List<AttrGroupWithAttrsVo> attrGroupWithAttrsByCategoryId = attrgroupService.getAttrGroupWithAttrsByCategoryId(categoryId);
        return R.ok().put("data",attrGroupWithAttrsByCategoryId);
    }


}
