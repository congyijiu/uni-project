package com.uni.pj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.uni.pj.common.ResponseResult;
import com.uni.pj.dynamic.dtos.AdminDynamicCommentsPageDto;
import com.uni.pj.dynamic.dtos.DynamicCommentsAddDto;
import com.uni.pj.dynamic.dtos.DynamicCommentsPageDto;
import com.uni.pj.dynamic.pojos.DynamicComments;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2023-11-26
 */
public interface DynamicCommentsService extends IService<DynamicComments> {

    /**
     * 添加动态评论
     * @param dynamicCommentsAddDto
     * @return
     */
    ResponseResult addDynamicComments(DynamicCommentsAddDto dynamicCommentsAddDto);

    /**
     * 分页查询动态评论
     * @param dynamicCommentsPageDto
     * @return
     */
    ResponseResult DynamicCommentsPage(DynamicCommentsPageDto dynamicCommentsPageDto);

    /**
     * 后台分页查询动态评论
     *
     * @param page
     * @param pageDto
     * @return
     */
    ResponseResult adminPageList(Integer page, Integer size, AdminDynamicCommentsPageDto pageDto);

    ResponseResult adminDetail(Long id);
}
