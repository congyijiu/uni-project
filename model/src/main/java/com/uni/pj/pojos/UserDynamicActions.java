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
@TableName("user_dynamic_actions")
public class UserDynamicActions implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 行为唯一标识符，自动增加
     */
    @TableId(value = "action_id", type = IdType.AUTO)
    private Integer actionId;

    /**
     * 执行行为的用户的唯一标识符
     */
    private Integer userId;

    /**
     * 行为目标的唯一标识符（例如，被点赞或被收藏的动态ID）
     */
    private Integer dynamicId;

    /**
     * 行为发生日期，默认为当前时间戳
     */
    private LocalDateTime actionDate;

    /**
     * 是否点赞,0表示没有点赞,1表示点赞
     */
    private Integer islike;

    /**
     * 是否收藏,0表示没有收藏,1表示收藏
     */
    private Integer isfavorite;


}
