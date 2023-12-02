package com.uni.pj.users.vos;

import lombok.Data;

/**
 * @author congyijiu
 * @create 2023-11-29-22:26
 */
@Data
public class UserFollowsPageVos {

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户头像
     */
    private String avatarUrl;

    /**
     * 用户简介
     */
    private String hobbies;
}
