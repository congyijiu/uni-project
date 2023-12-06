package com.uni.pj.users.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.io.Serializable;


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
@TableName("users")
@Schema(name = "用户")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户唯一标识符，自动增加
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名，最大长度50
     */
    @TableField("username")
    private String username;

    /**
     * 电子邮件地址，最大长度100
     */
    @TableField("email")
    private String email;

    /**
     * 密码，最大长度255
     */
    @TableField("password")
    private String password;

    /**
     * 头像URL，最大长度255
     */
    @TableField("avatar_url")
    private String avatarUrl;

    /**
     * 爱好，文本字段
     */
    @TableField("hobbies")
    private String hobbies;

    /**
     * 性别，枚举类型
     */
    @Schema(description = "性别,0表示未知，1表示男，2表示女")
    @TableField("gender")
    private Integer gender;

    /**
     * 个性签名，最大长度255
     */
    @TableField("signature")
    private String signature;

    /**
     * 电话号码，最大长度20
     */
    @TableField("phone_number")
    private String phoneNumber;

    /**
     * 用户注册日期，默认为当前时间戳
     */
    @TableField("registration_date")
    private LocalDateTime registrationDate;

    /**
     * 逻辑删除，0表示未删除，1表示删除
     */
    @TableField("is_delete")
    private Integer isDelete;


}
