package com.uni.pj.ws.vos;

import lombok.Data;

/**
 * @author congyijiu
 * @create 2023-12-18-15:02
 */
@Data
public class MessageVo {
    /**
     * 发送用户id
     */
    private Integer sendUserId;
    /**
     * 消息类型（图片：img，文本：text）
     */
    private String type;

    /**
     * 发送内容
     */
    private String content;

    /**
     * 发送图片的base64编码
     */
    private String imageBase64;
}
