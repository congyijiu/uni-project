package com.uni.pj.ws.dtos;

import lombok.Data;

/**
 * @author congyijiu
 * @create 2023-12-06-20:18
 */
@Data
public class ChatMessageDto {
    /**
     * 发送用户id
     */
    private Integer sendUserId;

    /**
     * 接手用户id
     */
    private Integer acceptUserId;
    /**
     * 消息类型（图片：img，文本：text）
     */
    private String type;

    /**
     * 发送内容
     */
    private String content;

}
