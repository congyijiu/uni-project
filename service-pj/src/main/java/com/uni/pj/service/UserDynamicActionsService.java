package com.uni.pj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.uni.pj.common.ResponseResult;
import com.uni.pj.dtos.DynamicActionDto;
import com.uni.pj.pojos.UserDynamicActions;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-11-28
 */
public interface UserDynamicActionsService extends IService<UserDynamicActions> {

    ResponseResult actions(DynamicActionDto dynamicActionDto);

    UserDynamicActions selectactions(Long id, Integer userId);
}
