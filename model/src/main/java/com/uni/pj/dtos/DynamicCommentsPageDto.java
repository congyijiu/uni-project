package com.uni.pj.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * @author congyijiu
 * @create 2023-11-26-12:31
 */
@Data
@ApiModel("动态评论分页Dto")
public class DynamicCommentsPageDto {
    /**
     * 页码
     */
    @ApiModelProperty("页码")
    private Integer pageIndex;

    /**
     * 每页条数
     */
    @ApiModelProperty("每页条数")
    private Integer pageSize;

    /**
     * 动态id
     */
    @ApiModelProperty("动态id")
    private Integer dynamicId;

    /**
     * 如果为空则为顶层评论，不为空则指向某个顶层评论id
     */
    @ApiModelProperty("如果为空则为顶层评论，不为空则指向某个顶层评论id")
    private Integer topCommentId;

    /**
     * 查询类型，1-查询动态下评论，2-查询评论下回复
     */
    @ApiModelProperty("查询类型，1-查询动态下评论，2-查询评论下回复")
    private Integer type;




}
