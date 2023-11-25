package com.uni.pj.dtos;

import lombok.Data;

/**
 * @author congyijiu
 * @create 2023-11-22-17:24
 */

@Data
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
    Integer type;

    /**
     * 关键字
     */
    String keyWord;
}
