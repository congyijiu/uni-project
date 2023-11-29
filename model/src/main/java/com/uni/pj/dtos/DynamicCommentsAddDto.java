package com.uni.pj.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * @author congyijiu
 * @create 2023-11-26-12:09
 */
@Data
@ApiModel("动态评论添加Dto")
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
    @ApiModelProperty("如果为空则为顶层评论，不为空则指向某个顶层评论id")
    private Integer topCommentId;

    /**
     * 回复目标评论id
     */
    @ApiModelProperty("回复目标评论id")
    private Integer toCommentId;
}
