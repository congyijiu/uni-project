package com.uni.pj.dynamic.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author congyijiu
 * @create 2023-11-22-17:24
 */

@Data
@Schema(name = "分页查询参数")
public class PageDto {
    // 分页查询

    /**
     * 页码
     */
    Integer pageIndex;

    /**
     * 每页条数
     */
    Integer pageSize;

    /**
     * 动态类型
     */
    @Schema(description = "动态类型")
    Integer type;

    /**
     * 关键字
     */
    @Schema(description = "关键字")
    String keyWord;

    /**
     * 用户id
     */
    @Schema(description = "当查询某个用户的动态时，需要传入用户id")
    Integer userId;

}
