package com.lm.lmliving.commodity.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.lm.lmliving.common.valid.*;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * 家居品牌
 *
 * @author lm
 * @email 1802387260@qq.com
 * @date 2023-11-13 13:26:35
 */
@Data
@TableName("commodity_brand")
public class BrandEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     * 1.@NotNull(message="修改要求指定id",groups={UpdateGroup.class.})
     * 表示@NotNull 在UpdateGroup校验组生效
     * 2 @Null(message="添加不能指定id",groups={SaveGroup.class})
     * 表示@NuLL在SaveGroup校验组生效
     * 3.使用了分组校验后，在你要校验的字段都要指定group，如果不指定group，编写的校验规则会失效
     * 4.groups 可以指定多个校验组
     */
    @TableId
    @NotNull(message = "修改要求指定id", groups = {UpdateGroup.class, UpdateIsShow.class})
    @Null(message = "添加不能指定id", groups = {SaveGroup.class})
    private Long id;
    /**
     * 品牌名
     * 1.NotBlank表示name必须包括一个非空字符
     * 2.message="品牌名不能为空"是指定的一个校验消息
     * 3.如果没有指定message="品牌名不能为空"，就会返回默认的校验消息key=javax.validation.constraints.NotBlank.message
     * 4.这个默认的消息是在ValidationMessages_zhCN.properties文件配置的
     * 5.@NotBlank可以用于CharSequence
     */
    @NotBlank(message = "品牌名不能为空",groups = {UpdateGroup.class, SaveGroup.class})
    private String name;
    /**
     * logo
     * 因为这个logo对应的是图片url
     * 1.@NotBlank和@URL组合去校验logo
     * 2.@NotBlank(message = "logo不能为空",groups = {SaveGroup.class})表示只在SaveGroup组生效，修改时在后端sql传值为null或者不传值时，表示不修改该字段，保持原来的值
     * 所以修改可以不进行@NotBlank校验
     */
    @NotBlank(message = "logo不能为空",groups = {SaveGroup.class})
    @URL(message = "logo不是一个合法的URL",groups = {UpdateGroup.class, SaveGroup.class})
    private String logo;
    /**
     * 说明
     */
    private String description;
    /**
     * 显示
     * 显示，isshow后面的s是小写
     * 1.这里使用的注解是@NotNull,他可以接收任意类型
     * 2.如果这里使用@NotBlank,会报错，因为@NotBlank不支特扛Integer
     * 3.同学门在开发时，需要知道注解可以用在哪些类型上，可以查看注解源码
     * 4.说明，假如isshow规定是0/1，这时我门后面通过自定义校验器来解决..
     * 5.如果是String类型，可以直接使用@Pattern来进一步校验
     */
    @NotNull(message = "显示状态不能为空",groups = {UpdateGroup.class, SaveGroup.class, UpdateIsShow.class})
    @EnumValidate(values = {0,1},message = "显示状态值只能传入0或1",groups = {UpdateGroup.class, SaveGroup.class, UpdateIsShow.class})
    // @EnumZhengzeValidate(regexp = "^[0-1]$",groups = {UpdateGroup.class, SaveGroup.class})
    private Integer isshow;
    /**
     * 检索首字母
     * 检索首字母 a-Z A-Z
     * 因为firstLetter是String 可以直接使用@Pattern
     */
    @NotBlank(message = "检索首字母不能为空",groups = {SaveGroup.class})
    @Pattern(regexp = "^[a-zA-Z]$", message = "检索首字母必须是a-Z或者A-Z",groups = {UpdateGroup.class, SaveGroup.class})
    private String firstLetter;
    /**
     * 排序
     */
    @NotNull(message = "排序值不能为空",groups = {SaveGroup.class})
    @Min(value = 0, message = "排序值要求大于等于0",groups = {UpdateGroup.class, SaveGroup.class})
    private Integer sort;

}
