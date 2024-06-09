package com.uni.pj.controller;

import com.uni.pj.common.ResponseResult;
import com.uni.pj.common.enums.AppHttpCodeEnum;
import com.uni.pj.dynamic.dtos.DynamicPublishDto;
import com.uni.pj.dynamic.dtos.FavoritePageDto;
import com.uni.pj.dynamic.dtos.PageDto;
import com.uni.pj.dynamic.pojos.Dynamic;
import com.uni.pj.service.DynamicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author congyijiu
 * @create 2023-11-21-15:53
 */


@RestController
@RequestMapping("/dynamic")
@Tag(name = "动态相关接口")
public class DynamicController {
    @Autowired
    private DynamicService dynamicService;

    /**
     * 分页查询
     * @param pageDto
     * @return
     */
    @Operation(summary = "分页查询动态")
    @PostMapping("/page")
    public ResponseResult page(@RequestBody PageDto pageDto){
        return dynamicService.mypage(pageDto);
    }

    /**
     * 查看动态详情
     * @param id
     * @return
     */
    @Operation(summary = "查看动态详情")
    @PostMapping("/detail/{dynamicId}")
    public ResponseResult detail(@PathVariable("dynamicId") Long id){
        return dynamicService.detail(id);
    }


    /**
     * 发布动态
     * @param publishDto
     * @return
     */
    @Operation(summary = "发布动态")
    @PostMapping("/publish")
    public ResponseResult publish(@RequestBody DynamicPublishDto publishDto){
        return dynamicService.publish(publishDto);
    }

    /**
     * 分页查询用户收藏的动态
     */
    @Operation(summary = "分页查询用户收藏的动态")
    @PostMapping("/favorite/page")
    public ResponseResult favoritePage(@RequestBody FavoritePageDto pageDto){
        return dynamicService.favoritePage(pageDto);
    }

    /**
     * 查询点赞最多的十条动态
     */
    @Operation(summary = "查询点赞最多的十条动态")
    @GetMapping("/top10")
    public ResponseResult top10(){
        return dynamicService.top10();
    }


}
