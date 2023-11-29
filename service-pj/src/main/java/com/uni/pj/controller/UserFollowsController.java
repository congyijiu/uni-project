package com.uni.pj.controller;

import com.uni.pj.common.ResponseResult;
import com.uni.pj.service.UserFollowsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author congyijiu
 * @create 2023-11-28-15:04
 */
@RestController
@RequestMapping("/user-follows")
@Api(tags = "用户关注接口")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserFollowsController {

    @Autowired
    private UserFollowsService userFollowsService;

    @PostMapping("/follow/{userId}")
    @ApiOperation("关注/取消关注")
    public ResponseResult follow(@PathVariable Integer userId) {
        return userFollowsService.follow(userId);
    }

}
