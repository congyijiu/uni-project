package com.uni.pj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.uni.pj.common.ResponseResult;
import com.uni.pj.common.enums.AppHttpCodeEnum;
import com.uni.pj.dtos.DynamicCommentsActionDto;
import com.uni.pj.mapper.DynamicCommentActionsMapper;
import com.uni.pj.pojos.DynamicCommentActions;
import com.uni.pj.pojos.DynamicComments;
import com.uni.pj.service.DynamicCommentActionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uni.pj.service.DynamicCommentsService;
import com.uni.pj.utils.AppThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author author
 * @since 2023-11-28
 */
@Service
public class DynamicCommentActionsServiceImpl extends ServiceImpl<DynamicCommentActionsMapper, DynamicCommentActions> implements DynamicCommentActionsService {

    @Override
    public ResponseResult likeDynamicComments(DynamicCommentsActionDto dto) {
        //1.校验参数
        if (dto.getCommentId() == null || dto.getIsLike() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //2.获取当前用户id
        Integer userId = AppThreadLocalUtil.getAppUserId();
        //3.根据用户id和评论id查询评论点赞表
        LambdaQueryWrapper<DynamicCommentActions> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DynamicCommentActions::getUserId, userId);
        wrapper.eq(DynamicCommentActions::getCommentId, dto.getCommentId());
        DynamicCommentActions actions = baseMapper.selectOne(wrapper);
        //4.如果没有点赞记录，新增点赞记录
        if (actions == null) {
            actions = new DynamicCommentActions();
            actions.setUserId(userId);
            actions.setCommentId(dto.getCommentId());
            actions.setIslike(dto.getIsLike());
            this.save(actions);
        } else {
            //5.如果有点赞记录，更新点赞记录
            actions.setIslike(dto.getIsLike());
            this.updateById(actions);
        }

        //6.更新评论点赞数量
        updateCommentLikeCount(dto.getCommentId(), dto.getIsLike() == 1 ? 1 : -1);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @Autowired
    private DynamicCommentsService dynamicCommentsService;

    @Async
    public void updateCommentLikeCount(Integer commentId, Integer likeCount) {
        DynamicComments byId = dynamicCommentsService.getById(commentId);
        if (byId != null) {
            byId.setLikeCount(byId.getLikeCount() + likeCount);
            dynamicCommentsService.updateById(byId);
        }
    }
}
