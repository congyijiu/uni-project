package com.uni.pj.dynamic.pojos;

import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * @since 2023-11-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("dynamic_comments")
public class DynamicComments implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论唯一标识符，自动增加
     */
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Integer commentId;

    /**
     * 评论所属动态的唯一标识符
     */
    private Integer dynamicId;

    /**
     * 评论者用户的唯一标识符
     */
    private Integer userId;

    /**
     * 如果为空则为顶层评论，不为空则指向某个顶层评论id
     */
    private Integer topCommentId;

    /**
     * 回复目标评论id
     */
    private Integer toCommentId;

    /**
     * 评论内容，文本字段
     */
    private String content;

    /**
     * 评论日期，默认为当前时间戳
     */
    private LocalDateTime commentDate;


    /**
     * 评论回复数，默认为0
     */
    private Integer replyCount;

    /**
     * 点赞次数，默认为0
     */
    private Integer likeCount;

    /**
     * 回复用户id
     */
    private Integer replyUserId;

    /**
     * 回复用户名称
     */
    private String replyUsername;

    /**
     * 逻辑删除标志，0表示未删除，1表示已删除
     */
    @TableLogic
    private Integer isDeleted;


}
