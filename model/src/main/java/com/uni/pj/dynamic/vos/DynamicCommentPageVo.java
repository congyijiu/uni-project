package com.uni.pj.dynamic.vos;

import com.uni.pj.dynamic.pojos.DynamicComments;
import lombok.Data;

/**
 * @author congyijiu
 * @create 2023-11-26-13:31
 */
@Data
public class DynamicCommentPageVo extends DynamicComments {


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
