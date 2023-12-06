package com.uni.pj.dynamic.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author congyijiu
 * @create 2023-11-30-20:08
 */
@Data
@Schema(name = "动态发布参数")
public class DynamicPublishDto {
    /**
     * 动态标题，最大长度100
     */
    @Schema(description = "动态标题")
    private String title;

    /**
     * 动态内容，文本字段
     */
    @Schema(description = "动态内容")
    private String content;

    /**
     * 动态图片的URL集合
     */
    @Schema(description = "动态图片的URL集合")
    private List<String> imageUrls;

    /**
     * 动态类型，默认为1
     */
    @Schema(description = "动态类型，默认为1")
    private Integer typeId;
}
