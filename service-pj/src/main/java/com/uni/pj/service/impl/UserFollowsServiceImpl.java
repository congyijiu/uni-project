package com.uni.pj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.uni.pj.common.ResponseResult;
import com.uni.pj.common.enums.AppHttpCodeEnum;
import com.uni.pj.mapper.UserFollowsMapper;
import com.uni.pj.pojos.UserFollows;
import com.uni.pj.service.UserFollowsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.uni.pj.utils.AppThreadLocalUtil;
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
public class UserFollowsServiceImpl extends ServiceImpl<UserFollowsMapper, UserFollows> implements UserFollowsService {




    /**
     * 查询关注关系
     * @param userId 被关注者
     * @param userId1 关注者
     * @return
     */
    @Override
    public UserFollows selectFollows(Integer userId, Integer userId1) {
        LambdaQueryWrapper<UserFollows> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserFollows::getFollowerId,userId1);
        wrapper.eq(UserFollows::getFollowingId,userId);
        return this.getOne(wrapper);
    }

    @Override
    public ResponseResult follow(Integer userId) {
        //1.校验参数
        if (userId == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //2.获取当前用户id
        Integer appUserId = AppThreadLocalUtil.getAppUserId();
        //3.查询关注关系
        UserFollows userFollows = selectFollows(userId, appUserId);
        //4.判断是否已经关注
        if (userFollows != null) {
            this.removeById(userFollows.getId());
        }else {
            userFollows = new UserFollows();
            userFollows.setFollowerId(appUserId);
            userFollows.setFollowingId(userId);
            this.save(userFollows);
        }

        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }
}
