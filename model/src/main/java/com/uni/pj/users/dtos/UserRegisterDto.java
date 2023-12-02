package com.uni.pj.users.dtos;

import lombok.Data;

/**
 * @author congyijiu
 * @create 2023-11-21-15:25
 */
@Data
public class UserRegisterDto {
    private String username;
    private String password;
    private String passwordAgain;
    private String phone;
}
