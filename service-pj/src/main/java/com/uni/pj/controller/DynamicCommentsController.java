package com.uni.pj.controller;

import com.uni.pj.common.ResponseResult;
import com.uni.pj.dynamic.dtos.DynamicCommentsActionDto;
import com.uni.pj.dynamic.dtos.DynamicCommentsAddDto;
import com.uni.pj.dynamic.dtos.DynamicCommentsPageDto;
import com.uni.pj.service.DynamicCommentActionsService;
import com.uni.pj.service.DynamicCommentsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author congyijiu
 * @create 2023-11-26-12:05
 */
@RestController
@RequestMapping("/dynamic-comments")
@Tag(name = "动态评论相关接口")
public class DynamicCommentsController {

    @Autowired
    private DynamicCommentsService dynamicCommentsService;

    @Autowired
    private DynamicCommentActionsService dynamicCommentActionsService;

    /**
     * 添加动态评论
     * @param dynamicCommentsAddDto
     * @return
     */
    @Operation(summary = "添加动态评论")
    @PostMapping("/add")
    public ResponseResult addDynamicComments(@RequestBody DynamicCommentsAddDto dynamicCommentsAddDto) {
        return dynamicCommentsService.addDynamicComments(dynamicCommentsAddDto);
    }

    /**
     * 分页查询动态评论
     * @param dynamicCommentsPageDto
     * @return
     */
    @Operation(summary = "分页查询动态评论")
    @PostMapping("/page")
    public ResponseResult DynamicCommentsPage(@RequestBody DynamicCommentsPageDto dynamicCommentsPageDto) {
        return dynamicCommentsService.DynamicCommentsPage(dynamicCommentsPageDto);
    }

    /**
     * 用户评论点赞
     * @param dto
     * @return
     */
    @Operation(summary = "用户评论点赞")
    @PostMapping("/like")
    public ResponseResult likeDynamicComments(@RequestBody DynamicCommentsActionDto dto) {
        return dynamicCommentActionsService.likeDynamicComments(dto);
    }


}
