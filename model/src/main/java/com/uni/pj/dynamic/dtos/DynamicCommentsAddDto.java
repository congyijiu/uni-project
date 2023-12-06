package com.uni.pj.dynamic.dtos;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author congyijiu
 * @create 2023-11-26-12:09
 */
@Data
@Schema(name = "DynamicCommentsAddDto", description = "动态评论添加Dto")
public class DynamicCommentsAddDto {

    /**
     * 所属动态id
     */
    private Integer dynamicId;

//    /**
//     * 评论者id
//     */
//    private Integer userId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 如果为空则为顶层评论，不为空则指向某个顶层评论id
     */
    @Schema(description = "如果为空则为顶层评论，不为空则指向某个顶层评论id")
    private Integer topCommentId;

    /**
     * 回复目标评论id
     */
    @Schema(description = "回复目标评论id")
    private Integer toCommentId;
}
