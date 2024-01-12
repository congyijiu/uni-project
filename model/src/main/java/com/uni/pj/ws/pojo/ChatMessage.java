package com.uni.pj.ws.pojo;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2023-12-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("chat_message")
public class ChatMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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

    /**
     * 是否阅读 (0表示未读，1表示已读)
     */
    private Integer readed;

    /**
     * 是否删除(0表示未删，1表示已删)
     */
    private Integer delete;

    /**
     * 发送时间
     */
    private LocalDateTime sendTime;


}
