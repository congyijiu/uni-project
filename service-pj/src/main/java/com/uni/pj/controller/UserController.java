package com.uni.pj.controller;

import com.uni.pj.common.ResponseResult;
import com.uni.pj.dtos.UserLoginDto;
import com.uni.pj.dtos.UserRegisterDto;
import com.uni.pj.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author congyijiu
 * @create 2023-11-21-11:34
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户相关接口")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private UsersService usersService;

    /**
     * 登录
     * @param userLoginDto
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("登录")
    public ResponseResult login(@RequestBody UserLoginDto userLoginDto) {
        return usersService.login(userLoginDto);
    }


    /**
     * 注册
     * @param userRegisterDto
     * @return
     */
    @PostMapping("/register")
    @ApiOperation("注册")
    public ResponseResult register(@RequestBody UserRegisterDto userRegisterDto) {
        return usersService.register(userRegisterDto);
    }
}
