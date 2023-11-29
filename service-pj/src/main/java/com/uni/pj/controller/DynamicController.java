package com.uni.pj.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.uni.pj.common.ResponseResult;
import com.uni.pj.dtos.PageDto;
import com.uni.pj.pojos.Dynamic;
import com.uni.pj.service.DynamicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author congyijiu
 * @create 2023-11-21-15:53
 */


@RestController
@RequestMapping("/dynamic")
@Api(tags = "动态相关接口")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DynamicController {
    @Autowired
    private DynamicService dynamicService;

    /**
     * 分页查询
     * @param pageDto
     * @return
     */
    @ApiOperation("分页查询动态")
    @PostMapping("/page")
    public ResponseResult page(@RequestBody PageDto pageDto){
        return dynamicService.mypage(pageDto);
    }

    /**
     * 查看动态详情
     * @param id
     * @return
     */
    @ApiOperation("查看动态详情")
    @PostMapping("/detail/{dynamicId}")
    public ResponseResult detail(@PathVariable("dynamicId") Long id){
        return dynamicService.detail(id);
    }





}
