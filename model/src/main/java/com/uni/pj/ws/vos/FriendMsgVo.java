package com.uni.pj.ws.vos;

import lombok.Data;

/**
 * @author congyijiu
 * @create 2023-12-06-20:40
 */
@Data
public class FriendMsgVo {

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户头像
     */
    private String avatarUrl;

    /**
     * 消息类型（图片：img，文本：text）
     */
    private String type;

    /**
     * 最新消息
     */
    private String content;

    /**
     * 最新消息时间
     */
    private String time;

    /**
     * 未读消息数
     */
    private Integer noreadNum;

}
