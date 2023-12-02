package com.uni.pj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.uni.pj.common.ResponseResult;
import com.uni.pj.dynamic.dtos.DynamicCommentsActionDto;
import com.uni.pj.dynamic.pojos.DynamicCommentActions;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-11-28
 */
public interface DynamicCommentActionsService extends IService<DynamicCommentActions> {

    ResponseResult likeDynamicComments(DynamicCommentsActionDto dto);
}
