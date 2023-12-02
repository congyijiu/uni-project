package com.uni.pj.users.pojo;

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
@TableName("user_follows")
public class UserFollows implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 关注关系唯一标识符，自动增加
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 关注者用户的唯一标识符
     */
    private Integer followerId;

    /**
     * 被关注者用户的唯一标识符
     */
    private Integer followingId;

    /**
     * 关注日期，默认为当前时间戳
     */
    private LocalDateTime followDate;


}
