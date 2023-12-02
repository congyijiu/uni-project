package com.uni.pj.dynamic.dtos;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author congyijiu
 * @create 2023-11-30-20:08
 */
@Data
public class DynamicPublishDto {
    /**
     * 动态标题，最大长度100
     */
    @ApiModelProperty("动态标题")
    private String title;

    /**
     * 动态内容，文本字段
     */
    @ApiModelProperty("动态内容")
    private String content;

    /**
     * 动态图片的URL集合
     */
    @ApiModelProperty("动态图片的URL集合")
    private List<String> imageUrls;

    /**
     * 动态类型，默认为1
     */
    @ApiModelProperty("动态类型，默认为1")
    private Integer typeId;
}
