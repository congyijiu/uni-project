package com.uni.pj.controller.admin;

import com.uni.pj.common.ResponseResult;
import com.uni.pj.dynamic.pojos.DynamicTypes;
import com.uni.pj.service.DynamicTypesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author congyijiu
 * @create 2024-01-12-15:10
 */
@RestController
@RequestMapping("/admin/dynamicType")
@Tag(name = "后台动态类型管理接口")
public class AdminDynamicTypeController {

    @Autowired
    private DynamicTypesService dynamicTypesService;

    @GetMapping("/list/{page}/{size}")
    @Operation(summary = "获取动态类型列表")
    public ResponseResult getDynamicTypeList(@PathVariable("page") Integer page,
                                             @PathVariable("size") Integer size) {
        return dynamicTypesService.adminPageList(page,size);
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "获取动态类型详情")
    public ResponseResult getDynamicType(@PathVariable("id") Long id) {
        return ResponseResult.okResult(dynamicTypesService.getById(id));
    }

    @PutMapping("/info")
    @Operation(summary = "更新动态类型")
    public ResponseResult updateDynamicType(@RequestBody DynamicTypes dynamicTypes) {
        return ResponseResult.okResult(dynamicTypesService.updateById(dynamicTypes));
    }

    @PostMapping("/add")
    @Operation(summary = "添加动态类型")
    public ResponseResult addDynamicType(@RequestBody DynamicTypes dynamicTypes) {
        return ResponseResult.okResult(dynamicTypesService.save(dynamicTypes));
    }

}
