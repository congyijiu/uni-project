package com.uni.pj.users.vos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author congyijiu
 * @create 2023-11-27-16:01
 */

@Data
public class UserInfoVo {


    /**
     * 用户唯一标识符，自动增加
     */
    private Integer id;


    /**
     * 头像URL，最大长度255
     */
    private String avatarUrl;

    /**
     * 爱好，文本字段
     */
    private String hobbies;

    /**
     * 性别，枚举类型
     */
    private Integer gender;

    /**
     * 个性签名，最大长度255
     */
    private String signature;

    /**
     * 电话号码，最大长度20
     */
    private String phoneNumber;

    /**
     * 用户注册日期，默认为当前时间戳
     */
    private LocalDateTime registrationDate;

    /**
     * 用户名，最大长度50
     */
    private String username;

    /**
     * 电子邮件地址，最大长度100
     */
    private String email;

    /**
     * 用户关注数
     */
    private Integer follows;

    /**
     * 用户粉丝数
     */
    private Integer fans;
}
