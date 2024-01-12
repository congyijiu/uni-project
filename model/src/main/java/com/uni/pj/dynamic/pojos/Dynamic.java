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
 * @since 2023-11-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("dynamic")
public class Dynamic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 动态唯一标识符，自动增加
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 发动态的用户的唯一标识符
     */
    private Integer userId;

    /**
     * 动态标题，最大长度100
     */
    private String title;

    /**
     * 动态内容，文本字段
     */
    private String content;

    /**
     * 动态图片的URL，最大长度255
     */
    private String imageUrl;

    /**
     * 动态发布日期，默认为当前时间戳
     */
    private LocalDateTime postDate;

    /**
     * 评论数，默认为0
     */
    private Integer commentCount;

    /**
     * 点赞数，默认为0
     */
    private Integer likeCount;

    /**
     * 收藏数，默认为0
     */
    private Integer favoriteCount;

    /**
     * 动态类型，默认为1
     */
    private Integer typeId;

    /**
     * 逻辑删除，0表示未删除，1表示删除
     */
    @TableLogic
    private Integer isDelete;


}
