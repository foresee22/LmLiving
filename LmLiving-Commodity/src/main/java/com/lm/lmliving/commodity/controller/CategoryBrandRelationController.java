package com.lm.lmliving.commodity.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

// import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lm.lmliving.commodity.entity.BrandEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lm.lmliving.commodity.entity.CategoryBrandRelationEntity;
import com.lm.lmliving.commodity.service.CategoryBrandRelationService;
import com.lm.lmliving.common.utils.PageUtils;
import com.lm.lmliving.common.utils.R;



/**
 * 品牌分类关联表
 *
 * @author lm
 * @email 1802387260@qq.com
 * @date 2023-11-20 18:55:24
 */
@RestController
@RequestMapping("commodity/categorybrandrelation")
public class CategoryBrandRelationController {
    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("commodity:categorybrandrelation:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = categoryBrandRelationService.queryPage(params);

        return R.ok().put("page", page);
    }
    /**
     * 列表-根据brandId返回品牌和分类关联的记录
     */
    @RequestMapping("/brand/list")
    // @RequiresPermissions("commodity:categorybrandrelation:list")
    public R categoryBrandListByBrandId(@RequestParam("brandId") Long brandId){
        List<CategoryBrandRelationEntity> brandRelationEntityList = categoryBrandRelationService.list(
                new QueryWrapper<CategoryBrandRelationEntity>().eq("brand_id", brandId));

        return R.ok().put("data", brandRelationEntityList);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    // @RequiresPermissions("commodity:categorybrandrelation:info")
    public R info(@PathVariable("id") Long id){
		CategoryBrandRelationEntity categoryBrandRelation = categoryBrandRelationService.getById(id);

        return R.ok().put("categoryBrandRelation", categoryBrandRelation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("commodity:categorybrandrelation:save")
    public R save(@RequestBody CategoryBrandRelationEntity categoryBrandRelation){
		// categoryBrandRelationService.save(categoryBrandRelation);
        categoryBrandRelationService.saveAll(categoryBrandRelation);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("commodity:categorybrandrelation:update")
    public R update(@RequestBody CategoryBrandRelationEntity categoryBrandRelation){
		categoryBrandRelationService.updateById(categoryBrandRelation);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("commodity:categorybrandrelation:delete")
    public R delete(@RequestBody Long[] ids){
		categoryBrandRelationService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
    /**
     * 根据categoryId.返回管理的品牌信息
     */
    @RequestMapping("/brands/list")
    public R relationBrandsList(@RequestParam("catId") Long cateGoryId){
        List<BrandEntity> brandsByCategoryId = categoryBrandRelationService.getBrandsByCategoryId(cateGoryId);

        return R.ok().put("data",brandsByCategoryId);
    }

}
