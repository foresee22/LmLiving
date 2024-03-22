package com.lm.lmliving.commodity.service.impl;

import com.lm.lmliving.commodity.entity.*;
import com.lm.lmliving.commodity.service.*;
import com.lm.lmliving.commodity.vo.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lm.lmliving.common.utils.PageUtils;
import com.lm.lmliving.common.utils.Query;

import com.lm.lmliving.commodity.dao.SpuInfoDao;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {
    @Resource
    private SpuInfoDescService spuInfoDescService;
    @Resource
    private SpuImagesService spuImagesService;

    @Resource
    private AttrService attrService;
    @Resource
    private ProductAttrValueService productAttrValueService;
    @Resource
    private SkuInfoService skuInfoService;
    @Resource
    private SkuImagesService skuImagesService;
    @Resource
    private SkuSaleAttrValueService skuSaleAttrValueService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                new QueryWrapper<SpuInfoEntity>()
        );

        return new PageUtils(page);
    }

    /**
     * 保SpuSaveVO对象/数据到表[会根据业务，将数据分别保存对应表]
     * 涉及多表操作，进行事务管理
     *
     * @param spuSaveVo
     */
    @Override
    @Transactional
    public void saveSpuInfo(SpuSaveVO spuSaveVo) {
        // 1.保存spU基本信息到表commodity_spU_info
        SpuInfoEntity spuInfoEntity = new SpuInfoEntity();
        // 2.将spuSaveVO对象的属性值对拷到spuInfoEntity对象[属性名需要有对应关系]
        BeanUtils.copyProperties(spuSaveVo, spuInfoEntity);
        spuInfoEntity.setCreateTime(new Date());
        spuInfoEntity.setUpdateTime(new Date());
        // 3.将spuInfoEntity保存到commodity_spu_info,这里为了可读性和扩展性
        // 单独的写一个方法。
        this.saveBaseSpuInfo(spuInfoEntity);

        // 保存spu的介绍图片的信息-对应的表commodity_spu_info_desc
        List<String> decript = spuSaveVo.getDecript();
        SpuInfoDescEntity spuInfoDescEntity = new SpuInfoDescEntity();
        // 这里设置给spuInfoDescEntity.对象ispuId就是上面添加spuInfoEntity对应id
        spuInfoDescEntity.setSpuId(spuInfoEntity.getId());
        // 判断decript中有几个图片路径-元素
        if (decript.isEmpty()) { // spU没有对应的介绍图片-设置一个默认图片
            spuInfoDescEntity.setDecript("default.jpg");
        } else { // SpU有对应的介绍图片，就进行遍历，如果有多个图片URL,就使用，间隔
            spuInfoDescEntity.setDecript(String.join(",", decript));
        }
        // 保存spuInfoDescEntity
        spuInfoDescService.saveSpuInfoDesc(spuInfoDescEntity);
        // 保存spU对应的图片集
        // 1.先得到images
        List<String> images = spuSaveVo.getImages();
        // 2.保存spuImagesEntity
        spuImagesService.saveImages(spuInfoEntity.getId(), images);

        //--保SPU对应的基本属性[一个SPU可以有多个基本属性]-将基本属性保存到
        // commodity_product_attr_value
        // 1.取出前端发送过来的基本属性
        List<BaseAttrs> baseAttrs = spuSaveVo.getBaseAttrs();
        // 2.遍历baseAttrs,将数据封装到ProductAttrValueEntity[根据相应的业务要求完成]
        //,通常情况下，对于有综合业务需求处理的遍历，使用流式计算stream
        List<ProductAttrValueEntity> productAttrValueEntities = baseAttrs.stream().map(baseAttr -> {
            ProductAttrValueEntity productAttrValueEntity = new ProductAttrValueEntity();
            productAttrValueEntity.setSpuId(spuInfoEntity.getId());
            productAttrValueEntity.setQuickShow(baseAttr.getShowDesc());
            productAttrValueEntity.setAttrValue(baseAttr.getAttrValues());
            productAttrValueEntity.setAttrSort(0);// 默认值0
            // 这里我门发现前端没有通过json把基本属性名携带到0对象,需要二次处理
            AttrEntity attrEntity = attrService.getById(baseAttr.getAttrId());
            productAttrValueEntity.setAttrName(attrEntity.getAttrName());
            productAttrValueEntity.setAttrId(baseAttr.getAttrId());
            return productAttrValueEntity;
        }).collect(Collectors.toList());
        // 3.将收集到的productAttrValueEntities批量保存到对应的表
        productAttrValueService.saveProductAttr(productAttrValueEntities);

        //--保SKU的基本信息-commodity_sku_info
        // 1.从spuSaveVO对象取出前端通过json发送的sku基本信息数据
        // 前端发送sku信息是多个-对应集合
        List<Skus> skus = spuSaveVo.getSkus();
        // 2.遍历skUs,将skU的基本信息封装到SkuInfoEntity[根据相应的业务要求处理即可]
        // 这里会涉及到业务处理，使用遍历完成
        if (!skus.isEmpty()) {
            skus.forEach(item -> {
                // 先创建SkuInfoEntity
                SkuInfoEntity skuInfoEntity = new SkuInfoEntity();
                skuInfoEntity.setSpuId(spuInfoEntity.getId());
                // 处理默认图片
                String defaultImg = "default.jpg";
                for (Images image : item.getImages()) {
                    if (image.getDefaultImg() == 1) {
                        defaultImg = image.getImgUrl();
                    }
                }
                skuInfoEntity.setSkuDefaultImg(defaultImg);
                skuInfoEntity.setSaleCount(0L);//销量默认)
                skuInfoEntity.setCatalogId(spuInfoEntity.getCatalogId());
                skuInfoEntity.setBrandId(spuInfoEntity.getBrandId());
                skuInfoEntity.setPrice(item.getPrice());
                skuInfoEntity.setSkuTitle(item.getSkuTitle());
                skuInfoEntity.setSkuName(item.getSkuName());
                skuInfoEntity.setSkuSubtitle(item.getSkuSubtitle());
                //保存skU的基本信息到表中commodity_sku_info
                skuInfoService.saveSkuInfo(skuInfoEntity);
                //-保存sku图片集信息到commodity_sku_images
                //-一个sku可以对应多个图片
                //1.从item(sku)取出图片集集合->进行遍历->把数据封装到SkuImagesEntity
                //->进行保存
                //2.在收集skuImagesEntities时，要过滤到imgUrl为空的情况
                List<SkuImagesEntity> skuImagesEntities = item.getImages().stream().map(img -> {
                    SkuImagesEntity skuImagesEntity = new SkuImagesEntity();
                    skuImagesEntity.setImgSort(0);
                    skuImagesEntity.setDefaultImg(img.getDefaultImg());
                    skuImagesEntity.setImgUrl(img.getImgUrl());
                    skuImagesEntity.setSkuId(skuInfoEntity.getSkuId());
                    return skuImagesEntity;
                }).filter(img -> {
                    //过滤掉img对象的imgUrl为空" "的对象
                    return StringUtils.isNotBlank(img.getImgUrl());
                }).collect(Collectors.toList());
                // 2.批量添加
                skuImagesService.saveBatch(skuImagesEntities);
                //-保存sku的销售属性-对应的表是commodity_sku_sale_attr_valve
                //-一个sku可以对应多个销售属性
                //1.得到前端发送的skU的销售属性-集合
                List<Attr> attrs = item.getAttr();
                //2.遍历attrs将数据封装到SkuSaleAttrValueEntity对象中[根据具体的业务需求完成即可]
                List<SkuSaleAttrValueEntity> skuSaleAttrValueEntities = attrs.stream().map(attr -> {
                    SkuSaleAttrValueEntity skuSaleAttrValueEntity = new SkuSaleAttrValueEntity();
                    skuSaleAttrValueEntity.setSkuId(skuInfoEntity.getSkuId());
                    skuSaleAttrValueEntity.setAttrSort(0);//默认排序0
                    skuSaleAttrValueEntity.setAttrId(attr.getAttrId());
                    skuSaleAttrValueEntity.setAttrName(attr.getAttrName());
                    skuSaleAttrValueEntity.setAttrValue(attr.getAttrValue());
                    return skuSaleAttrValueEntity;
                }).collect(Collectors.toList());
                //3.批量保存skuSaleAttrValueEntities
                skuSaleAttrValueService.saveBatch(skuSaleAttrValueEntities);
            });
        }

    }

    /**
     * 保存spu的基本信息
     *
     * @param spuInfoEntity
     */
    @Override
    public void saveBaseSpuInfo(SpuInfoEntity spuInfoEntity) {
        this.baseMapper.insert(spuInfoEntity);
    }

    @Override
    public PageUtils queryPageByCondition(Map<String, Object> params) {
        QueryWrapper<SpuInfoEntity> wrapper = new QueryWrapper<>();
        String key = (String) params.get("key");
        if (StringUtils.isNotBlank(key)){
            wrapper.and(w -> {
                w.eq("id",key).or().like("spu_name",key);
            });
        }
        String status = (String) params.get("status");
        if (StringUtils.isNotBlank(status)){
           wrapper.eq("publish_status",status);
        }
        String brandId = (String) params.get("brandId");
        if (StringUtils.isNotBlank(brandId) && !"0".equalsIgnoreCase(brandId)){
            wrapper.eq("brand_id",brandId);
        }
        String catalogId = (String) params.get("catalogId");
        if (StringUtils.isNotBlank(catalogId) && !"0".equalsIgnoreCase(catalogId)){
            wrapper.eq("catalog_id",catalogId);
        }
        IPage<SpuInfoEntity> page = this.page(
                new Query<SpuInfoEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    public void up(Long supId) {
        this.baseMapper.updateSpuStatus(supId,1);
    }

    @Override
    public void down(Long supId) {
        this.baseMapper.updateSpuStatus(supId,2);
    }

}