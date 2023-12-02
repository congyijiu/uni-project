package com.uni.pj.controller;

import com.uni.pj.common.ResponseResult;
import com.uni.pj.service.UserFollowsService;
import com.uni.pj.users.dtos.UserFollowsPageDto;
import com.uni.pj.users.dtos.UserLoginDto;
import com.uni.pj.users.dtos.UserRegisterDto;
import com.uni.pj.users.pojo.Users;
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

    @Autowired
    private UserFollowsService userFollowsService;

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


    /**
     * 根据id查询用户信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation("根据id查询用户信息")
    public ResponseResult getUserInfoById(@PathVariable("id") Integer id) {
        return usersService.getUserInfoById(id);
    }

    /**
     *  查询当前登录用户信息
     */
    @GetMapping("/info")
    @ApiOperation("查询当前登录用户信息")
    public ResponseResult getUserInfo() {
        return usersService.getUserInfo();
    }

    /**
     * 修改用户信息
     */
    @PutMapping("/info")
    @ApiOperation("修改用户信息")
    public ResponseResult updateUserInfo(@RequestBody Users users) {
        return usersService.updateUserInfo(users);
    }


    /**
     * 分页查询用户关注列表
     * @param userFollowsPageDto
     */
    @PostMapping("/followsList")
    @ApiOperation("分页查询用户关注列表")
    public ResponseResult getUserFollowsList(@RequestBody UserFollowsPageDto userFollowsPageDto) {
        return usersService.getUserFollowsList(userFollowsPageDto);
    }


    @PostMapping("/follow/{userId}")
    @ApiOperation("关注/取消关注")
    public ResponseResult follow(@PathVariable Integer userId) {
        return userFollowsService.follow(userId);
    }


}
