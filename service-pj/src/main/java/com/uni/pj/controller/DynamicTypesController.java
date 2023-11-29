package com.uni.pj.controller;

import com.uni.pj.common.ResponseResult;
import com.uni.pj.service.DynamicTypesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author congyijiu
 * @create 2023-11-22-21:17
 */
@RestController
@RequestMapping("/dynamicType")
@Api(tags = "动态类型相关接口")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DynamicTypesController {
    @Autowired
    private DynamicTypesService dynamicTypesService;

    @ApiOperation("查询所有动态类型")
    @PostMapping("/list")
    public ResponseResult list(){
        return ResponseResult.okResult(dynamicTypesService.list());
    }
}
