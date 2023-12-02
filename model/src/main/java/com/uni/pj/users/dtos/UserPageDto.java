package com.uni.pj.users.dtos;

import lombok.Data;

/**
 * @author congyijiu
 * @create 2023-11-29-21:33
 */
@Data
public class UserPageDto {

    /**
     * 当前页
     */
    private Integer pageIndex;

    /**
     * 每页显示条数
     */
    private Integer pageSize;

    /**
     * 用户名
     */
    private String username;



}
