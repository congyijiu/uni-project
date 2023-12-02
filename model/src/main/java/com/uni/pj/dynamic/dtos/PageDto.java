package com.uni.pj.dynamic.dtos;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "动态类型")
    Integer type;

    /**
     * 关键字
     */
    @ApiModelProperty(value = "关键字")
    String keyWord;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "当查询某个用户的动态时，需要传入用户id")
    Integer userId;

    /**
     * 查询该用户的动态，还是查询该用户的收藏的动态
     */
    @ApiModelProperty(value = "0:查询该用户的动态，1:查询该用户的收藏的动态")
    Integer queryType;

}
