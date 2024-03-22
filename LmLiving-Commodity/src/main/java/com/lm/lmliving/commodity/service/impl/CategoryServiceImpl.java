package com.lm.lmliving.commodity.service.impl;

import com.lm.lmliving.commodity.vo.Catalog2Vo;
import com.lm.lmliving.common.utils.PageUtils;
import com.lm.lmliving.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.lm.lmliving.commodity.dao.CategoryDao;
import com.lm.lmliving.commodity.entity.CategoryEntity;
import com.lm.lmliving.commodity.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {
    // 核心方法，返回所有分类及其子分类（带有层级关系-即树形！）
    // 这里会使用到的java8的流式计算(stream api) + 递归操作（有一定雅度）
    // 学会，可以将其作为一个方案在以后的工作中使用.
    @Override
    public List<CategoryEntity> listTree() {
        // 思路分析步骤
        // 1.查出所有的分类数据
        // baseMapper在ServiceImpl类中有
        List<CategoryEntity> categoryEntities = baseMapper.selectList(null);
        // 2.组装成层级树形结构[使用到java8的stream api+递归操作]
        // 思路
        // 2.1过滤filter，返回1级分类
        // 2.2进行map映射操作，给每个分类设置对应的子分类（这个过程会使用到递归）
        // 2.3进行排序sorted操作
        // 2.4将处理好的数据收集/转换到集合
        // 3.返回带有层级关系数据-即树形

        // filter传入的是Predicate类型的函数式接口，传入一个匿名函数去实现它里面的一个
        // 唯一的抽象方法 boolean test(T t); 即根据传入的t类型，返回一个boolean值,实现了这个方法之后,
        // 就可以用函数式接口调用这个方法,调用这个test方法最终会进入到传入的这个匿名函数进行执行，返回抽象方法要返回的数据.
        // 形参categoryEntity参数类型已经确定为CategoryEntity，因为.filter()方法的形参确定为Predicate<CategoryEntity>
        // 下面的其他方法传入的匿名函数用法类似
        List<CategoryEntity> categoryEntitiesTree = categoryEntities.stream().filter(categoryEntity -> {
            // 2.1过滤filter，返回1级分类
            return categoryEntity.getParentId() == 0;
        }).map(categoryEntity -> {
            // 2.2进行map映射操作，给每个一级分类设置对应的子分类（这个过程会使用到递归）
            categoryEntity.setChildrenCategories(getChildrenCategories(categoryEntity, categoryEntities));
            return categoryEntity;
        }).sorted((categoryEntity1, categoryEntity2) -> {
            // 2.3进行排序sorted操作
            return (categoryEntity1.getSort() == null ? 0 : categoryEntity1.getSort()) -
                    (categoryEntity2.getSort() == null ? 0 : categoryEntity2.getSort());
        }).collect(Collectors.toList());// 2.4将处理好的数据收集/转换到集合
        // 3.返回带有层级关系数据-即树形
        return categoryEntitiesTree;
    }

    // 编写方法 ，返回一级分类下的所有子分类，以及所有子分类的子分类（带树形结构）
    // 也就是递归查询所有分类的子分类
    // 1.该方法的任务就是把root下的所有子分类的层级关系组织好（有多少级，就处理多少级），并返回
    // 2.all就是所有的分类信息[即上个方法categoryEntities]
    public List<CategoryEntity> getChildrenCategories(CategoryEntity root, List<CategoryEntity> all) {
        // 1.过滤，找到所有一级分类的子分类
        // all 下面的元素就是 CategoryEntity 所以filter里面(categoryEntity -> {})写categoryEntity，而不是root
        List<CategoryEntity> childrenCategoriest = all.stream().filter(categoryEntity -> {
            // return categoryEntity.getParentId() == root.getId(); 这段代码有问题
            /*if(l >= -128 && l<=127) {//will cache
                return LongCache.cache[(int)l + offset];
            }
            return new Long(l);
            */
            // Long是包装类 long是基本数据类型，通过源码可以看到，当数值不在（l >= -128 && l<=127）时
            // Long就会返回两个不同的对象，在（l >= -128 && l<=127）时，就会返回数值
            // 所以当parentId大于127时就会出现问题，而==，两边是数值时，就是数值比较，两边是对象时，是对象比较
            // 解决方案1.把两边都转成数值
            // return categoryEntity.getParentId().longValue() == root.getId().longValue();
            // 解决方案2.用equals
            return categoryEntity.getParentId().equals(root.getId());
        }).map(categoryEntity -> { //.map就是对过滤好的list里面的元素进行循环操作（因为前面有循环操作）
            // 找到子分类，并设置，递归操作
            categoryEntity.setChildrenCategories(getChildrenCategories(categoryEntity, all));
            return categoryEntity;
        }).sorted((categoryEntity1, categoryEntity2) -> { // 排序，升序排序
            return (categoryEntity1.getSort() == null ? 0 : categoryEntity1.getSort()) -
                    (categoryEntity2.getSort() == null ? 0 : categoryEntity2.getSort());
        }).collect(Collectors.toList()); // 将处理好的数据收集/转换到集合
        return childrenCategoriest;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 1.该方法返回cascadedCategoryId,数据形式是[1,21,301]
     * 2.这里需要使用到递归，会递归的查找parentId
     *
     * @param categoryId
     * @return
     */
    @Override
    public Long[] getCascadedCategoryId(Long categoryId) {
        // 1.先创建一个集合，把层级关系收集到集合
        List<Long> cascadedCategoryId = new ArrayList<>();
        // 2.调用方法进行处理-递归方法
        // cascadedCategoryId是引用传递，所以直接就影响到本方法的 cascadedCategoryId
        // 返回的数据如[301,21，1]
        getParentCategoryId(categoryId, cascadedCategoryId);
        // 3.将cascadedCategoryId集合进行翻转[301,21,1]=>[1,21,301]
        Collections.reverse(cascadedCategoryId);

        return cascadedCategoryId.toArray(new Long[cascadedCategoryId.size()]);
    }

    @Override
    public List<CategoryEntity> getLevel1Categories() {
        return this.baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_id", 0));
    }

    /**
     * @param selectList 就是所有的分类数据
     * @param parentCId  根据父级的分类Id,返回对应的分类数据
     * @return
     */
    private List<CategoryEntity> getParent_cid
    (List<CategoryEntity> selectList, Long parentCId) {

        // 流式计算-filter
        List<CategoryEntity> categoryEntities = selectList.stream().filter(item -> {
            return item.getParentId().equals(parentCId);
        }).collect(Collectors.toList());
        return categoryEntities;
    }

    /**
     * 返回二级分类(包含三级分类)的数据-按照规定的格式Map<String, List<Catalog2Vo>>
     * 这里会使用到流式计算的 集合->map
     * 有一定难度-有层级关系
     * 分析: 需要一个辅助方法, 就是通过parentId获取对应的下一级的分类数据
     *
     * @return
     */
    @Override
    public Map<String, List<Catalog2Vo>> getCatalogJson() {
        //-先得到所有的分类数据[到数据表查一次]-> 在程序中进行业务处理 -> Map<String, List<Catalog2Vo>>

        //- 得到所有的分类数据[到数据表查一次]
        List<CategoryEntity> selectList =
                this.baseMapper.selectList(null);
        //- 从一级分类开始 -》 二级分类 -》 三级分类 ->

        //- 得到所有的一级分类
        List<CategoryEntity> level1Categories =
                getParent_cid(selectList, 0L);
        //- 遍历一级分类 ---> 最终得到 --》 Map<String, List<Catalog2Vo>>
        //- > 直接使用前面的 Collectors.toMap
        Map<String, List<Catalog2Vo>> categoryMap =
                level1Categories.stream().collect(Collectors.toMap(
                        k -> {
                            return k.getId().toString();
                        }, v -> {
                            List<CategoryEntity> level2Categories = getParent_cid(selectList, v.getId());
                            List<Catalog2Vo> catalog2Vos = new ArrayList<>();
                            if (!level2Categories.isEmpty()) {
                                catalog2Vos = level2Categories.stream().map( l2 -> {
                                    Catalog2Vo catalog2Vo = new Catalog2Vo(v.getId().toString(), null, l2.getId().toString(), l2.getName());
                                    List<CategoryEntity> level3Categories = getParent_cid(selectList, l2.getId());
                                    List<Catalog2Vo.Category3Vo> category3Vos = new ArrayList<>();
                                    if (!level3Categories.isEmpty()) {
                                        category3Vos = level3Categories.stream().map(l3 -> {
                                            Catalog2Vo.Category3Vo category3Vo = new Catalog2Vo.Category3Vo(l2.getId().toString(),l3.getId().toString(),l3.getName());
                                            return category3Vo;

                                        }).collect(Collectors.toList());
                                        catalog2Vo.setCatalog3List(category3Vos);
                                    }


                                    return catalog2Vo;

                                }).collect(Collectors.toList());
                            }
                            return catalog2Vos;
                        }));
        return categoryMap;
    }

    /**
     * 编写方法，更加categoryId递归的查找层级关系
     * ,比如接收到categoryId301->parentId->,···直到parentId=0
     */
    public void getParentCategoryId(Long categoryId, List<Long> cascadedCategoryId) {
        cascadedCategoryId.add(categoryId);
        CategoryEntity category = this.getById(categoryId);
        // parentId为0，表示最顶级的分类，也就是一级分类
        if (!category.getParentId().equals(0L)) {
            getParentCategoryId(category.getParentId(), cascadedCategoryId);
        }
    }

}