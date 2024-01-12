package com.uni.pj.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.uni.pj.dynamic.dtos.DynamicCommentsPageDto;
import com.uni.pj.dynamic.vos.DynamicCommentPageVo;
import com.uni.pj.ws.pojo.ChatMessage;
import com.uni.pj.ws.vos.FriendMsgVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 * @author author
 * @since 2023-12-06
 */
public interface ChatMessageMapper extends BaseMapper<ChatMessage> {
    IPage<FriendMsgVo> getFriendMsgList(@Param("page") IPage<FriendMsgVo> page, @Param("userId") Integer userId);

    IPage<ChatMessage> getMsgDetail(@Param("page") IPage<FriendMsgVo> page, @Param("send") Integer send, @Param("accept") Integer accept);

}
