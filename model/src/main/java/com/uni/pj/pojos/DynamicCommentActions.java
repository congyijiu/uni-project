package com.uni.pj.pojos;
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
 * @since 2023-11-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("dynamic_comment_actions")
public class DynamicCommentActions implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论行为唯一标识符，自动增加
     */
    @TableId(value = "action_id", type = IdType.AUTO)
    private Integer actionId;

    /**
     * 执行行为的用户的唯一标识符
     */
    private Integer userId;

    /**
     * 目标评论的唯一标识符
     */
    private Integer commentId;

    /**
     * 0表示不点赞，1表示点赞
     */
    private Integer islike;

    /**
     * 行为发生日期，默认为当前时间戳
     */
    private LocalDateTime actionDate;

    /**
     * 逻辑删除标志，0表示未删除，1表示已删除
     */
    private Boolean isDeleted;


}
