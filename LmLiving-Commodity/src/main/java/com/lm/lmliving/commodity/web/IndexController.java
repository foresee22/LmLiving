package com.lm.lmliving.commodity.web;

import com.lm.lmliving.commodity.entity.CategoryEntity;
import com.lm.lmliving.commodity.service.CategoryService;
import com.lm.lmliving.commodity.vo.Catalog2Vo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    @Resource
    private CategoryService categoryService;
    @RequestMapping(value = {"/","/index.html"})
    public String indexPage(Model model){
        List<CategoryEntity> level1Categories = categoryService.getLevel1Categories();
        model.addAttribute("categories",level1Categories);
        return "index";
    }
    //测试-返回json数据
    @GetMapping(value = "/index/catalog.json")
    @ResponseBody
    public Map<String,List<Catalog2Vo>> getCatalogJson() {

        Map<String, List<Catalog2Vo>> catalogJson =
                categoryService.getCatalogJson();

        return catalogJson;
    }
}
