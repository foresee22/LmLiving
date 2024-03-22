package com.lm.lmliving.commodity.controller;

import com.lm.lmliving.commodity.service.SkuInfoService;
import com.lm.lmliving.commodity.vo.SearchResult;
import com.lm.lmliving.common.utils.PageUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lm
 * @version 1.0
 */
@Controller
public class SearchController {

    //装配SkuInfoService
    @Resource
    private SkuInfoService skuInfoService;

    /**
     * 1. 家居网前台（购买用户）-检索页面
     * 2. 如果将来检索的时候有检索条件，还需要进行处理..
     * 3. 用户提交的检索条件，封装到 params
     *
     * @return
     */
    @RequestMapping("/list.html")
    public String searchList(@RequestParam Map<String, Object> params, Model model) {
        System.out.println(params);
        //分页查询
        SearchResult searchResult =
                skuInfoService.querySearchPageByCondition(params);
        System.out.println("SearchResult" + searchResult);
        //因为得到searchResult 是需要给html模板使用,
        //将 searchResult 放入model
        model.addAttribute("result", searchResult);

        return "list";
    }
}
