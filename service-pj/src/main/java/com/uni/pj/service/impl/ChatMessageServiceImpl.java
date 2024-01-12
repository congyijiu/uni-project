package com.uni.pj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.uni.pj.common.ResponseResult;
import com.uni.pj.common.enums.AppHttpCodeEnum;
import com.uni.pj.mapper.ChatMessageMapper;
import com.uni.pj.service.ChatMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uni.pj.utils.AppThreadLocalUtil;
import com.uni.pj.ws.dtos.ChatMessageDto;
import com.uni.pj.ws.dtos.MsgDetailPageDto;
import com.uni.pj.ws.pojo.ChatMessage;
import com.uni.pj.ws.vos.FriendMsgVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2023-12-06
 */
@Service
@Slf4j
public class ChatMessageServiceImpl extends ServiceImpl<ChatMessageMapper, ChatMessage> implements ChatMessageService {

    @Autowired
    private ChatMessageMapper chatMessageMapper;


    @Override
    public ResponseResult getFriendMsgList(Integer index, Integer size) {

        Integer appUserId = AppThreadLocalUtil.getAppUserId();

        Page<FriendMsgVo> friendMsgVoPage = new Page<>(index, size);

        IPage<FriendMsgVo> friendMsgList = chatMessageMapper.getFriendMsgList(friendMsgVoPage, appUserId);


        return ResponseResult.okResult(friendMsgList);
    }

    @Override
    public ResponseResult addFriendMsg(ChatMessageDto dto) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setAcceptUserId(dto.getAcceptUserId());
        chatMessage.setSendUserId(dto.getSendUserId());
        chatMessage.setContent(dto.getContent());
        chatMessage.setType(dto.getType());
        this.save(chatMessage);
        return ResponseResult.okResult("发送成功");
    }

    @Override
    public ResponseResult getMessageDetail(MsgDetailPageDto acceptUserId) {

        //1.校验参数
        if(acceptUserId == null || acceptUserId.getAcceptUserId() == null || acceptUserId.getIndex() == null || acceptUserId.getSize() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //2.获取当前用户id
        Integer appUserId = AppThreadLocalUtil.getAppUserId();
        log.info("appUserId:{}", appUserId);
        Page<FriendMsgVo> friendMsgVoPage = new Page<>(acceptUserId.getIndex(), acceptUserId.getSize());
        IPage<ChatMessage> msgDetail = chatMessageMapper.getMsgDetail(friendMsgVoPage, appUserId, acceptUserId.getAcceptUserId());

        //3.修改消息状态为已读
        LambdaQueryWrapper<ChatMessage> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ChatMessage::getAcceptUserId, appUserId);
        wrapper.eq(ChatMessage::getSendUserId, acceptUserId.getAcceptUserId());
        wrapper.eq(ChatMessage::getReaded, 0);
        this.update(new ChatMessage().setReaded(1), wrapper);
        //4.返回结果
        return ResponseResult.okResult(msgDetail);
    }
}
