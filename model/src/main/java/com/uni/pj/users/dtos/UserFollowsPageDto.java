package com.uni.pj.users.dtos;

import lombok.Data;

/**
 * @author congyijiu
 * @create 2023-11-29-21:49
 */
@Data
public class UserFollowsPageDto {
    /**
    * 当前页
    */
    private Integer pageIndex;

    /**
    * 每页显示条数
    */
    private Integer pageSize;

    /**
    * 用户id
    */
    private Integer userId;
}
