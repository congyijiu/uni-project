package com.uni.pj.controller.admin;

import com.uni.pj.common.ResponseResult;
import com.uni.pj.dynamic.dtos.AdminDynamicCommentsPageDto;
import com.uni.pj.service.DynamicCommentsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @author congyijiu
 * @create 2024-01-12-15:36
 */
@RestController
@RequestMapping("/admin/dynamicComment")
@Tag(name = "后台动态评论管理", description = "动态评论管理")
public class AdminDynamicCommentController {
    @Autowired
    private DynamicCommentsService dynamicCommentsService;


    @PostMapping("/list/{page}/{size}")
    @Operation(summary = "分页查询动态评论列表")
    public ResponseResult getDynamicCommentList(@PathVariable("page") Integer page,
                                                @PathVariable("size") Integer size,
                                                @RequestBody AdminDynamicCommentsPageDto pageDto) {
        return dynamicCommentsService.adminPageList(page,size,pageDto);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "删除动态评论")
    public ResponseResult deleteDynamicComment(@PathVariable("id") Long id) {
        return ResponseResult.okResult(dynamicCommentsService.removeById(id));
    }

    @DeleteMapping("/deleteBatch")
    @Operation(summary = "批量删除动态评论")
    public ResponseResult deleteDynamicCommentBatch(@RequestBody Long[] ids) {
        return ResponseResult.okResult(dynamicCommentsService.removeByIds(Arrays.asList(ids)));
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "根据id查询动态评论")
    public ResponseResult getDynamicComment(@PathVariable("id") Long id) {
        return dynamicCommentsService.adminDetail(id);
    }

}
