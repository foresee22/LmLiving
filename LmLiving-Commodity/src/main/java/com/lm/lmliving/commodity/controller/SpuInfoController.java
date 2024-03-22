package com.lm.lmliving.commodity.controller;

import java.util.Arrays;
import java.util.Map;

// import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.lm.lmliving.commodity.vo.SpuSaveVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lm.lmliving.commodity.entity.SpuInfoEntity;
import com.lm.lmliving.commodity.service.SpuInfoService;
import com.lm.lmliving.common.utils.PageUtils;
import com.lm.lmliving.common.utils.R;



/**
 * 商品 spu 信息
 *
 * @author lm
 * @email 1802387260@qq.com
 * @date 2023-11-27 15:18:16
 */
@RestController
@RequestMapping("commodity/spuinfo")
public class SpuInfoController {
    @Autowired
    private SpuInfoService spuInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("commodity:spuinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        // PageUtils page = spuInfoService.queryPage(params);
        PageUtils page = spuInfoService.queryPageByCondition(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    // @RequiresPermissions("commodity:spuinfo:info")
    public R info(@PathVariable("id") Long id){
		SpuInfoEntity spuInfo = spuInfoService.getById(id);

        return R.ok().put("spuInfo", spuInfo);
    }

    /**
     * 保存
     * 因为保存的商品信息，涉及到的表非常多，不是SpuInfoEntity实体类可以全部包含的
     * 将SpuInfoEntity改成前面生成的SpuSaveVo对象，将前端提交的json数据全部封装到SpuSaveVo
     * 然后在下面的业务中，进行相应的保存
     */
    @RequestMapping("/save")
    // @RequiresPermissions("commodity:spuinfo:save")
    public R save(@RequestBody SpuSaveVO spuSaveVO){
		// spuInfoService.save(spuInfo);
        spuInfoService.saveSpuInfo(spuSaveVO);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("commodity:spuinfo:update")
    public R update(@RequestBody SpuInfoEntity spuInfo){
		spuInfoService.updateById(spuInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("commodity:spuinfo:delete")
    public R delete(@RequestBody Long[] ids){
		spuInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
    @RequestMapping("/{spuId}/up")
    // @RequiresPermissions("commodity:spuinfo:delete")
    public R spuUp(@PathVariable("spuId") Long spuId){
        spuInfoService.up(spuId);
        return R.ok();
    }
    @RequestMapping("/{spuId}/down")
    // @RequiresPermissions("commodity:spuinfo:delete")
    public R spuDown(@PathVariable("spuId") Long spuId){
        spuInfoService.down(spuId);
        return R.ok();
    }

}
