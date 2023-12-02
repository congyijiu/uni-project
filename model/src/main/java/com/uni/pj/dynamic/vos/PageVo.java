package com.uni.pj.dynamic.vos;

import lombok.Data;

/**
 * @author congyijiu
 * @create 2023-11-22-20:22
 */

@Data
public class PageVo {

    /**
     * 动态唯一标识符，自动增加
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 封面图片链接
     */
    private String imageUrl;


    /**
     * 点赞数，默认为0
     */
    private Integer likeCount;

    /**
     * 用户名
     */
    private String username;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 是否点赞
     */
    private Integer isLike;

}
