package com.lm.lmliving.commodity.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.lm.lmliving.common.valid.SaveGroup;
import com.lm.lmliving.common.valid.UpdateGroup;
import com.lm.lmliving.common.valid.UpdateIsShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lm.lmliving.commodity.entity.BrandEntity;
import com.lm.lmliving.commodity.service.BrandService;
import com.lm.lmliving.common.utils.PageUtils;
import com.lm.lmliving.common.utils.R;


/**
 * 家居品牌
 *
 * @author lm
 * @email 1802387260@qq.com
 * @date 2023-11-13 13:26:35
 */
@RestController
@RequestMapping("commodity/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    // @RequiresPermissions("commodity:brand:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    // @RequiresPermissions("commodity:brand:info")
    public R info(@PathVariable("id") Long id) {
        BrandEntity brand = brandService.getById(id);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     * 1.@Validated注解的作用就是启用BrandEntity字段校验
     * 2.注解如果没有写@Validated这个校验规则不生效
     * 3.BindingResult result: springboot会将校验的错误放入到result
     * 4.程序员可以通过BindingResult result将校验得到错误取出，然后进行下一步处理
     * 5.因为要将数据校验异常/错误交给全局异常处理器LmlivingExceptionControllerAdvice
     * ,所以这里save方法就注销相关的校验代码.
     * 6.@Validated({SaveGroup.class})的SaveGroup.class用于指定校验组为SaveGroup，使用的是SaveGroup的校验规则
     */
    @RequestMapping("/save")
    // @RequiresPermissions("commodity:brand:save")
    public R save(@Validated({SaveGroup.class}) @RequestBody BrandEntity brand/*, BindingResult result*/) {
        /*  // 创建map，用于存放错误信息
        Map<String, String> map = new HashMap<>();
        if (result.hasErrors()) { // 如果校验有错误
            // 遍历result,将错误信息收集到map
            result.getFieldErrors().forEach((item) -> {
                // 得到filed
                String field = item.getField();
                // 得到校验错误信息
                String defaultMessage = item.getDefaultMessage();
                // 将信息放入map
                map.put(field, defaultMessage);
            });
            return R.error(400, "品牌表单数据不合法").put("data", map);
        } else { // 如果没有校验错误，入库

        }*/
        brandService.save(brand);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // @RequiresPermissions("commodity:brand:update")
    public R update(@Validated({UpdateGroup.class}) @RequestBody BrandEntity brand) {
        brandService.updateById(brand);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // @RequiresPermissions("commodity:brand:delete")
    public R delete(@RequestBody Long[] ids) {
        brandService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
    /**
     * 修改isshow
     */
    @RequestMapping("/update/isshow")
    // @RequiresPermissions("commodity:brand:update")
    public R updateIsShow(@Validated({UpdateIsShow.class}) @RequestBody BrandEntity brand) {
        brandService.updateById(brand);

        return R.ok();
    }

}
