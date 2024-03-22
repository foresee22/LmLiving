package com.lm.lmliving.commodity.controller;

import java.util.Arrays;
import java.util.Map;

// import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.lm.lmliving.commodity.entity.AttrAttrgroupRelationEntity;
import com.lm.lmliving.commodity.service.AttrAttrgroupRelationService;
import com.lm.lmliving.commodity.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lm.lmliving.commodity.entity.AttrEntity;
import com.lm.lmliving.commodity.service.AttrService;
import com.lm.lmliving.common.utils.PageUtils;
import com.lm.lmliving.common.utils.R;

import javax.annotation.Resource;


/**
 * 商品属性表
 *
 * @author lm
 * @email 1802387260@qq.com
 * @date 2023-11-21 13:15:37
 */
@RestController
@RequestMapping("commodity/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private AttrAttrgroupRelationService attrAttrgroupRelationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("commodity:attr:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }
    /**
     * 列表-根据categoryId+查询条件key完成分页检索基本属性
     */
    @RequestMapping("/base/list/{categoryId}")
    // @RequiresPermissions("commodity:attr:list")
    public R baseAttrList(@RequestParam Map<String, Object> params,
                          @PathVariable("categoryId") Long categoryId){
        PageUtils page = attrService.queryBaseAttrPage(params, categoryId);

        return R.ok().put("page", page);
    }
    /**
     * 列表-根据categoryId+查询条件key完成分页检索销售属性
     */
    @RequestMapping("/sale/list/{categoryId}")
    // @RequiresPermissions("commodity:attr:list")
    public R saleAttrList(@RequestParam Map<String, Object> params,
                          @PathVariable("categoryId") Long categoryId){
        PageUtils page = attrService.querySaleAttrPage(params, categoryId);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
    // @RequiresPermissions("commodity:attr:info")
    public R info(@PathVariable("attrId") Long attrId){
		AttrEntity attr = attrService.getById(attrId);
        Long categoryId = attr.getCategoryId();
        Long[] cascadedCategoryId = categoryService.getCascadedCategoryId(categoryId);

        attr.setCascadedCategoryId(cascadedCategoryId);
        AttrAttrgroupRelationEntity byAttrId = attrAttrgroupRelationService.getByAttrId(attrId);
        if (byAttrId != null) {
            attr.setAttrGroupId(byAttrId.getAttrGroupId());
        }

        return R.ok().put("attr", attr);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("commodity:attr:save")
    public R save(@RequestBody AttrEntity attr){
		// attrService.save(attr);
        attrService.saveAttrAndRelation(attr);
        System.out.println(attr);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("commodity:attr:update")
    public R update(@RequestBody AttrEntity attr){
		attrService.updateAttrAndRelation(attr);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("commodity:attr:delete")
    public R delete(@RequestBody Long[] attrIds){
		attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }

}
