package com.uni.pj.controller;

import com.uni.pj.common.ResponseResult;
import com.uni.pj.service.ChatMessageService;
import com.uni.pj.ws.dtos.ChatMessageDto;
import com.uni.pj.ws.dtos.MsgDetailPageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author congyijiu
 * @create 2023-12-06-21:11
 */
@RestController
@RequestMapping("/message")
@Tag(name = "消息相关接口")
public class MessageController{

    @Autowired
    private ChatMessageService chatMessageService;

    @GetMapping("/friendMsgList/{index}/{size}")
    @Operation(summary = "获取好友消息列表")
    public ResponseResult getFriendMsgList(@PathVariable("index") Integer index, @PathVariable("size") Integer size) {
        return chatMessageService.getFriendMsgList(index, size);
    }

    @PostMapping("/addFriendMsg")
    @Operation(summary = "添加好友消息")
    public ResponseResult addFriendMsg(ChatMessageDto dto) {
        return chatMessageService.addFriendMsg(dto);
    }

    @PostMapping("/messageDetail")
    @Operation(summary = "获取消息详情")
    public ResponseResult getMessageDetail(@RequestBody MsgDetailPageDto dto) {
        return chatMessageService.getMessageDetail(dto);
    }
}
