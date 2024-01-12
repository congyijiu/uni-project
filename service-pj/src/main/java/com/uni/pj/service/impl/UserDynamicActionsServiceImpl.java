package com.uni.pj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.uni.pj.common.ResponseResult;
import com.uni.pj.common.enums.AppHttpCodeEnum;
import com.uni.pj.common.enums.DynamicActionEnum;
import com.uni.pj.dynamic.dtos.DynamicActionDto;
import com.uni.pj.mapper.DynamicMapper;
import com.uni.pj.mapper.UserDynamicActionsMapper;
import com.uni.pj.dynamic.pojos.Dynamic;
import com.uni.pj.users.pojo.UserDynamicActions;
import com.uni.pj.service.UserDynamicActionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uni.pj.utils.AppThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2023-11-28
 */
@Service
public class UserDynamicActionsServiceImpl extends ServiceImpl<UserDynamicActionsMapper, UserDynamicActions> implements UserDynamicActionsService {


    @Override
    public ResponseResult actions(DynamicActionDto dynamicActionDto) {
        //1.校验参数
        if (dynamicActionDto == null || dynamicActionDto.getDynamicId() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //2.执行行为
        UserDynamicActions userDynamicActions = new UserDynamicActions();
        userDynamicActions.setDynamicId(dynamicActionDto.getDynamicId());
        Integer type = 0;
        Integer count = 0;
        if (dynamicActionDto.getIslike() != null) {
            userDynamicActions.setIslike(dynamicActionDto.getIslike());
            count = dynamicActionDto.getIslike();
            type = DynamicActionEnum.LIKE.getCode();
        }
        if (dynamicActionDto.getIsfavorite() != null) {
            userDynamicActions.setIsfavorite(dynamicActionDto.getIsfavorite());
            count = dynamicActionDto.getIsfavorite();
            type = DynamicActionEnum.FAVORITE.getCode();
        }
        //3.获取当前用户ID
        Integer userId = AppThreadLocalUtil.getAppUserId();
        userDynamicActions.setUserId(userId);
        //4.查询是否已经有行为
        LambdaQueryWrapper<UserDynamicActions> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserDynamicActions::getUserId, userId);
        wrapper.eq(UserDynamicActions::getDynamicId, dynamicActionDto.getDynamicId());
        UserDynamicActions actions = this.getOne(wrapper);
        //5.判断是否有行为，如果有行为，更新行为
        if (actions != null) {
            //5.1更新行为
            userDynamicActions.setActionId(actions.getActionId());
            updateById(userDynamicActions);
        }else {
            //5.2保存行为
            save(userDynamicActions);
        }

        //6异步更新动态表的点赞数和收藏数
        //6.1获取动态ID
        Integer dynamicId = dynamicActionDto.getDynamicId();
        //6.2异步更新
        updateDynamicCount(dynamicId, type, count);

        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @Override
    public UserDynamicActions selectactions(Long id, Integer userId) {
        LambdaQueryWrapper<UserDynamicActions> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserDynamicActions::getUserId, userId);
        wrapper.eq(UserDynamicActions::getDynamicId, id);
        UserDynamicActions actions = this.getOne(wrapper);
        return actions;
    }

    @Autowired
    private DynamicMapper dynamicMapper;
    @Async
    public void updateDynamicCount(Integer dynamicId, Integer type, Integer count){
        Dynamic dynamic = dynamicMapper.selectById(dynamicId);
        if (count == 0){
            count = -1;
        }
        if(type == DynamicActionEnum.LIKE.getCode()){
            dynamic.setLikeCount(dynamic.getLikeCount()+count);
        }else if(type == DynamicActionEnum.FAVORITE.getCode()){
            dynamic.setFavoriteCount(dynamic.getFavoriteCount()+count);
        }else if(type == DynamicActionEnum.Comment.getCode()){
            dynamic.setCommentCount(dynamic.getCommentCount()+count);
        }
        dynamicMapper.updateById(dynamic);
    }
}
