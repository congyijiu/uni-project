package com.uni.pj.controller;

import com.uni.pj.common.ResponseResult;
import com.uni.pj.dtos.DynamicActionDto;
import com.uni.pj.service.UserDynamicActionsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author congyijiu
 * @create 2023-11-28-10:53
 */
@RestController
@RequestMapping("/user-dynamic-actions")
@Api(tags = "用户动态行为相关接口")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserDynamicActionsController {

    @Autowired
    private UserDynamicActionsService userDynamicActionsService;

    @PostMapping("/Actions")
    @ApiOperation("用户动态行为")
    public ResponseResult like(@RequestBody DynamicActionDto dynamicActionDto) {
        return userDynamicActionsService.actions(dynamicActionDto);
    }
}
