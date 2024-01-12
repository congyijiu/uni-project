package com.uni.pj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.uni.pj.common.ResponseResult;
import com.uni.pj.ws.dtos.ChatMessageDto;
import com.uni.pj.ws.dtos.MsgDetailPageDto;
import com.uni.pj.ws.pojo.ChatMessage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-12-06
 */
public interface ChatMessageService extends IService<ChatMessage> {

    ResponseResult getFriendMsgList(Integer index, Integer size);

    ResponseResult addFriendMsg(ChatMessageDto dto);

    ResponseResult getMessageDetail(MsgDetailPageDto acceptUserId);

    ResponseResult setReaded(Integer id);
}
