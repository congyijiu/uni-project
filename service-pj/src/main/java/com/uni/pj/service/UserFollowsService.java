package com.uni.pj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.uni.pj.common.ResponseResult;
import com.uni.pj.pojos.UserFollows;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-11-28
 */
public interface UserFollowsService extends IService<UserFollows> {

    UserFollows selectFollows(Integer userId, Integer userId1);

    ResponseResult follow(Integer userId);
}
