package com.uni.pj.controller.admin;

import com.uni.pj.common.ResponseResult;
import com.uni.pj.dynamic.dtos.AdminDynamicListDto;
import com.uni.pj.service.DynamicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @author congyijiu
 * @create 2024-01-11-10:36
 */
@RestController
@RequestMapping("/admin/dynamic")
@Tag(name = "后台动态管理接口")
public class AdminDynamicController {
    @Autowired
    private DynamicService dynamicService;


    @Operation(summary = "获取动态列表")
    @PostMapping ("/list/{page}/{size}")
    public ResponseResult getDynamicList(@PathVariable("page") Integer page,
                                         @PathVariable("size") Integer size,
                                         @RequestBody AdminDynamicListDto adminDynamicListDto) {
        return dynamicService.getAdminDynamicList(page,size,adminDynamicListDto);
    }


    @Operation(summary = "删除动态")
    @DeleteMapping("/delete/{id}")
    public ResponseResult getDynamicDelete(@PathVariable("id") Long id) {
        return ResponseResult.okResult(dynamicService.removeById(id));
    }

    @Operation(summary = "批量删除动态")
    @DeleteMapping("/batch")
    public ResponseResult batchRemove(@RequestBody Long[] ids) {
        return ResponseResult.okResult(dynamicService.removeByIds(Arrays.asList(ids)));
    }


    @Operation(summary = "查询动态详情")
    @GetMapping("/get/{id}")
    public ResponseResult getDynamic(@PathVariable("id") Long id) {
        return dynamicService.adminDetail(id);
    }


}
