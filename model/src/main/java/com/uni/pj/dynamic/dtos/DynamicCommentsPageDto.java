package com.uni.pj.dynamic.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author congyijiu
 * @create 2023-11-26-12:31
 */
@Data
@Schema(name = "动态评论分页查询参数")
public class DynamicCommentsPageDto {
    /**
     * 页码
     */
    @Schema(description = "页码")
    private Integer pageIndex;

    /**
     * 每页条数
     */
    @Schema(description = "每页条数")
    private Integer pageSize;

    /**
     * 动态id
     */
    @Schema(description = "动态id")
    private Integer dynamicId;

    /**
     * 如果为空则为顶层评论，不为空则指向某个顶层评论id
     */
    @Schema(description = "如果为空则为顶层评论，不为空则指向某个顶层评论id")
    private Integer topCommentId;

    /**
     * 查询类型，1-查询动态下评论，2-查询评论下回复
     */
    @Schema(description = "查询类型，1-查询动态下评论，2-查询评论下回复")
    private Integer type;




}
